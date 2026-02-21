package com.pick.zick.domain.feed.service;

import com.pick.zick.domain.feed.entity.Feed;
import com.pick.zick.domain.feed.entity.GiftedPerson;
import com.pick.zick.domain.feed.persistence.dto.request.GiftedPersonRequest;
import com.pick.zick.domain.feed.persistence.dto.request.UpdateFeedRequest;
import com.pick.zick.domain.feed.persistence.dto.response.FeedPostResponse;
import com.pick.zick.domain.feed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateFeedService {
    private final FeedRepository feedRepository;

    @Transactional
    public FeedPostResponse updateFeed(Long feedId, UpdateFeedRequest request) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("feed not found"));

        GiftedPersonRequest gpr = request.getGiftedPerson();
        feed.getGiftedPerson().update(
                gpr.getName(),
                gpr.getBirthday(),
                gpr.getGiftPrice(),
                gpr.getOccasion(),
                gpr.getMemo(),
                gpr.getTags()
        );

        feed.update(request.getTitle(), request.getDescription(), request.getImgUrl(), feed.getGiftedPerson());
        return FeedPostResponse.from(feed);
    }
}
