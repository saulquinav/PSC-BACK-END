package inventory.tracking.resource;

import inventory.tracking.dto.inventoryitem.InventoryItemDTO;
import inventory.tracking.dto.userinfo.UserInfoDTO;
import inventory.tracking.service.InventoryItemService;
import inventory.tracking.service.UserInfoService;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.OPTIONS;

import java.util.List;
import java.util.Optional;

@Path("inventoryitems")
@PermitAll // This annotation allows access to anybody, it's here only for testing purposes
//@RolesAllowed("USER") // does not seem to work
public class InventoryItemResource
{
    @Inject
    private InventoryItemService service;

//    @OPTIONS
//    public Response handleOptions()
//    {
//        return Response.ok().build();
//    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id)
    {
        Optional<InventoryItemDTO> dto = service.findById(id);

        if (dto.isPresent())
            return Response.ok(dto.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getAll()
    {
        List<InventoryItemDTO> allDTOs = service.findAll();
        return Response.ok(allDTOs).build();
    }

    @POST
    public Response create(InventoryItemDTO dto)
    {
        service.create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, InventoryItemDTO dto)
    {
        Optional<InventoryItemDTO> foundDTO = service.findById(id);

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
