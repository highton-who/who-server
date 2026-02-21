package com.pick.zick.domain.feed.controller;

import com.pick.zick.domain.feed.persistence.dto.request.CreateFeedRequest;
import com.pick.zick.domain.feed.persistence.dto.request.UpdateFeedRequest;
import com.pick.zick.domain.feed.persistence.dto.response.FeedDetailResponse;
import com.pick.zick.domain.feed.persistence.dto.response.FeedPostResponse;
import com.pick.zick.domain.feed.persistence.dto.response.FeedResponse;
import com.pick.zick.domain.feed.service.CreateFeedService;
import com.pick.zick.domain.feed.service.GetFeedListService;
import com.pick.zick.domain.feed.service.GetFeedService;
import com.pick.zick.domain.feed.service.DeleteFeedService;
import com.pick.zick.domain.feed.service.GetPeopleListService;
import com.pick.zick.domain.feed.service.UpdateFeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FeedController {
    private final GetFeedListService getFeedListService;
    private final GetFeedService getFeedService;
    private final CreateFeedService createFeedService;
    private final UpdateFeedService updateFeedService;
    private final DeleteFeedService deleteFeedService;
    private final GetPeopleListService getPeopleListService;

    @GetMapping("/feed")
    public List<FeedResponse> getFeedList() {
        return getFeedListService.getFeedList();
    }

    @GetMapping("/feed/{id}")
    public FeedDetailResponse getFeed(@PathVariable Long id) {
        return getFeedService.getFeed(id);
    }

    @PostMapping("/posting")
    @ResponseStatus(HttpStatus.CREATED)
    public FeedPostResponse createFeed(@RequestBody CreateFeedRequest request) {
        return createFeedService.createFeed(request);
    }

    @PatchMapping("/posting/{id}")
    public FeedPostResponse updateFeed(@PathVariable Long id, @RequestBody UpdateFeedRequest request) {
        return updateFeedService.updateFeed(id, request);
    }

    @DeleteMapping("/posting/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFeed(@PathVariable Long id) {
        deleteFeedService.deleteFeed(id);
    }

    @GetMapping("/peoples")
    public List<String> getPeopleList() {
        return getPeopleListService.getPeopleList();
    }
}
