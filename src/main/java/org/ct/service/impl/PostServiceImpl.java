package org.ct.service.impl;

import org.ct.entity.Post;
import org.ct.entity.Tag;
import org.ct.dto.request.RequestPost;
import org.ct.exception.PostTagException;
import org.ct.repository.PostRepository;
import org.ct.repository.TagRepository;
import org.ct.service.PostService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;

@ApplicationScoped
@Transactional
public class PostServiceImpl implements PostService {

    @Inject
    private PostRepository postRepository;

    @Inject
    private TagRepository tagRepository;


    @Override
    public List<Post> findAll(){
        return postRepository.listAll();
    }

    @Override
    public Post findById(long postId) {
        Optional<Post> post = postRepository.findByIdOptional(postId);
        if(!post.isPresent()) {
            throw new PostTagException("Post id "+postId+" tidak ditemukan");
        }
        return post.get();
    }

    @Override
    public Post add(RequestPost request) {
        List<Tag> tags = Optional.ofNullable(request.getTagId())
                                .map(x -> tagRepository.findByTagIds(Arrays.asList(x))).orElse(new ArrayList<>());
        Post post = new Post();
        post.setContent(request.getContent());
        post.setTitle(request.getTitle());
        post.setTags(tags);

        postRepository.persist(post);
        return post;
    }

    @Override
    public Post update(RequestPost request, long postId){
        Post post = findById(postId);
        List<Tag> tags = Optional.ofNullable(request.getTagId())
                                .map(x -> tagRepository.findByTagIds(Arrays.asList(x))).orElse(new ArrayList<>());

        post.setContent(request.getContent());
        post.setTitle(request.getTitle());
        post.setTags(tags);

        postRepository.persist(post);
        return post;
    }

    @Override
    public void delete(long postId){
        Post post = findById(postId);
        postRepository.delete(post);
    }

}
