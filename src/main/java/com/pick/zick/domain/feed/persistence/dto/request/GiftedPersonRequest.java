package com.pick.zick.domain.feed.persistence.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GiftedPersonRequest {
    private String name;
    private String birthday;
    private int giftPrice;
    private String occasion;
    private String memo;
    private List<String> tags;
}
