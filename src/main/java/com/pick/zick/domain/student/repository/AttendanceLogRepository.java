package com.pick.zick.domain.student.repository;

import com.pick.zick.domain.student.entity.AttendanceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceLogRepository extends JpaRepository<AttendanceLog, Long> {
    List<AttendanceLog> findAllByUser_AppliedTrue();
}