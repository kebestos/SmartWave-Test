package com.devskiller.tasks.blog.rest;

import com.devskiller.tasks.blog.model.dto.CommentDto;
import com.devskiller.tasks.blog.model.dto.NewCommentDto;
import com.devskiller.tasks.blog.model.dto.PostDto;
import com.devskiller.tasks.blog.service.CommentService;
import com.devskiller.tasks.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/posts")
public class PostController {

	private final PostService postService;

	private final CommentService commentService;

	@Autowired
	public PostController(PostService postService, CommentService commentService) {
		this.postService = postService;
		this.commentService = commentService;
	}

	@GetMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.OK)
	public PostDto getPost(@PathVariable Long id) {
		return postService.getPost(id);
	}


//	POST at /posts/{id}/comments which should:
//	Save a new comment with current date and time for post with passed {id}
//	Return 201 Created if comment is created successfully for post with passed {id}
//	Return 404 Not Found if post with passed {id} does not exist
	@PostMapping(value = "/{id}/comments")
	public ResponseEntity<CommentDto> createNewComment(@PathVariable Long id) {
		try{
			PostDto post = postService.getPost(id);
			NewCommentDto newCommentDto = new NewCommentDto("test","test");
			Long id = CommentService.addComment(id,newCommentDto);
			return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
		} catch (Exception e) {
			//provide exception information in body and log
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

//	GET at /posts/{id}/comments which should:
//	Return all comments sorted by creation date in descending order for a post with passed {id}
//	Return empty list if a post with passed {id} does not exists or when it does not contain any comments
	@GetMapping(value = "/{id}")
	public ResponseEntity<List<CommentDto>> getCommentsForPost(@PathVariable Long id) {
		try{
			List<CommentDto> commentDTOS = commentService.getCommentsForPost(id);
			return new ResponseEntity<>(commentDTOS, HttpStatus.OK);
		} catch (Exception e) {
			//provide exception information in body and log
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
  }
}
