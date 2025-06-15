package inventory.tracking.resource;

import inventory.tracking.dto.user.UserRegisterDTO;
import inventory.tracking.dto.user.UserLoginDTO;
import inventory.tracking.service.UserService;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.util.Optional;


@Path("/auth")
@PermitAll
public class AuthResource
{
    @Inject
    private UserService userService;

    @POST
    @Path("/register")
    public Response register(UserRegisterDTO dto)
    {
        userService.register(dto);
        return Response.ok().build();
    }

    @POST
    @Path("/login")
    public Response login(UserLoginDTO dto)
    {
        Optional<String> token = userService.login(dto);

        if (token.isPresent())
        {
            return Response.ok(Json.createObjectBuilder()
                            .add("token", token.get()).build())
                            .build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
