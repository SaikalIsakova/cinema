package kg.mega.cinema.mappers;

import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public interface BaseMapper<Entity,Dto> {

    Entity toEntity(Dto dto);
    Dto toDto(Entity entity);

    List<Entity> toEntities(List<Dto>dtos);
    List<Dto>toDtos(List<Entity>entities);
}
