package com.greenfox.springadvanced.Controllers;

import com.greenfox.springadvanced.Models.AuthenticationRequest;
import com.greenfox.springadvanced.Models.AuthenticationResponse;
import com.greenfox.springadvanced.Models.DAO.MovieRetrofitDAO;
import com.greenfox.springadvanced.Models.DAO.RetrofitMovieInstance;
import com.greenfox.springadvanced.Models.Movie;
import com.greenfox.springadvanced.Repositories.MovieRepository;
import com.greenfox.springadvanced.Security.JwtUtil;
import com.greenfox.springadvanced.Service.MovieService;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

@Import(SecurityConfig.class)

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final MovieRepository movieRepository;
    private final MovieService movieService;

    @Autowired
    private  AuthenticationManager authenticationManager;

    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    public RestController(MovieRepository movieRepository, MovieService movieService, UserDetailsService userDetailsService) {
        this.movieRepository = movieRepository;
        this.movieService = movieService;
        this.userDetailsService = userDetailsService;
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

    @PostMapping("/authenticate")
    public ResponseEntity createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try{
        authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e) {
            throw  new Exception("Incorrect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }



}
