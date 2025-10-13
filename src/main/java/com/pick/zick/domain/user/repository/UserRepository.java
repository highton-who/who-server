package com.pick.zick.domain.user.repository;

import com.pick.zick.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    boolean existsByLoginId(String loginId);
    Optional<User> findByLoginId(String loginId);
}

