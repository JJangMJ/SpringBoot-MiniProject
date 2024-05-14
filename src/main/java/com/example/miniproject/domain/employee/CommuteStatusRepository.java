package com.example.miniproject.domain.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommuteStatusRepository extends JpaRepository<CommuteStatus, Long> {
    boolean existsByEmployeeId(Long employeeId);

    CommuteStatus findByEmployeeId(Long employeeId);
}
