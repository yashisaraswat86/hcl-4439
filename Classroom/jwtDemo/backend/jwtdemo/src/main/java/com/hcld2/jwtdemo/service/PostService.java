package com.hcld2.jwtdemo.service;

import com.hcld2.jwtdemo.dto.PostRequest;
import com.hcld2.jwtdemo.dto.PostResponse;
import com.hcld2.jwtdemo.entity.Post;
import com.hcld2.jwtdemo.repository.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostResponse> findAll() {
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(PostResponse::fromEntity)
                .toList();
    }

    public PostResponse addPost(PostRequest req, String author) {
        Post post = new Post(req.title(), req.content(), author);
        Post saved = postRepository.save(post);
        return PostResponse.fromEntity(saved);
    }
}

