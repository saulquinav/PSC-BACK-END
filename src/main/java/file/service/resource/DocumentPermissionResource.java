package file.service.resource;

import file.service.dto.docpermission.DocumentPermissionDTO;
import file.service.entity.DocumentPermissionId;
import file.service.service.DocumentPermissionService;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("document-permissions")
@PermitAll // This annotation allows access to anybody, it's here only for testing purposes
public class DocumentPermissionResource
{
    @Inject
    private DocumentPermissionService service;

    @GET
    @Path("/{id}")
    public Response get(@PathParam("userId") Long userId, @PathParam("documentId") Long documentId)
    {
        // Reconstruct composite ID from its simple IDs
        DocumentPermissionId id = new DocumentPermissionId(userId, documentId);

        Optional<DocumentPermissionDTO> dto = service.findById(id);

        if (dto.isPresent())
            return Response.ok(dto.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getAll()
    {
        List<DocumentPermissionDTO> allDTOs = service.findAll();
        return Response.ok(allDTOs).build();
    }

    @POST
    public Response create(DocumentPermissionDTO dto)
    {
        service.create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("userId") Long userId, @PathParam("documentId") Long documentId, DocumentPermissionDTO dto)
    {
        // Reconstruct composite ID from its simple IDs
        DocumentPermissionId id = new DocumentPermissionId(userId, documentId);

        Optional<DocumentPermissionDTO> foundDTO = service.findById(id);

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
    public Response delete(@PathParam("userId") Long userId, @PathParam("documentId") Long documentId)
    {
        // Reconstruct composite ID from its simple IDs
        DocumentPermissionId id = new DocumentPermissionId(userId, documentId);

        service.delete(id);
        return Response.ok().build();
    }
}
