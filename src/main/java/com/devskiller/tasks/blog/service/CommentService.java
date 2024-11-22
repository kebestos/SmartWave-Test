package com.devskiller.tasks.blog.service;

import com.devskiller.tasks.blog.model.Comment;
import com.devskiller.tasks.blog.model.Post;
import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.repository.CommentRepository;
import com.devskiller.tasks.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final PostRepository postRepository;

	@Autowired
	public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
		this.commentRepository = commentRepository;
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
			Optional<Post> post = postRepository.findById(postId);
			if(post.isPresent()){
				List<CommentDto> commentDtos = new java.util.ArrayList<>(post.get().getCommentList().stream()
					.map(comment -> new CommentDto(comment.getId(), comment.getComment(), comment.getAuthor(), comment.getCreationDate()))
					.toList());
				commentDtos.sort((comment1, comment2) -> comment2.creationDate().compareTo(comment1.creationDate()));
				return commentDtos;
			}
			else return Collections.emptyList();
		}catch (Exception e){
			throw new UnsupportedOperationException(e.getMessage(),e.getCause());
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
			if(postId == null) throw new IllegalArgumentException("postId is null");
			Optional<Post> post = postRepository.findById(postId);
			if(post.isPresent()){
				Comment comment = new Comment(post.get(),newCommentDto.content(),newCommentDto.author(),LocalDateTime.now());
				Comment createdComment = commentRepository.save(comment);
				return createdComment.getId();
			}
			else throw new IllegalArgumentException("post not found");
		}catch (Exception e){
			throw new UnsupportedOperationException(e.getMessage(),e.getCause());
		}
	}
}
