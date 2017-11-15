package sm_arts.routineunittest;

import com.google.gson.internal.LinkedTreeMap;

import sm_arts.routineunittest.dto.RoutineItem;

/**
 * Created by admin on 2017-11-15.
 */

public class ConverUtils {
    private static final String TAG = "ConverUtils";


    public static RoutineItem convertRoutineItem(LinkedTreeMap<String,Object> item) {
        RoutineItem routineItem = new RoutineItem();
        routineItem.data = (LinkedTreeMap<String,Object>)item.get("data");
        return routineItem;
    }
}
