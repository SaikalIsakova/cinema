package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.MovieDto;
import kg.mega.cinema.models.requests.MovieRequest;
import kg.mega.cinema.models.responses.MovieResponse;

import java.util.List;

public interface MovieService extends BaseService<MovieDto>{
    MovieDto create(MovieRequest movie);

    List<MovieResponse> getPaginationResult(int limit, int offset);

    List<MovieResponse>findAllMovies();
}
