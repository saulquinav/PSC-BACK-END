package file.service.resource;

import file.service.dto.docmetadata.DocumentMetadataDTO;
import file.service.service.DocumentMetadataService;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("doc-metadatas")
@PermitAll // This annotation allows access to anybody, it's here only for testing purposes
public class DocumentMetadataResource
{
    @Inject
    private DocumentMetadataService service;

    @OPTIONS
//    @Path("{path : .*}")
    public Response handleOptions()
    {
        return Response.ok().build();
    }

//    @OPTIONS
//    @Path("{path : .*}")
//    public Response preflight() {
//        return Response.ok()
//                .header("Access-Control-Allow-Origin", "*")
////                .header("Access-Control-Allow-Origin", "http://localhost:3000")
//                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS")
//                .header("Access-Control-Allow-Headers", "Content-Type")
//                .build();
//    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id)
    {
        Optional<DocumentMetadataDTO> dto = service.findById(id);

        if (dto.isPresent())
            return Response.ok(dto.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getAll()
    {
        List<DocumentMetadataDTO> allDTOs = service.findAll();
        return Response.ok(allDTOs).build();
    }

    @POST
    public Response create(DocumentMetadataDTO dto)
    {
        service.create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, DocumentMetadataDTO dto)
    {
        Optional<DocumentMetadataDTO> foundDTO = service.findById(id);

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
