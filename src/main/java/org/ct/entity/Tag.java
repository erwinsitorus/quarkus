package org.ct.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tag")
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tag_id_seq")
    @SequenceGenerator(name = "tag_id_seq", sequenceName = "tag_id_seq", allocationSize = 1, initialValue = 10)
    @Column(name = "tag_id")
    long tagId;

    private String label;

    @Column(name = "created_at", columnDefinition= "TIME WITH TIME ZONE")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date createdAt;

    @Column(name = "updated_at",columnDefinition= "TIME WITH TIME ZONE")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    Date updatedAt;

    @ManyToMany(mappedBy = "tags")
    List<Post> posts;

    public Tag(String label) {
        this.createdAt = new Date();
        this.label = label;
        this.updatedAt = createdAt;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @JsonbTransient
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
