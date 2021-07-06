package org.ct.api;

import org.ct.dto.request.RequestTag;
import org.ct.entity.Tag;
import org.ct.service.TagService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("/api/tag")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TagResource {

    @Inject
    private TagService tagService;

    @GET
    public List<Tag> findAll(){
        return tagService.findAll();
    }

    @GET
    @Path("/{tagId}")
    public Tag get(@PathParam("tagId") Long tagId) {
        return tagService.findById(tagId);
    }

    @GET
    @Path("/search/{label}")
    public List<Tag>  search(@PathParam("label") String label) {
        return tagService.findByLabel(label);
    }

    @POST
    @Path("/add")
    public Response create(@Valid RequestTag request) {
        return Response.status(Response.Status.CREATED).entity(tagService.add(request)).build();
    }

    @PUT
    @Path("/{tagId}")
    public Tag update(@PathParam("tagId") Long tagId, RequestTag request) {
        return tagService.update(request, tagId);
    }

    @DELETE
    @Path("/{tagId}")
    public Response delete(@PathParam("tagId") Long tagId) {
        tagService.delete(tagId);
        return Response.status(204).build();
    }

}
