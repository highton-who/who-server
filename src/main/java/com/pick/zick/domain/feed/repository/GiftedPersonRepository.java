package com.pick.zick.domain.feed.repository;

import com.pick.zick.domain.feed.entity.GiftedPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GiftedPersonRepository extends JpaRepository<GiftedPerson, Long> {

    @Query("SELECT DISTINCT g.name FROM GiftedPerson g")
    List<String> findAllNames();
}
