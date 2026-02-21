package com.pick.zick.domain.feed.service;

import com.pick.zick.domain.feed.persistence.dto.response.FeedResponse;
import com.pick.zick.domain.feed.repository.FeedRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetFeedListService {
    private final FeedRepository feedRepository;

    @Transactional(readOnly = true)
    public List<FeedResponse> getFeedList() {
        return feedRepository.findAll().stream()
                .map(FeedResponse::from)
                .collect(Collectors.toList());
    }
}
