package com.pick.zick.domain.feed.service;

import com.pick.zick.domain.feed.entity.Feed;
import com.pick.zick.domain.feed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteFeedService {
    private final FeedRepository feedRepository;

    @Transactional
    public void deleteFeed(Long feedId) {
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("feed not found"));

        feedRepository.delete(feed);
    }
}
