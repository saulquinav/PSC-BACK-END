package file.service.resource;

import file.service.dto.DocumentPermissionDTO;
import file.service.dto.UserDTO;
import file.service.entity.DocumentPermissionEntity;
import file.service.entity.DocumentPermissionId;
import file.service.service.CrudService;
import file.service.service.DocumentPermissionService;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("document-permissions")
@PermitAll // This annotation allows access to anybody, it's here only for testing purposes
public class DocumentPermissionResource extends CrudResource<DocumentPermissionEntity, DocumentPermissionId, DocumentPermissionDTO>
{
    @Inject
    private DocumentPermissionService documentPermissionService;

    // Implement the only abstract method of CrudResource<E, ID, D> class
    @Override
    protected CrudService<DocumentPermissionEntity, DocumentPermissionId, DocumentPermissionDTO> getService()
    {
        return documentPermissionService;
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") DocumentPermissionId id)
    {
        return super.get(id); // delegate logic to base class method
    }

    @GET
    public Response getAll()
    {
        return super.getAll(); // delegate logic to base class method
    }

    @POST
    public Response create(DocumentPermissionDTO dto)
    {
        return super.create(dto); // delegate logic to base class method
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") DocumentPermissionId id, DocumentPermissionDTO dto)
    {
        return super.update(id, dto); // delegate logic to base class method
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") DocumentPermissionId id)
    {
        return super.delete(id); // delegate logic to base class method
    }
}
