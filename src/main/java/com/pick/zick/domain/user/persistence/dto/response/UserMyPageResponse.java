package com.pick.zick.domain.user.persistence.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserMyPageResponse {
    private final Long id;
    private final String name;

    @Getter
    @Builder
    public static class FeedResponse {
        private final Long id;
        private final String imgUrl;
        private final String title;
        private final int likeCount;
        private final Boolean liked;
    }
}
