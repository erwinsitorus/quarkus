package org.ct.service;

import org.ct.dto.request.RequestTag;
import org.ct.entity.Tag;
import java.util.List;

public interface TagService {
    List<Tag> findAll();

    List<Tag> findByLabel(String label);

    Tag findById(long postId);

    Tag add(RequestTag request);

    Tag update(RequestTag request, long tagId);

    void delete(long postId);
}
