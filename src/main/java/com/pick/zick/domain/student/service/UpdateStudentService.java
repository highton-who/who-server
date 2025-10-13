package com.pick.zick.domain.student.service;

import com.pick.zick.domain.student.dto.request.UpdateStudentRequest;
import com.pick.zick.domain.user.entity.User;
import com.pick.zick.domain.user.exception.UserNotFoundException;
import com.pick.zick.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateStudentService {
    private final UserRepository userRepository;

    @Transactional
    public void execute(Long id, UpdateStudentRequest updateStudentRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> UserNotFoundException.EXCEPTION);
        user.update(
                updateStudentRequest.getStudentNumber(),
                updateStudentRequest.getUserName(),
                updateStudentRequest.getApplied()
        );
    }
}
