package com.pick.zick.domain.feed.service;

import com.pick.zick.domain.feed.entity.Feed;
import com.pick.zick.domain.feed.persistence.dto.response.FeedDetailResponse;
import com.pick.zick.domain.feed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetFeedService {
    private final FeedRepository feedRepository;

    @Transactional(readOnly = true)
    public FeedDetailResponse getFeed(Long feedId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("feed not found"));
        return FeedDetailResponse.from(feed);
    }
}
