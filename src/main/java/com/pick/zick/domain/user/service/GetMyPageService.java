package com.pick.zick.domain.user.service;

import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.facade.UserFacade;
import com.pick.zick.domain.user.persistence.dto.response.UserMyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetMyPageService {
    private final UserFacade userFacade;

    public UserMyPageResponse getMyPage(){
        User user = userFacade.getCurrentUser();

        return UserMyPageResponse.builder()
                .id(user.getId())
                .name(user.getLoginId())
                .build();
    }
}