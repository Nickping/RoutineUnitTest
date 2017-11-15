package sm_arts.routineunittest.service;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by admin on 2017-11-15.
 */

public interface ApiService {

    @POST("/api/timer/getMyTasks")
    Call<List<HashMap<String,Object>>> getMyTasks(@Header("Authorization") String userId);

}
