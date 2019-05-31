package com.example.attendanceqr;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Database {

    public static final String loginDomain = "http://mtlbl.xyz/loginst.php";
    public static final String attendanceDomain = "http://mtlbl.xyz/attend.php";

    private static Database mInstance;
    private final Context context;
    private RequestQueue queue;


    private Database(Context context){
        this.context = context;
        queue = getQueue();
    }

    public static synchronized Database getmInstance(Context context){
        if(mInstance == null) {
            mInstance = new Database(context);
        }

        return mInstance;
    }

    private RequestQueue getQueue(){
        if(queue == null) {
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }

        return queue;
    }

    private <T> void addQueue(Request<T> request){
        queue.add(request);
    }

    private boolean connectionCheck(){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState()
                == NetworkInfo.State.CONNECTED
                || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState()
                == NetworkInfo.State.CONNECTED;
    }

    public void execute(StringRequest request){
        if(connectionCheck()){
            getmInstance(context).addQueue(request);
        }
    }
}
