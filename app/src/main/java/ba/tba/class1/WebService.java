package ba.tba.class1;

import java.util.List;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by jackblack on 11/3/15.
 */
public class WebService {
    public interface GoToApiEndpointInterface {

        @GET("/stations.json")
        Call<List<Station>> getStationList();

        @GET("/next_lines.json")
        Call<List<Next>> getNext(
                @Query("start") String fromStation,
                @Query("end") String toStation,
                @Query("time") String time);
    }


    static public    Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://sleepy-river-8228.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    static public    GoToApiEndpointInterface apiService =
                retrofit.create(GoToApiEndpointInterface.class);

    }
