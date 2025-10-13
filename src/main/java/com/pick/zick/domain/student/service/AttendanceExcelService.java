package com.pick.zick.domain.student.service;

import com.pick.zick.domain.student.dto.response.ExcelResponse;
import com.pick.zick.domain.student.entity.AttendanceLog;
import com.pick.zick.domain.student.repository.AttendanceLogRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceExcelService {
    private final AttendanceLogRepository attendanceLogRepository;

    @Transactional(readOnly = true)
    public ExcelResponse generateExcel() throws IOException {
        List<AttendanceLog> logs = attendanceLogRepository.findAllByUser_AppliedTrue();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("출석 명단");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("학번");
        header.createCell(1).setCellValue("이름");
        header.createCell(2).setCellValue("아침");
        header.createCell(3).setCellValue("점심");
        header.createCell(4).setCellValue("저녁");

        CellStyle[] gradeStyles = new CellStyle[3];
        IndexedColors[] colors = {
                IndexedColors.LIGHT_YELLOW,
                IndexedColors.LIGHT_GREEN,
                IndexedColors.LIGHT_BLUE,
        };
        for (int i = 0; i < 3; i++) {
            CellStyle style = workbook.createCellStyle();
            style.setFillForegroundColor(colors[i].getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            gradeStyles[i] = style;
        }

        Map<String, List<AttendanceLog>> grouped =
                logs.stream().collect(Collectors.groupingBy(log -> log.getUser().getStudentNumber()));

        List<String> sortedStudentNumbers = grouped.keySet().stream()
                .sorted(
                        Comparator
                                .comparing((String studentNumber) -> Character.getNumericValue(studentNumber.charAt(0))) // 1. 학년 비교
                                .thenComparing((String studentNumber) -> Character.getNumericValue(studentNumber.charAt(1))) // 2. 반 비교
                                .thenComparing((String studentNumber) -> Integer.parseInt(studentNumber.substring(2))) // 3. 번호 비교

                )
                .toList();

        int rowIdx = 1;
        for (String studentNumber : sortedStudentNumbers) {
            List<AttendanceLog> attendanceLogs = grouped.get(studentNumber);

            String userName = attendanceLogs.stream()
                    .findFirst()
                    .map(log -> log.getUser().getUserName())
                    .orElse("");

            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(studentNumber);
            row.createCell(1).setCellValue(userName);

            for (int col = 2; col <= 4; col++) {
                row.createCell(col).setCellValue(" ");
            }

            int grade = Character.getNumericValue(studentNumber.charAt(0));
            if (grade >= 1 && grade <= 3) {
                for (int col = 0; col <= 4; col++) {
                    Cell cell = row.getCell(col);
                    if (cell == null) cell = row.createCell(col);
                    cell.setCellStyle(gradeStyles[grade - 1]);
                }
            }

            for (AttendanceLog log : attendanceLogs) {
                if (log.getStatus()) {
                    switch (log.getMealType()) {
                        case BREAKFAST -> row.getCell(2).setCellValue("O"); // 🌟 2번 컬럼
                        case LUNCH -> row.getCell(3).setCellValue("O");     // 🌟 3번 컬럼
                        case DINNER -> row.getCell(4).setCellValue("O");    // 🌟 4번 컬럼
                    }
                }
            }
        }

        for (int i = 0; i <= 4; i++) {
            sheet.autoSizeColumn(i);
        }

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workbook.write(bos);
            workbook.close();
            String base64Excel = Base64.getEncoder().encodeToString(bos.toByteArray());
            return new ExcelResponse("attendance.xlsx", base64Excel);
        }
    }
}
