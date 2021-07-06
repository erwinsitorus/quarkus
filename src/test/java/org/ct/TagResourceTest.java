package org.ct;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.ct.entity.Tag;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class TagResourceTest {

    private final Logger logger = LoggerFactory.getLogger(TagResourceTest.class);

    @Test
    public void getAll() {
        given().when().get("/api/tag")
                .then()
                .statusCode(200);
    }

    @Test
    public void getById() {
        Tag tag = createTag();
        Tag saved = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(tag)
                .post("/api/tag/add")
                .then()
                .statusCode(201)
                .extract().as(Tag.class);


        Tag got = given()
                .when().get("/api/tag/{tagId}", saved.getTagId())
                .then()
                .statusCode(200)
                .extract().as(Tag.class);

        assertThat(saved.getTagId()).isEqualTo(got.getTagId());
    }

    @Test
    public void post() {
        Tag tag = createTag();
        Tag saved = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(tag)
                .post("/api/tag/add")
                .then()
                .statusCode(201)
                .extract().as(Tag.class);
        assertThat(saved.getTagId()).isNotNull();
    }

    @Test
    public void postFailNoFirstName() {
        Tag post = createTag();
        post.setLabel(null);
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(post)
                .post("/api/tag/add")
                .then()
                .statusCode(400);
    }

    @Test
    public void put() {
        Tag tag = createTag();
        Tag saved = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(tag)
                .post("/api/tag/add")
                .then()
                .statusCode(201)
                .extract().as(Tag.class);

        saved.setLabel("Updated");
        Tag updated = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(saved)
                .put("/api/tag/"+saved.getTagId())
                .then()
                .statusCode(200)
                .extract().as(Tag.class);
        assertThat(updated.getLabel()).isEqualTo("Updated");
    }

    @Test
    private Tag createTag() {
        Tag tag = new Tag();
        tag.setLabel(RandomStringUtils.randomAlphabetic(10));
        return tag;
    }

}
