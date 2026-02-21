package com.pick.zick.domain.feed.entity;

import com.pick.zick.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imgUrl;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int likeCount;

    @Builder.Default
    @Column(nullable = false)
    private Boolean liked = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void updateLike(boolean isLike) {
        this.liked = isLike;
        if (isLike) {
            this.likeCount++;
        }
        else {
            this.likeCount = Math.max(0, this.likeCount - 1);
        }
    }
}
