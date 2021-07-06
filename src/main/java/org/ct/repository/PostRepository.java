package org.ct.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.ct.entity.Post;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {
}
