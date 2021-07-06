package org.ct.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Date;

@Provider
public class ApplicationExceptionHandler  implements ExceptionMapper<PostTagException>
{
    @Override
    public Response toResponse(PostTagException exception)
    {
        return Response.status(Status.BAD_REQUEST).entity(new ExceptionResponse(Status.BAD_REQUEST.getStatusCode(),
                                                            new Date(), exception.getMessage(), "")).build();
    }
}