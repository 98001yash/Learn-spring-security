package com.company.SecurityApp.SecurityApplication.services;


import com.company.SecurityApp.SecurityApplication.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();


    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(PostDTO inputPost, Long postId);
}

