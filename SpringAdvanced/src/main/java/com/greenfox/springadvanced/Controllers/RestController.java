package com.greenfox.springadvanced.Controllers;

import com.greenfox.springadvanced.Models.DAO.MovieRetrofitDAO;
import com.greenfox.springadvanced.Models.DAO.RetrofitMovieInstance;
import com.greenfox.springadvanced.Models.DTOS.MovieDTO;
import com.greenfox.springadvanced.Models.Movie;
import com.greenfox.springadvanced.Repositories.MovieRepository;
import com.greenfox.springadvanced.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.io.IOException;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final MovieRepository movieRepository;
    private final MovieService movieService;

    @Autowired
    public RestController(MovieRepository movieRepository, MovieService movieService) {
        this.movieRepository = movieRepository;
        this.movieService = movieService;
    }

//    @GetMapping("/movie/{id}")
//    public ResponseEntity movies(@PathVariable Integer id) {
//        RestTemplate restTemplate = new RestTemplate();
//        Movie movie = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + id + "?api_key=7dc85399622b3c77fbb1fd1d9b5bcbbc", Movie.class);
//        movieRepository.save(movie);
//        return ResponseEntity.status(HttpStatus.OK).body(movie);
//    }

    @GetMapping("/movie/{id}")
    public ResponseEntity searchMovies(@PathVariable Integer id) throws IOException {
        Retrofit retrofitInstance = RetrofitMovieInstance.getRetrofitInstance();
        MovieRetrofitDAO movieRetrofitDAO = retrofitInstance.create(MovieRetrofitDAO.class);
        Call<Movie> a = movieRetrofitDAO.getMovie(id);
        Response<Movie> execute = a.execute();
        Movie movie = execute.body();
        movieRepository.save(movie);
        return ResponseEntity.status(HttpStatus.OK).body(movie);
    }

    @GetMapping ("/")
    public String hello(){
        return "<h1> hello User </h1>";
    }

    @GetMapping ("/hello")
    public String helloWorld(){
        return "<h1> hello World </h1>";
    }




}
