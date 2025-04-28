package com.example.firstdemo.dao;

import com.example.firstdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Daniel
 * @since 2025-04-28
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 可以添加自定义查询方法
}