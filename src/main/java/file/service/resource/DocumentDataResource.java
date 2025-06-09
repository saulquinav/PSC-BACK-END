package file.service.resource;

import file.service.dto.docdata.DocumentDataDTO;
import file.service.service.DocumentDataService;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("doc-datas")
@PermitAll // This annotation allows access to anybody, it's here only for testing purposes
public class DocumentDataResource
{
    @Inject
    private DocumentDataService service;

    @OPTIONS
    public Response handleOptions()
    {
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id)
    {
        Optional<DocumentDataDTO> dto = service.findById(id);

        if (dto.isPresent())
            return Response.ok(dto.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getAll()
    {
        List<DocumentDataDTO> allDTOs = service.findAll();
        return Response.ok(allDTOs).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON) // explicitly tell Jakarta which content type to use
    public Response create(DocumentDataDTO dto)
    {
        service.create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON) // explicitly tell Jakarta which content type to use
    public Response update(@PathParam("id") Long id, DocumentDataDTO dto)
    {
        Optional<DocumentDataDTO> foundDTO = service.findById(id);

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
