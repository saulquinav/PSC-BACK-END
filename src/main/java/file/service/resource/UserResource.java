package file.service.resource;

import file.service.dto.user.UserCreationDTO;
import file.service.dto.user.UserReadingDTO;
import file.service.dto.user.UserUpdateDTO;
import file.service.entity.UserEntity;
import file.service.service.CrudService;
import file.service.service.UserService;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;


@Path("users")
@PermitAll // This annotation allows access to anybody, it's here only for testing purposes
public class UserResource extends CrudResource<UserEntity, Long, UserCreationDTO, UserReadingDTO, UserUpdateDTO>
{
    @Inject
    private UserService userService;

    // Implement the only abstract method of CrudResource<E, ID, D> class
    @Override
    protected CrudService<UserEntity, Long, UserCreationDTO, UserReadingDTO, UserUpdateDTO> getService() { return userService; }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id)
    {
        return super.get(id); // delegate logic to base class method
    }

    @GET
    public Response getAll()
    {
        return super.getAll(); // delegate logic to base class method
    }

    @POST
    public Response create(UserCreationDTO dto)
    {
        return super.create(dto); // delegate logic to base class method
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UserUpdateDTO dto)
    {
        return super.update(id, dto); // delegate logic to base class method
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id)
    {
        return super.delete(id); // delegate logic to base class method
    }
}
