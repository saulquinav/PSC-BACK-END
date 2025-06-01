package file.service.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import file.service.dto.UserDTO;
import file.service.service.UserService;

import java.util.List;
import java.util.Optional;

@Path("users")
public class UserResource
{
    @Inject
    private UserService userService;

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id)
    {
        Optional<UserDTO> foundUser = userService.findById(id);

        if (foundUser.isPresent())
            return Response.ok(foundUser.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getAll()
    {
        List<UserDTO> allUsers = userService.findAll();
        return Response.ok(allUsers).build();
    }

    @POST
    public Response create(UserDTO userDTO)
    {
        userService.create(userDTO);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UserDTO userDTO)
    {
        userService.update(userDTO);

        Optional<UserDTO> foundUser = userService.findById(id);

        if (foundUser.isPresent())
        {
            UserDTO user = foundUser.get();
            user.setUsername(userDTO.getUsername());

            userService.update(user);

            return Response.ok(user).build();
        }
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id)
    {
        userService.delete(id);
        return Response.ok().build();
    }
}
