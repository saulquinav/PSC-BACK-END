package file.service.resource;

import file.service.dto.user.UserRegisterDTO;
import file.service.dto.user.UserReadingDTO;
import file.service.dto.user.UserPasswordUpdateDTO;
import file.service.service.UserService;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;


@Path("users")
@PermitAll // This annotation allows access to anybody, it's here only for testing purposes
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

    @POST
    public Response register(UserRegisterDTO dto)
    {
        service.register(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response changePassword(@PathParam("id") Long id, UserPasswordUpdateDTO dto)
    {
        Optional<UserReadingDTO> foundDTO = service.findById(id);

        if (foundDTO.isPresent())
        {
            service.updatePassword(dto);
            return Response.ok(dto).build();
        }
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}
