package com.pick.zick.domain.student.controller;

import com.pick.zick.domain.student.dto.request.CheckCanEnterRequest;
import com.pick.zick.domain.student.dto.request.UpdateStudentRequest;
import com.pick.zick.domain.student.dto.response.CheckCanEnterResponse;
import com.pick.zick.domain.student.dto.response.ExcelResponse;
import com.pick.zick.domain.student.dto.response.QrResponse;
import com.pick.zick.domain.student.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final GetRandomHash getRandomHash;
    private final CheckCanEnterService checkCanEnterService;
    private final AttendanceExcelService attendanceExcelService;
    private final UpdateStudentService updateStudentService;
    private final DeleteStudentService deleteStudentService;

    @GetMapping("/qr")
    public QrResponse getRandomHash() throws NoSuchAlgorithmException {
        return getRandomHash.makeHash();
    }

    @PostMapping("/attendances")
    public CheckCanEnterResponse checkCanEnterService(@RequestBody CheckCanEnterRequest checkCanEnterRequest) {
        return checkCanEnterService.execute(checkCanEnterRequest.getKey());
    }

    @GetMapping("/attendances/excel")
    public ExcelResponse getAttendanceExcel() throws IOException {
        return attendanceExcelService.generateExcel();
    }

    @PatchMapping("students/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequest updateStudentRequest) {
        updateStudentService.execute(id, updateStudentRequest);
    }

    @DeleteMapping("students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        deleteStudentService.execute(id);
    }
}
