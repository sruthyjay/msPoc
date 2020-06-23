package com.ibm.orderprocess.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.orderprocess.entity.AuditLog;

@Repository
public interface AuditRepo extends JpaRepository<AuditLog, Integer>{

}
