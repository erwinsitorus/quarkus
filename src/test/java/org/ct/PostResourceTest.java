package org.ct;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.ct.entity.Post;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class PostResourceTest {

    @Test
    public void getAll() {
        given().when().get("/api/post")
                .then()
                .statusCode(200);
    }

    @Test
    public void getById() {
        Post post = createPost();
        Post saved = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(post)
                .post("/api/post/add")
                .then()
                .statusCode(201)
                .extract().as(Post.class);

        Post got = given()
                .when().get("/api/post/{postId}", saved.getPostId())
                .then()
                .statusCode(200)
                .extract().as(Post.class);
        assertThat(saved).isEqualTo(got);
    }

    @Test
    public void post() {
        Post post = createPost();
        Post saved = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(post)
                .post("/api/post/add")
                .then()
                .statusCode(201)
                .extract().as(Post.class);
        assertThat(saved.getPostId()).isNotNull();
    }

    @Test
    public void postFailNoFirstName() {
        Post post = createPost();
        post.setContent(null);
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(post)
                .post("/api/post/add")
                .then()
                .statusCode(400);
    }

    @Test
    public void put() {
        Post post = createPost();
        Post saved = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(post)
                .post("/api/post/add")
                .then()
                .statusCode(201)
                .extract().as(Post.class);

        saved.setContent("Updated");
        Post updated = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(saved)
                .put("/api/post/"+saved.getPostId())
                .then()
                .statusCode(200)
                .extract().as(Post.class);
        assertThat(updated.getContent()).isEqualTo("Updated");
    }

    @Test
    private Post createPost() {
        Post post = new Post();
        post.setContent(RandomStringUtils.randomAlphabetic(10));
        post.setTitle(RandomStringUtils.randomAlphabetic(10));
        post.setTags(null);
        return post;
    }
}
