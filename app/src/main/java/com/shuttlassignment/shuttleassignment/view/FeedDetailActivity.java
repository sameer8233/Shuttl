package com.shuttlassignment.shuttleassignment.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shuttlassignment.shuttleassignment.R;
import com.shuttlassignment.shuttleassignment.modal.pojo.Feeds;
import com.shuttlassignment.shuttleassignment.util.Utils;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static com.bumptech.glide.request.RequestOptions.overrideOf;

public class FeedDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView feed_header, feed_date, description, feed_quote, feed_text, name_detail;
    private ImageView feed_profile_pic, feed_content_pic;
    private Button like_dislike;
    private Feeds feed_data;
    private boolean dirty_like_dislike = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_detail);

        /*
        * Initialisation of id's
        * */

        init();

    }

    private void init() {
        feed_header = (TextView) findViewById(R.id.feed_header);
        feed_date = (TextView) findViewById(R.id.feed_date);
        feed_text = (TextView) findViewById(R.id.feed_text);
        feed_quote = (TextView) findViewById(R.id.txt_quote_details);
        name_detail = (TextView) findViewById(R.id.txt_name_details);
        description = (TextView) findViewById(R.id.txt_desc_long_details);
        feed_profile_pic = (ImageView) findViewById(R.id.img_people_details);
        feed_content_pic = (ImageView) findViewById(R.id.img_place_details);
        like_dislike = (Button) findViewById(R.id.like_dislike_button);
        like_dislike.setOnClickListener(this);


        setupViews();
    }

    private void setupViews() {

        /*Setting up the views from intent data*/

        /* used some hard coding for radius and text we can create a constant file and we can maintain them in that*/

        feed_data = (Feeds) getIntent().getSerializableExtra("feed");

        feed_header.setText(feed_data.getTitle());
        feed_date.setText(Utils.getDateFromTimeStamp(Long.parseLong(feed_data.getTime())));
        description.setText(feed_data.getDescription());
        name_detail.setText("From " + feed_data.getName());

        if (feed_data.is_liked()) {
            like_dislike.setText("Unlike");
            dirty_like_dislike = true;
        } else {
            like_dislike.setText("Like");
            dirty_like_dislike = false;
        }
        setColorForButton();

        /*placeholder for images which could not load or take time to load */

        if (feed_data.getTitle().equals("Place")) {
            Glide.with(this)
                    .load(feed_data.getImageUrl())
                    .apply(overrideOf(200, 200))
                    .apply(bitmapTransform(new RoundedCornersTransformation(10, 10, RoundedCornersTransformation.CornerType.ALL)))
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.ic_launcher))
                    .apply(new RequestOptions()
                            .error(R.mipmap.ic_launcher))
                    .into(feed_content_pic);

        }

        if (feed_data.getTitle().equals("People")) {
            Glide.with(this)
                    .load(feed_data.getImageUrl())
                    .apply(overrideOf(200, 200))
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.ic_launcher))
                    .apply(new RequestOptions()
                            .error(R.mipmap.ic_launcher))
                    .apply(bitmapTransform(new RoundedCornersTransformation(10, 10, RoundedCornersTransformation.CornerType.ALL)))
                    .into(feed_profile_pic);

            feed_text.setText(feed_data.getText());
            feed_content_pic.setVisibility(View.GONE);

        }

        if (feed_data.getTitle().equals("Quote")) {

            feed_quote.setText(feed_data.getText());
            feed_content_pic.setVisibility(View.GONE);
        }

    }


    public void setResult() {

        /*Setting up the data dirty if their is anything change in temp_like_dislike
        * If no changed detect in previous state of data then we are cancelling the result
        * */

        if (feed_data.is_liked() != dirty_like_dislike) {
            Intent i = new Intent();
            i.putExtra("position", getIntent().getIntExtra("position", 0));
            i.putExtra("is_liked", dirty_like_dislike);
            setResult(RESULT_OK, i);
        } else {
            setResult(RESULT_CANCELED, null);
        }
    }

    private void setColorForButton() {

        if(dirty_like_dislike)
        {
            like_dislike.setBackground(getResources().getDrawable(R.drawable.round_button));
            like_dislike.setTextColor(getResources().getColor(R.color.white));
        }else
        {
            like_dislike.setBackground(getResources().getDrawable(R.drawable.round_button_gray));
            like_dislike.setTextColor(getResources().getColor(R.color.colorPrimary));
        }

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.like_dislike_button:
                if (feed_data.is_liked()) {
                    like_dislike.setText("Like");
                    dirty_like_dislike = false;

                } else {
                    like_dislike.setText("Unlike");
                    dirty_like_dislike = true;

                }
                setColorForButton();
                setResult();
                break;
        }
    }
}
