package com.greenfox.springadvanced.Models.DAO;


import com.greenfox.springadvanced.Models.DTOS.MovieDTO;
import com.greenfox.springadvanced.Models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface MovieRetrofitDAO {

    @GET("3/movie/{id}?api_key=7dc85399622b3c77fbb1fd1d9b5bcbbc")
    Call<Movie> getMovie(@Path("id") Integer id);
}
