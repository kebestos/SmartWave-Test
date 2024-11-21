package com.devskiller.tasks.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devskiller.tasks.blog.model.Post;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {


	Optional<Object> findAllBy(Long id);
}
