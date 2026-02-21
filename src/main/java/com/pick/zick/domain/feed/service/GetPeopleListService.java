package com.pick.zick.domain.feed.service;

import com.pick.zick.domain.feed.repository.GiftedPersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetPeopleListService {
    private final GiftedPersonRepository giftedPersonRepository;

    @Transactional(readOnly = true)
    public List<String> getPeopleList() {
        return giftedPersonRepository.findAllNames();
    }
}
