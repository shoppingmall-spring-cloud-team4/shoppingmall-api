package com.nhnacademy.shoppingmall.repository;

import com.nhnacademy.shoppingmall.domain.UserDto;
import com.nhnacademy.shoppingmall.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    List<UserDto> getAllBy();
    Page<UserDto> getPagesBy(Pageable pageable);

    Optional<UserDto> getByUserId(String userId);
}
