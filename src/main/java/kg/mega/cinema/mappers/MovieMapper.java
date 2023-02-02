package kg.mega.cinema.mappers;

import kg.mega.cinema.models.dto.MovieDto;
import kg.mega.cinema.models.entities.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper extends BaseMapper<Movie, MovieDto>{
    MovieMapper INSTANCE= Mappers.getMapper(MovieMapper.class);

}
