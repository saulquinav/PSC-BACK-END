package inventory.tracking.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import inventory.tracking.auth.JwtUtil;
import inventory.tracking.dto.user.UserLoginResponseDTO;
import inventory.tracking.dto.user.UserLoginDTO;
import inventory.tracking.entity.UserEntity;
import inventory.tracking.service.UserService;

import java.util.Optional;


@Path("/auth")
public class AuthResource
{
    @Inject
    private UserService userService;

    @POST
    @Path("login")
    public Response login(UserLoginDTO userLoginDTO)
    {
        Optional<UserEntity> userOpt = userService.authenticate(userLoginDTO);

        if (userOpt.isPresent())
        {
            String token = JwtUtil.generateToken(userOpt.get());
//            return Response.ok(Collections.singletonMap("token", token)).build();
            return Response.ok().entity(new UserLoginResponseDTO(token)).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
