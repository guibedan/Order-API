package com.guibedan.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guibedan.course.entities.Users;

public interface UserRepository extends JpaRepository<Users, Long> {
}
