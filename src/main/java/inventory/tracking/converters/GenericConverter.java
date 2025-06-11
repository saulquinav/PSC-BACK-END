package inventory.tracking.converters;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/* E - Entity
** D - DTO */
public abstract class GenericConverter<E, D>
{
    public abstract D convertToDTO(E entity);
    public abstract E convertToEntity(D dto);
    public abstract E convertToEntityWithoutId(D dto);

    public Set<E> convertAllToEntity(Set<D> dtos)
    {
        return dtos.stream()
                .map(dto -> convertToEntity(dto))
                .collect(Collectors.toSet());
    }

    public List<E> convertAllToEntity(List<D> dtos)
    {
        return dtos.stream()
                .map(dto -> convertToEntity(dto))
                .collect(Collectors.toList());
    }

    public Set<D> convertAllToDTO(Set<E> entities)
    {
        return entities.stream()
                .map(entity -> convertToDTO(entity))
                .collect(Collectors.toSet());
    }

    public List<D> convertAllToDTO(List<E> entities)
    {
        return entities.stream()
                .map(entity -> convertToDTO(entity))
                .collect(Collectors.toList());
    }
}
