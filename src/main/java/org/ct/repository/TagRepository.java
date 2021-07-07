package org.ct.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.ct.entity.Tag;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Locale;

@ApplicationScoped
public class TagRepository implements PanacheRepository<Tag> {

    public List<Tag> findByTagIds(List<Long> tagId) {
        return list("SELECT t FROM Tag t WHERE t.tagId in ?1", tagId);
    }

    public List<Tag> findByLabel(String label){
        return list("SELECT t FROM Tag t WHERE upper(t.label) like ?1", label.toUpperCase(Locale.ROOT));
    }
}
