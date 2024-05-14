package com.example.miniproject.domain.employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommuteStatusRepository extends JpaRepository<CommuteStatus, Long> {
    boolean existsByEmployeeId(Long employeeId);
    CommuteStatus findByEmployeeId(Long employeeId);
}
