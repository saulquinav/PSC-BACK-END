package file.service.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import file.service.auth.JwtUtil;
import file.service.dto.user.LoginResponseDTO;
import file.service.dto.user.UserLoginDTO;
import file.service.entity.UserEntity;
import file.service.service.UserService;

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
            return Response.ok().entity(new LoginResponseDTO(token)).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
