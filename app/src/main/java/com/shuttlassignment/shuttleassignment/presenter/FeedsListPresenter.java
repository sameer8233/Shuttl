package com.shuttlassignment.shuttleassignment.presenter;

import android.app.Activity;
import android.content.Context;

import com.google.gson.Gson;
import com.shuttlassignment.shuttleassignment.view.FeedActivity;
import com.shuttlassignment.shuttleassignment.modal.pojo.FeedListPojo;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Sameer Yadav on 11-10-2017.
 */

public class FeedsListPresenter {

    private FeedListPojo feedsList;
    private Context context;


    private void loadData()
    {
        /* Loading data from json file*/
        /*We can create server call from here also*/
        feedsList = new Gson().fromJson(loadJSONFromAsset(),FeedListPojo.class);

        /* after parsing data we are publishing our data to activity for setting up the views*/
        publishData((Activity) context);
    }


    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("feeds.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }



    public void callPresenter(Activity context) {

        this.context = context;
        loadData();
    }

    /*
    * we can do multiple checks here like activity is still alive or not etc
    * which can help use in preventing memory leak.
    * */

    public void publishData(Activity context) {
        if (context != null) {

            // sending data to activity
            if(context instanceof FeedActivity)
            {
                ((FeedActivity) context).dataFromServer(feedsList);

            }

        }

    }



}
