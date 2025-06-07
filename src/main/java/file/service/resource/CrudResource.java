package file.service.resource;

import file.service.service.CrudService;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

/* Type parameters:
 ** E - entity
 ** ID - the id of the Entity
 ** DC - DTO for Creation
 ** DD - DTO for Reading
 ** DU - DTO for Update */
public abstract class CrudResource<E, ID,  DC, DR, DU>
{
    /* We use an abstract getter to provide a concrete CrudService because
    ** in this way all concrete subclasses of this class are required to implement
    ** this method.
    ** If we had used a setter which would set an internal instance, the user would
    ** not be forced to call that setter and, thus, the CrudService might remain null. */
    protected abstract CrudService<E, ID,  DC, DR, DU> getService();

    public Response get(ID id)
    {
        Optional<DR> dto = getService().findById(id);

        if (dto.isPresent())
            return Response.ok(dto.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response getAll()
    {
        List<DR> allDTOs = getService().findAll();
        return Response.ok(allDTOs).build();
    }

    public Response create(DC dto)
    {
        getService().create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    public Response update(ID id, DU dto)
    {
        Optional<DR> foundDTO = getService().findById(id);

        if (foundDTO.isPresent())
        {
            getService().update(dto);
            return Response.ok(dto).build();
        }
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response delete(ID id)
    {
        getService().delete(id);
        return Response.ok().build();
    }
}
