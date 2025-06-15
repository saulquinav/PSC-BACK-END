package inventory.tracking.resource;

import jakarta.ws.rs.OPTIONS;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
public class PreflightResource
{
    @OPTIONS
    @Path("{path:.*}")
    public Response preflight()
    {
        return Response.ok().header("Access-Control-Allow-Credentials", "true").build(); // maybe works?
//        return Response.ok().build();
    }
}
