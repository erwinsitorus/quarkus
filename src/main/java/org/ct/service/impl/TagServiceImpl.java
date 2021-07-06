package org.ct.service.impl;

import org.ct.dto.request.RequestTag;
import org.ct.entity.Tag;
import org.ct.exception.PostTagException;
import org.ct.repository.TagRepository;
import org.ct.service.TagService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional
public class TagServiceImpl implements TagService {

    @Inject
    private TagRepository tagRepository;

    @Override
    public List<Tag> findAll(){
        return tagRepository.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public List<Tag> findByLabel(String label){
        return tagRepository.findByLabel(label);
    }

    @Override
    public Tag findById(long tagId) {
        Optional<Tag> tag = tagRepository.findByIdOptional(tagId);
        if(!tag.isPresent()) {
            throw new PostTagException("Tag id "+tagId+" tidak ditemukan");
        }
        return tag.get();
    }

    @Override
    public Tag add(RequestTag request) {
        List<Tag> tags = findByLabel(request.getLabel());
        if(tags.size() > 0){
            throw new PostTagException("Tag dengan label "+request.getLabel()+" sudah tersedia");
        }

        Tag tag = new Tag(request.getLabel());
        tagRepository.persist(tag);
        return tag;
    }

    @Override
    public Tag update(RequestTag request, long tagId) {
        Tag tag = findById(tagId);
        tag.setLabel(request.getLabel());
        tagRepository.persist(tag);
        return tag;
    }

    @Override
    public void delete(long tagId) {
        Tag tag = findById(tagId);
        tagRepository.delete(tag);
    }

}
