package file.service.converters;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/* E - Entity
** D - DTO */
public abstract class GenericConverter<E, D>
{
    public abstract D convertToNewDTO(E entity);
    public abstract E convertToNewEntity(D dto);

    public Set<E> convertAllToEntity(Set<D> dtos)
    {
        return dtos.stream()
                .map(dto -> convertToNewEntity(dto))
                .collect(Collectors.toSet());
    }

    public List<E> convertAllToEntity(List<D> dtos)
    {
        return dtos.stream()
                .map(dto -> convertToNewEntity(dto))
                .collect(Collectors.toList());
    }

    public Set<D> convertAllToDTO(Set<E> entities)
    {
        return entities.stream()
                .map(entity -> convertToNewDTO(entity))
                .collect(Collectors.toSet());
    }

    public List<D> convertAllToDTO(List<E> entities)
    {
        return entities.stream()
                .map(entity -> convertToNewDTO(entity))
                .collect(Collectors.toList());
    }
}
