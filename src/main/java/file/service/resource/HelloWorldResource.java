package file.service.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("hello")
@PermitAll // This annotation allows access to anybody, it's here only for testing purposes
public class HelloWorldResource
{
    @GET
    public Response hello(@QueryParam("name") String name)
    {
        if ((name == null) || name.trim().isEmpty()) {
            name = "world";
        }
        return Response
                .ok(name)
                .build();
    }
}