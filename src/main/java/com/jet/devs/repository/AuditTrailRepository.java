package com.jet.devs.repository;

import com.jet.devs.model.AuditTrail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: ab
 * Date Time: 07/11/23
 */
@Repository
public interface AuditTrailRepository extends JpaRepository<AuditTrail, Long> {
}
