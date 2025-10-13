package com.pick.zick.domain.student.dto.request;

import lombok.Getter;

@Getter
public class UpdateStudentRequest {
    private String studentNumber;
    private String userName;
    private Boolean applied;
}
