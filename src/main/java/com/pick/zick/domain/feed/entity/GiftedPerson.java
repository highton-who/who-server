package com.pick.zick.domain.feed.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GiftedPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String birthday;
    private int giftPrice;
    private String occasion;
    private String memo;

    @ElementCollection
    @CollectionTable(name = "gifted_person_tags", joinColumns = @JoinColumn(name = "gifted_person_id"))
    @Column(name = "tag")
    @Builder.Default
    private List<String> tags = new ArrayList<>();

    public void update(String name, String birthday, int giftPrice, String occasion, String memo, List<String> tags) {
        this.name = name;
        this.birthday = birthday;
        this.giftPrice = giftPrice;
        this.occasion = occasion;
        this.memo = memo;
        this.tags = tags;
    }
}
