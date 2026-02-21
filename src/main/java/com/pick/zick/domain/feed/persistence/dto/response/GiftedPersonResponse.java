package com.pick.zick.domain.feed.persistence.dto.response;

import com.pick.zick.domain.feed.entity.GiftedPerson;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GiftedPersonResponse {
    private final String name;
    private final String birthday;
    private final int giftPrice;
    private final String occasion;
    private final String memo;
    private final List<String> tags;

    public static GiftedPersonResponse from(GiftedPerson giftedPerson) {
        return GiftedPersonResponse.builder()
                .name(giftedPerson.getName())
                .birthday(giftedPerson.getBirthday())
                .giftPrice(giftedPerson.getGiftPrice())
                .occasion(giftedPerson.getOccasion())
                .memo(giftedPerson.getMemo())
                .tags(giftedPerson.getTags())
                .build();
    }
}
