package com.pick.zick.domain.feed.repository;

import com.pick.zick.domain.feed.entity.Feed;
import com.pick.zick.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    List<Feed> findByUser(User user);
}
