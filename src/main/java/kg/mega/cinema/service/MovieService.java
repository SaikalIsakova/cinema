package kg.mega.cinema.service;


import kg.mega.cinema.models.dto.MovieDto;
import kg.mega.cinema.models.requests.SaveMovieRequest;

import java.util.List;

public interface MovieService extends BaseService<MovieDto>{
    MovieDto create(SaveMovieRequest movie);

    List<String> getAllMovies(int limit, int offset);
}
