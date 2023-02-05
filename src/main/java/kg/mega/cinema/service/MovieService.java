package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.MovieDto;
import kg.mega.cinema.models.requests.MovieRequest;

import java.util.List;

public interface MovieService extends BaseService<MovieDto>{
    MovieDto create(MovieRequest movie);

    List<String> getPaginationResult(int limit, int offset);
}
