package com.pick.zick.domain.feed.service;

import com.pick.zick.domain.feed.entity.Feed;
import com.pick.zick.domain.feed.persistence.dto.request.UpdateLikeResponse;
import com.pick.zick.domain.feed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateLikedService {
    public final FeedRepository feedRepository;

    @Transactional
    public void excute(Long feedId, UpdateLikeResponse request) {
        Feed feed = feedRepository.findById(feedId)
                .
    }
}