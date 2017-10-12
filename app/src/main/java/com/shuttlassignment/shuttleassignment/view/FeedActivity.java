package com.shuttlassignment.shuttleassignment.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.shuttlassignment.shuttleassignment.R;
import com.shuttlassignment.shuttleassignment.adapter.FeedsListAdapter;
import com.shuttlassignment.shuttleassignment.modal.pojo.FeedListPojo;
import com.shuttlassignment.shuttleassignment.modal.pojo.Feeds;
import com.shuttlassignment.shuttleassignment.presenter.FeedsListPresenter;
import com.shuttlassignment.shuttleassignment.util.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FeedActivity extends AppCompatActivity {

    private RecyclerView feeds_recyclerview;
    private FeedsListAdapter feedsListAdapter;
    private FeedsListPresenter feedsListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        /*Initialisation of id's*/
        init();
    }

    private void init() {
        feeds_recyclerview = (RecyclerView) findViewById(R.id.feeds_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        feeds_recyclerview.setLayoutManager(linearLayoutManager);
        feedsListAdapter = new FeedsListAdapter(this);
        feeds_recyclerview.setAdapter(feedsListAdapter);

        /*fetching data from json file*/
        feedsListPresenter = new FeedsListPresenter();
        feedsListPresenter.callPresenter(this);

    }

    /*
    Function which is have data
    coming from server or any other resource
    */

    public void dataFromServer(FeedListPojo data) {

        if (data.getFeedsList() != null && data.getFeedsList().length > 0)
            feedsListAdapter.setData(modifyList(Arrays.asList(data.getFeedsList())));
        else
            Toast.makeText(getApplicationContext(), "No Feeds to show", Toast.LENGTH_SHORT).show();
    }


    /*
    * Grouping of feed's date wise
    * Here we can do this in many ways by creating map etc.
    * but i have user a single list of do this which is having the complexity of
    * O(n), this can be reduced also
    *
    * */

    private List<Feeds> modifyList(List<Feeds> list) {
        List<Feeds> newlist = new ArrayList<>();
        String previous_time = "";
        for (Feeds f : list) {
            if (previous_time.equals("") || !previous_time.equals(f.getTime())) {
                newlist.add(new Feeds(Utils.getDateFromTimeStamp(Long.parseLong(f.getTime())), null, "Date", null, null, null));
                newlist.add(f);
                previous_time = f.getTime();

            } else {
                newlist.add(f);
            }

        }

        return newlist;
    }


    /* It is taking the result from the feed detail activity
    * We have marked data dirty in feed detail activity
     * if any this is changed and will update the feed list \
    * accordingly */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            feedsListAdapter.refreshLikeDislike(data.getIntExtra("position", 0), data.getBooleanExtra("is_liked", false));
        }

        // we have reject the result for non dirty marked data.

    }
}
