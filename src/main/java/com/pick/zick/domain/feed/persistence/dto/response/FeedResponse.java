package com.pick.zick.domain.feed.persistence.dto.response;

import com.pick.zick.domain.feed.entity.Feed;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedResponse {
    private final Long id;
    private final String imgUrl;
    private final String title;

    public static FeedResponse from(Feed feed) {
        return FeedResponse.builder()
                .id(feed.getId())
                .imgUrl(feed.getImgUrl())
                .title(feed.getTitle())
                .build();
    }
}
