package com.ibm.accountlogin;

import java.security.Principal;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Sso
@RestController
public class AccountLoginApplication extends WebSecurityConfigurerAdapter {

	@Autowired
	JWTUtility jwtUtility;

	public static void main(String[] args) {
		SpringApplication.run(AccountLoginApplication.class, args);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// Configuring Spring security access. For /login, /user, and /userinfo, we need
		// authentication.
		// Logout is enabled.
		// Adding csrf token support to this configurer.
		http.authorizeRequests().antMatchers("/login**", "/user", "/userInfo").authenticated().and().logout()
				.logoutSuccessUrl("/").permitAll().and().csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

	}

	@RequestMapping("/user")
	public Principal user(Principal principal) {
		// Principal holds the logged in user information.
		// Spring automatically populates this principal object after login.
		return principal;
	}

	@RequestMapping("/userInfo")
	public String userInfo(Principal principal) {
		final OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
		final Authentication authentication = oAuth2Authentication.getUserAuthentication();
		// Manually getting the details from the authentication, and returning them as
		// String.
		return authentication.getDetails().toString();
	}

	@RequestMapping("/userToken")
	public String getUsrToken(Principal principal) {
		String tokenValue = null;
		String tokentime = LocalTime.now().toString();
		final Authentication authObj = SecurityContextHolder.getContext().getAuthentication();
		final Object detailObj = authObj.getDetails();
		if (detailObj instanceof OAuth2AccessToken) {
			final OAuth2AccessToken token = (OAuth2AccessToken) detailObj;
			tokenValue = token.getValue();
		} else if (detailObj instanceof OAuth2AuthenticationDetails) {
			final OAuth2AuthenticationDetails authDetail = (OAuth2AuthenticationDetails) detailObj;
			tokenValue = authDetail.getTokenValue();
		} else {
			tokenValue = null;
		}
		return tokenValue + ":TTL:" + tokentime;
	}

	@RequestMapping("/transactionToken")
	public String getTransactionToken(Principal principal) {
		return jwtUtility.generateToken(principal.getName());
	}

}
