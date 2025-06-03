package file.service.resource;

import file.service.dto.UserInfoDTO;
import file.service.entity.UserInfoEntity;
import file.service.service.CrudService;
import file.service.service.UserInfoService;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("user-infos")
@PermitAll // This annotation allows access to anybody, it's here only for testing purposes
public class UserInfoResource extends CrudResource<UserInfoEntity, Long, UserInfoDTO>
{
    @Inject
    private UserInfoService userInfoService;

    // Implement the only abstract method of CrudResource<E, ID, D> class
    @Override
    protected CrudService<UserInfoEntity, Long, UserInfoDTO> getService() { return userInfoService; }

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
    public Response create(UserInfoDTO dto)
    {
        return super.create(dto); // delegate logic to base class method
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, UserInfoDTO dto)
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
