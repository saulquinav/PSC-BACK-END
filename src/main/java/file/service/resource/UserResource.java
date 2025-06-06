package file.service.resource;

import file.service.entity.UserEntity;
import file.service.service.CrudService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import file.service.dto.UserDTO;
import file.service.service.UserService;

@Path("users")
@PermitAll // This annotation allows access to anybody, it's here only for testing purposes
public class UserResource extends CrudResource<UserEntity, Long, UserDTO>
{
    @Inject
    private UserService userService;

    // Implement the only abstract method of CrudResource<E, ID, D> class
    @Override
    protected CrudService<UserEntity, Long, UserDTO> getService() { return userService; }

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
    public Response create(UserDTO dto)
    {
        return super.create(dto); // delegate logic to base class method
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UserDTO dto)
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
