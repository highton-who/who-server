package com.pick.zick.domain.student.service;

import com.pick.zick.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteStudentService {
    private final UserRepository userRepository;

    public void execute(Long id) {
        userRepository.deleteById(id);
    }
}
