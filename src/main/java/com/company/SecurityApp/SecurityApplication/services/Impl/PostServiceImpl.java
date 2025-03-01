package com.company.SecurityApp.SecurityApplication.services.Impl;

import com.company.SecurityApp.SecurityApplication.dto.PostDTO;
import com.company.SecurityApp.SecurityApplication.entities.PostEntity;
import com.company.SecurityApp.SecurityApplication.entities.User;
import com.company.SecurityApp.SecurityApplication.exceptions.ResourceNotFoundException;
import com.company.SecurityApp.SecurityApplication.repositories.PostRepository;
import com.company.SecurityApp.SecurityApplication.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService  {

    private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;




    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity,PostDTO.class))
                .collect(Collectors.toList());
    }



    @Override
    public PostDTO createNewPost(PostDTO inputPost){
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        return  modelMapper.map(postRepository.save(postEntity),PostDTO.class);
    }



    @Override
    public PostDTO getPostById(Long postId) {
      User user= (User)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      log.info("user {}",user);
        PostEntity postEntity =  postRepository
                .findById(postId)
                .orElseThrow(() ->new ResourceNotFoundException("post found with id: "+postId));

        return modelMapper.map(postEntity,PostDTO.class);
    }


    @Override
    public PostDTO updatePost(PostDTO inputPost, Long postId) {
        PostEntity olderPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + postId));
        modelMapper.map(inputPost, olderPost);

        olderPost.setId(postId);

        PostEntity savedPostEntity = postRepository.save(olderPost);
        return modelMapper.map(savedPostEntity, PostDTO.class);
    }
}
