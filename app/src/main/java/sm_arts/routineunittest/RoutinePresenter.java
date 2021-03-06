package sm_arts.routineunittest;

import android.util.Log;

import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sm_arts.routineunittest.dto.RoutineItem;
import sm_arts.routineunittest.service.ApiService;

/**
 * Created by admin on 2017-11-15.
 */

public class RoutinePresenter {

    private RoutineAdapter mRoutineAdapter;
    private static final String TAG = "RoutinePresenter";
    private RoutineView mRoutineView;

    public RoutinePresenter(RoutineView mRoutineView, RoutineAdapter mRoutineAdapter) {
        this.mRoutineAdapter = mRoutineAdapter;
        this.mRoutineView = mRoutineView;
    }

    public void getRoutineData() {
        Log.d(TAG, "getRoutineData: ");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.79.180.194:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);
        Call<List<HashMap<String, Object>>> c = service.getMyTasks("59f1f63af6c5f35fd2d105cd");
        c.enqueue(new Callback<List<HashMap<String, Object>>>() {
            @Override
            public void onResponse(Call<List<HashMap<String, Object>>> call, Response<List<HashMap<String, Object>>> response) {
                List<RoutineItem> routineItems = new ArrayList<RoutineItem>();
                for (int i = 0; i < response.body().size(); i++) {
                    HashMap<String, Object> result = response.body().get(i);
                    RoutineItem routineItem = new RoutineItem();
                    routineItem.data = (LinkedTreeMap<String, Object>) result.get("data");
                    routineItem._id = (String) result.get("_id");
                    routineItem.task_type = (String) result.get("task_type");
                    routineItem.time_id = (String) result.get("time_id");
                    routineItem.userId = (String) result.get("userId");
                    routineItems.add(routineItem);
                }

                mRoutineAdapter.setItems(routineItems);
                mRoutineView.onDataDownloadFinished();
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<List<HashMap<String, Object>>> call, Throwable t) {
                mRoutineView.onDataDownloadFinished();
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public void addTask() {
        RoutineItem routineItem = new RoutineItem();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.79.180.194:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);

        routineItem.userId = "59f1f63af6c5f35fd2d105cd";
        routineItem.hour = "21";
        routineItem.minute = "9";
        routineItem.task_type = "message";
        routineItem.data = new LinkedTreeMap<>();
        routineItem.data.put("text", "send test");
        Call<Void> c = service.addTask(routineItem);
        c.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: ");

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
        getRoutineData();
    }

    public void deleteRoutineItem(RoutineItem routineItem) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://52.79.180.194:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        Call<Void> c = service.deleteTask(routineItem._id);
        c.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG, "onResponse: ");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });

        getRoutineData();


    }
}
