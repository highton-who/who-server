package com.pick.zick.domain.feed.service;

import com.pick.zick.domain.feed.persistence.dto.response.FeedResponse;
import com.pick.zick.domain.feed.repository.FeedRepository;
import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GetFeedListService {
    private final FeedRepository feedRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public List<FeedResponse> getFeedList() {
        User user = userFacade.getCurrentUser();
        return feedRepository.findByUser(user).stream()
                .map(FeedResponse::from)
                .collect(Collectors.toList());
    }
}
