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
        return Response.ok().build(); // does not work
//        return Response.ok().header("Access-Control-Allow-Credentials", "true").build(); // does not work
    }
}
