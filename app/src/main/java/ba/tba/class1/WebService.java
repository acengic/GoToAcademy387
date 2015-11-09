package ba.tba.class1;


import android.util.Log;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;


import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Converter;
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
        Call<List<Arrival>> getNext(
                @Query("start") String fromStation,
                @Query("end") String toStation,
                @Query("time") String time);

    }


    static public Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://sleepy-river-8228.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    static public    GoToApiEndpointInterface apiService =
                retrofit.create(GoToApiEndpointInterface.class);

    }


