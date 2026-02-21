package com.pick.zick.domain.feed.persistence.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateFeedRequest {
    private String title;
    private String description;
    private String imgUrl;
    private GiftedPersonRequest giftedPerson;
}
