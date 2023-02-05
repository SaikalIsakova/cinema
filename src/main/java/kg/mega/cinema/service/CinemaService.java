package kg.mega.cinema.service;

import kg.mega.cinema.models.dto.CinemaDto;
import kg.mega.cinema.models.requests.CinemaRequest;

public interface CinemaService extends BaseService<CinemaDto>{
    CinemaDto create(CinemaRequest cinema);

}
