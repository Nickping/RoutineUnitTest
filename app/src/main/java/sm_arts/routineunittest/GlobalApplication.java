package sm_arts.routineunittest;

import android.app.Application;

/**
 * Created by admin on 2017-11-16.
 */

public class GlobalApplication extends Application {

    public static GlobalApplication sObj = null;

    public static GlobalApplication getGlobalApplication()
    {
        return sObj;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sObj = this;
    }
}
