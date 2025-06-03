package file.service.resource;

import file.service.service.CrudService;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

/* Type parameters:
** E - Entity
** ID - the ID class of the Entity
** D - DTO */
public abstract class CrudResource<E, ID, D>
{
    /* We use an abstract getter to provide a concrete CrudService because
    ** in this way all concrete subclasses of this class are required to implement
    ** this method.
    ** If we had used a setter which would set an internal instance, the user would
    ** not be forced to call that setter and, thus, the CrudService might remain null. */
    protected abstract CrudService<E, ID, D> getService();

    public Response get(ID id)
    {
        Optional<D> dto = getService().findById(id);

        if (dto.isPresent())
            return Response.ok(dto.get()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response getAll()
    {
        List<D> allDTOs = getService().findAll();
        return Response.ok(allDTOs).build();
    }

    public Response create(D dto)
    {
        getService().create(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    public Response update(ID id, D dto)
    {
        Optional<D> foundDTO = getService().findById(id);

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
