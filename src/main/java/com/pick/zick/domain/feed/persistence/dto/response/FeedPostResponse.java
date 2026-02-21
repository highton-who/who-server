package com.pick.zick.domain.feed.persistence.dto.response;

import com.pick.zick.domain.feed.entity.Feed;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedPostResponse {
    private final String title;
    private final String description;
    private final String imgUrl;
    private final GiftedPersonResponse giftedPerson;

    public static FeedPostResponse from(Feed feed) {
        return FeedPostResponse.builder()
                .title(feed.getTitle())
                .description(feed.getDescription())
                .imgUrl(feed.getImgUrl())
                .giftedPerson(GiftedPersonResponse.from(feed.getGiftedPerson()))
                .build();
    }
}
