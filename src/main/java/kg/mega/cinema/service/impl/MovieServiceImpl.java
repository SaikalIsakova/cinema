package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.MovieRep;
import kg.mega.cinema.exceptions.MovieNotFoundException;
import kg.mega.cinema.mappers.MovieMapper;
import kg.mega.cinema.models.dto.MovieDto;
import kg.mega.cinema.models.requests.MovieRequest;
import kg.mega.cinema.models.responses.MovieResponse;
import kg.mega.cinema.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    MovieMapper mapper = MovieMapper.INSTANCE;

    private final MovieRep rep;

    public MovieServiceImpl(MovieRep rep) {

        this.rep = rep;
    }

    @Override
    public MovieDto save(MovieDto movieDto) {

        return mapper.toDto(rep.save(mapper.toEntity(movieDto)));
    }

    @Override
    public MovieDto create(MovieRequest movie) {

        MovieDto movieDto = new MovieDto();
        movieDto.setName(movie.getName());
        movieDto.setDefinition(movie.getDefinition());
        movieDto.setRating(movie.getRating());
        movieDto.setPg(movie.getPg());
        movieDto.setImage(movie.getImage());

        return save(movieDto);
    }


    @Override
    public List<MovieResponse> getPaginationResult(int limit, int offset) {

        List<MovieDto> movieList = mapper.toDtos(rep.getPaginationResult(limit,offset));

        List<MovieResponse> getAllMoviesResponseList = new ArrayList<>();


        for (MovieDto item:movieList){

            MovieResponse getAllMoviesResponse= new MovieResponse();

            getAllMoviesResponse.setId(item.getId());
            getAllMoviesResponse.setDefinition(item.getDefinition());
            getAllMoviesResponse.setName(item.getName());
            getAllMoviesResponse.setRating(item.getRating());
            getAllMoviesResponse.setPg(item.getPg());
            getAllMoviesResponse.setImage(item.getImage());
            getAllMoviesResponseList.add(getAllMoviesResponse);

        }

        return getAllMoviesResponseList ;
    }

    @Override
    public List<MovieResponse> findAllMovies() {

        List<MovieDto>allMovies=findAll();

        List<MovieResponse>movieList=new ArrayList<>();

        for (MovieDto item:allMovies){

            MovieResponse movie= new MovieResponse();
            movie.setId(item.getId());
            movie.setDefinition(item.getDefinition());
            movie.setName(item.getName());
            movie.setRating(item.getRating());
            movie.setPg(item.getPg());
            movie.setImage(item.getImage());
            movieList.add(movie);

        }

        return movieList;
    }

    @Override
    public MovieDto findById(Long id) {

        return mapper.toDto(rep.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie not found!")));
    }

    @Override
    public MovieDto delete(Long id) {

        MovieDto movieDto = findById(id);
        movieDto.setActive(false);

        return save(movieDto);
    }

    @Override
    public List<MovieDto> findAll() {

        return mapper.toDtos(rep.findAll());
    }
}
