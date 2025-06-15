package inventory.tracking.resource;

import inventory.tracking.dto.user.UserRegisterDTO;
import inventory.tracking.dto.user.UserLoginDTO;
import inventory.tracking.service.UserService;

import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;


@Path("/auth")
public class AuthResource
{
    @Inject
    private UserService userService;

    @POST
    @Path("/register")
    public Response register(UserRegisterDTO user)
    {
        userService.register(user);
        return Response.ok().build();
    }

    @POST
    @Path("login")
    public Response login(UserLoginDTO userLoginDTO)
    {
        String token = userService.login(userLoginDTO);

        if (token != null)
        {
            return Response.ok(Json.createObjectBuilder()
                            .add("token", token).build())
                            .build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
