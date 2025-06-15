package inventory.tracking.resource;

import inventory.tracking.dto.inventorylog.InventoryLogDTO;
import inventory.tracking.service.InventoryLogService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("inventorylogs")
//@PermitAll // This annotation allows access to anybody, it's here only for testing purposes
@RolesAllowed("USER")
public class InventoryLogResource
{
    @Inject
    private InventoryLogService service;

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id)
    {
        Optional<InventoryLogDTO> dto = service.findById(id);

        if (dto.isPresent())
            return Response.ok(dto.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getAll()
    {
        List<InventoryLogDTO> allDTOs = service.findAll();
        return Response.ok(allDTOs).build();
    }

    @POST
    public Response create(InventoryLogDTO dto)
    {
        service.create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, InventoryLogDTO dto)
    {
        Optional<InventoryLogDTO> foundDTO = service.findById(id);

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
