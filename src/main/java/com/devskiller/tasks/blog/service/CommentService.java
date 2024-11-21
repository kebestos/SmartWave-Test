package com.devskiller.tasks.blog.service;

import java.util.List;

import com.devskiller.tasks.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;

@Service
public class CommentService {

	private final PostRepository postRepository;

	public CommentService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	/**
	 * Returns a list of all comments for a blog post with passed id.
	 *
	 * @param postId id of the post
	 * @return list of comments sorted by creation date descending - most recent first
	 */
	public List<CommentDto> getCommentsForPost(Long postId) {
		try{
			//get all comment from
			postRepository.findAllById().;
			//sort

		}catch (Exception e){
			throw new UnsupportedOperationException(/*TODO*/);
		}
	}

	/**
	 * Creates a new comment
	 *
	 * @param postId id of the post
	 * @param newCommentDto data of new comment
	 * @return id of the created comment
	 *
	 * @throws IllegalArgumentException if postId is null or there is no blog post for passed postId
	 */
	public Long addComment(Long postId, NewCommentDto newCommentDto) {
		try{

		}catch (Exception e){
			throw new UnsupportedOperationException(/*TODO*/);
		}

	}
}
