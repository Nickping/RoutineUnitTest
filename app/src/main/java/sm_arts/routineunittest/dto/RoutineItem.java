package sm_arts.routineunittest.dto;

import com.google.gson.internal.LinkedTreeMap;

/**
 * Created by admin on 2017-11-15.
 */

public class RoutineItem {
    public String _id;
    public String time_id;
    public String task_type;
    public String userId;
    public LinkedTreeMap<String,Object> data;
    public String hour;
    public String minute;

}
