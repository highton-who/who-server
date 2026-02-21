package com.pick.zick.domain.user.controller;

import com.pick.zick.domain.user.persistence.dto.response.UserMyPageResponse;
import com.pick.zick.domain.user.service.GetMyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final GetMyPageService getMyPageService;

    @GetMapping("/users/me")
    public UserMyPageResponse getUserByUserName() {
        return getMyPageService.getMyPage();
    }
}