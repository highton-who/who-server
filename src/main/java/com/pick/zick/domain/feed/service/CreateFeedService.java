package com.pick.zick.domain.feed.service;

import com.pick.zick.domain.feed.entity.Feed;
import com.pick.zick.domain.feed.entity.GiftedPerson;
import com.pick.zick.domain.feed.persistence.dto.request.CreateFeedRequest;
import com.pick.zick.domain.feed.persistence.dto.request.GiftedPersonRequest;
import com.pick.zick.domain.feed.persistence.dto.response.FeedPostResponse;
import com.pick.zick.domain.feed.repository.FeedRepository;
import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateFeedService {
    private final FeedRepository feedRepository;
    private final UserFacade userFacade;

    @Transactional
    public FeedPostResponse createFeed(CreateFeedRequest request) {
        User user = userFacade.getCurrentUser();
        GiftedPersonRequest gpr = request.getGiftedPerson();

        GiftedPerson giftedPerson = GiftedPerson.builder()
                .name(gpr.getName())
                .birthday(gpr.getBirthday())
                .giftPrice(gpr.getGiftPrice())
                .occasion(gpr.getOccasion())
                .memo(gpr.getMemo())
                .tags(gpr.getTags())
                .build();

        Feed feed = Feed.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .imgUrl(request.getImgUrl())
                .giftedPerson(giftedPerson)
                .user(user)
                .build();

        feedRepository.save(feed);
        return FeedPostResponse.from(feed);
    }
}
