package org.ct.service;

import org.ct.dto.request.RequestPost;
import org.ct.entity.Post;
import java.util.List;

public interface PostService {
    List<Post> findAll();

    Post findById(long postId);

    Post add(RequestPost request);

    Post update(RequestPost request, long postId);

    void delete(long postId);
}
