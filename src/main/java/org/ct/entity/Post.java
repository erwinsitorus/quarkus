package org.ct.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_id_seq")
    @SequenceGenerator(name = "post_id_seq", sequenceName = "post_id_seq", allocationSize = 1, initialValue = 10)
    @Column(name = "post_id")
    private Long postId;

    private String title;
    private String content;

    @Column(name = "created_at", columnDefinition= "TIME WITH TIME ZONE")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date createdAt;

    @Column(name = "updated_at",columnDefinition= "TIME WITH TIME ZONE")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date updatedAt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "post_tag",
               joinColumns = {@JoinColumn(name = "post_id")},
               inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    List<Tag> tags;

    public Post() {
        this.createdAt = new Date();
        this.updatedAt = createdAt;
    }
}
