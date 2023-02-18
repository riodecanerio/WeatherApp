package Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather?appid=99dff54488e9b5c4034f3d0cded85fd9&units=metric")
    Call<Example> getWeatherData(@Query("q") String name);

}
