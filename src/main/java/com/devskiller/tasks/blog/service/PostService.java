package com.devskiller.tasks.blog.service;

import com.devskiller.tasks.blog.model.Post;
import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.dto.PostDto;
import com.devskiller.tasks.blog.repository.PostRepository;

@Service
public class PostService {

	private final PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public PostDto getPost(Long id) {
		return postRepository.findById(id)
				.map(post -> new PostDto(post.getTitle(), post.getContent(), post.getCreationDate()))
				.orElse(null);
	}

	public PostDto createNewComment(Long id){
		return postRepository.save(id)
			.map(post -> new PostDto(post.getTitle(), post.getContent(), post.getCreationDate()))
			.orElse(null);
	}
}
