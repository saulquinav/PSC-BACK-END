package inventory.tracking.resource;

import inventory.tracking.dto.user.UserRegisterDTO;
import inventory.tracking.dto.user.UserReadingDTO;
import inventory.tracking.dto.user.UserUpdateDTO;
import inventory.tracking.service.UserService;

import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;


@Path("users")
@PermitAll // This annotation allows access to anybody, it's here only for testing purposes
//@RolesAllowed("USER")
public class UserResource
{
    @Inject
    private UserService service;

    @OPTIONS
    public Response handleOptions()
    {
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id)
    {
        Optional<UserReadingDTO> dto = service.findById(id);

        if (dto.isPresent())
            return Response.ok(dto.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getAll()
    {
        List<UserReadingDTO> allDTOs = service.findAll();
        return Response.ok(allDTOs).build();
    }

    @PUT
    @Path("/{id}")
    public Response editUser(@PathParam("id") Long id, UserUpdateDTO dto)
    {
        Optional<UserReadingDTO> foundDTO = service.findById(id);

        if (foundDTO.isPresent())
        {
            service.update(dto);
            return Response.ok(dto).build();
        }
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id)
    {
        service.delete(id);
        return Response.ok().build();
    }
}
