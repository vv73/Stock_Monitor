package com.example.stockmonitor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FinnhubService {
    @GET("api/{version}/stock/symbol")
    Call<List<Symbol>> listSymbols(
            @Path("version") String version,
             @Query("token") String token,
             @Query("exchange") String exch
             );

}
