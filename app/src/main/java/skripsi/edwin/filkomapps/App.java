package skripsi.edwin.filkomapps;

import android.app.Application;
import android.content.Context;

import com.google.firebase.iid.FirebaseInstanceId;

public class App extends Application{
    private static boolean IsMainActivityOpen = false;
    private static App mInstance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseInstanceId.getInstance().getToken();

    }


    public static App getInstance(Context context) {
        return (App) context.getApplicationContext();
    }

    public static App getInstance() {
        if (mInstance == null) {
            mInstance = new App();
        }
        return mInstance;
    }
    public void setMaintActivityOpen(boolean isMainActivityOpen) {
        this.IsMainActivityOpen = isMainActivityOpen;
    }

    public static boolean getIsMainActivityOpen() {
        return IsMainActivityOpen;
    }




}
