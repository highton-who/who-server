package com.pick.zick.domain.feed.persistence.dto.response;

import com.pick.zick.domain.feed.entity.Feed;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedDetailResponse {
    private final String title;
    private final String description;
    private final GiftedPersonResponse giftedPerson;

    public static FeedDetailResponse from(Feed feed) {
        return FeedDetailResponse.builder()
                .title(feed.getTitle())
                .description(feed.getDescription())
                .giftedPerson(GiftedPersonResponse.from(feed.getGiftedPerson()))
                .build();
    }
}
