package org.ct.api;


import org.ct.entity.Post;
import org.ct.dto.request.RequestPost;
import org.ct.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/post")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostResource {

    private final Logger LOGGER = LoggerFactory.getLogger(PostResource.class);

    @Inject
    private PostService postService;

    @GET
    public Response get() {
        return Response.ok(postService.findAll()).build();
    }

    @GET
    @Path("/{postId}")
    public Post get(@PathParam("postId") Long postId) {
        return postService.findById(postId);
    }

    @POST
    @Path("/add")
    public Response create(@Valid RequestPost request) {
        return Response.status(Response.Status.CREATED).entity(postService.add(request)).build();
    }

    @PUT
    @Path("/{postId}")
    public Post update(@PathParam("postId") Long postId, RequestPost request) {
        return postService.update(request, postId);
    }

    @DELETE
    @Path("/{postId}")
    public Response delete(@PathParam("postId") Long postId) {
        postService.delete(postId);
        return Response.status(204).build();
    }
}
