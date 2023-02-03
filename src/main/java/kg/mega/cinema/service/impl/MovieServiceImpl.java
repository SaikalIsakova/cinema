package kg.mega.cinema.service.impl;

import kg.mega.cinema.dao.MovieRep;
import kg.mega.cinema.mappers.MovieMapper;
import kg.mega.cinema.models.dto.MovieDto;
import kg.mega.cinema.models.requests.SaveMovieRequest;
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
    public MovieDto create(SaveMovieRequest movie) {
        MovieDto movieDto = new MovieDto();
        movieDto.setName(movie.getName());
        movieDto.setDefinition(movie.getDefinition());
        movieDto.setRating(movie.getRating());
        movieDto.setPg(movie.getPg());
        movieDto.setImage(movie.getImage());

        return save(movieDto);
    }


    @Override
    public List<String> getPaginationResult(int limit, int offset) {
        List<MovieDto> movieList = mapper.toDtos(rep.getPaginationResult(limit,offset));
        List<String> allMovieList = new ArrayList<>();
        for (MovieDto item:movieList){
            allMovieList.add("ID="+item.getId()+", "+item.getName()+", "+item.getImage()+ ", " +item.getPg());
        }
        return allMovieList;
    }

    @Override
    public MovieDto findById(Long id) {
        return mapper.toDto(rep.findById(id).orElseThrow(() -> new RuntimeException("Movie not found!")));
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
