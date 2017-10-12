package com.shuttlassignment.shuttleassignment.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.shuttlassignment.shuttleassignment.R;
import com.shuttlassignment.shuttleassignment.modal.pojo.Feeds;
import com.shuttlassignment.shuttleassignment.view.FeedDetailActivity;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;
import static com.bumptech.glide.request.RequestOptions.overrideOf;


/**
 * Created by Sameer Yadav on 11-10-2017.
 */

public class FeedsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Feeds> feedsList = new ArrayList<>();
    private Context context;
    public FeedsListAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Feeds> list) {
        this.feedsList = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        /*Setting up the views according to Tag
        * In our case tag is title*/

        /* I have done it here only
        * we can fetch getType from pojo also
        **/

        if (feedsList.get(position).getTitle().equals("Place"))
            return 1;
        else if (feedsList.get(position).getTitle().equals("Quote"))
            return 2;
        else if (feedsList.get(position).getTitle().equals("People"))
            return 3;
        else if (feedsList.get(position).getTitle().equals("Date"))
            return 0;


        return 4;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = null;

        switch (viewType) {

            case 0:
                View date = LayoutInflater.from(parent.getContext()).inflate(R.layout.date_layout, parent, false);
                vh = new DateHolder(date);
                break;
            case 1:
                View places = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_places_layout, parent, false);
                vh = new PlaceHolder(places);
                break;
            case 2:
                View quote = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_quotes_layout, parent, false);
                vh = new QuoteHolder(quote);
                break;
            case 3:
                View people = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_people_layout, parent, false);
                vh = new PeopleHolder(people);
                break;


        }
        return vh;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder1, final int position) {

        switch (holder1.getItemViewType()) {

            case 0:
                final DateHolder dateHolder = (DateHolder) holder1;

                dateHolder.feed_date.setText(feedsList.get(position).getTime());

                break;

            case 1:
                final PlaceHolder placeHolder = (PlaceHolder) holder1;

                if (feedsList.get(position).is_liked()) {
                    placeHolder.feed_like_button.setText("Unlike");
                    setColorToButton(placeHolder,true);

                } else {
                    placeHolder.feed_like_button.setText("Like");
                    setColorToButton(placeHolder,false);

                }
                placeHolder.feed_from_text.setText(feedsList.get(position).getName());
                placeHolder.feed_card_title.setText(feedsList.get(position).getTitle());

                Glide.with(context)
                        .load(feedsList.get(position).getImageUrl())
                        .apply(new RequestOptions()
                                .placeholder(R.mipmap.ic_launcher))
                        .apply(new RequestOptions()
                                .error(R.mipmap.ic_launcher))
                        .apply(bitmapTransform(new RoundedCornersTransformation(10, 10, RoundedCornersTransformation.CornerType.ALL)))
                        .into(placeHolder.feed_place_image);

                placeHolder.place_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Activity) context).startActivityForResult(new Intent(context, FeedDetailActivity.class).putExtra("feed", feedsList.get(position)).putExtra("position", position), 555);
                    }
                });
                placeHolder.feed_like_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (feedsList.get(position).is_liked()) {
                            feedsList.get(position).setIs_liked(false);
                            placeHolder.feed_like_button.setText("Like");
                            setColorToButton(placeHolder,false);

                        } else {
                            feedsList.get(position).setIs_liked(true);
                            placeHolder.feed_like_button.setText("Unlike");
                            setColorToButton(placeHolder,true);

                        }

                    }
                });

                break;

            case 2:
                final QuoteHolder quoteHolder = (QuoteHolder) holder1;

                if (feedsList.get(position).is_liked()) {
                    quoteHolder.feed_like_button.setText("Unlike");
                    setColorToButton(quoteHolder,true);

                } else {
                    quoteHolder.feed_like_button.setText("Like");
                    setColorToButton(quoteHolder,false);

                }
                quoteHolder.feed_from_text.setText(feedsList.get(position).getName());
                quoteHolder.feed_card_title.setText(feedsList.get(position).getTitle());
                quoteHolder.feed_quote_text.setText(feedsList.get(position).getText());

                quoteHolder.quote_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Activity) context).startActivityForResult(new Intent(context, FeedDetailActivity.class).putExtra("feed", feedsList.get(position)).putExtra("position", position), 555);
                    }
                });

                quoteHolder.feed_like_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (feedsList.get(position).is_liked()) {
                            feedsList.get(position).setIs_liked(false);
                            quoteHolder.feed_like_button.setText("Like");
                            setColorToButton(quoteHolder,false);

                        } else {
                            feedsList.get(position).setIs_liked(true);
                            quoteHolder.feed_like_button.setText("Unlike");
                            setColorToButton(quoteHolder,true);

                        }

                    }
                });

                break;

            case 3:
                final PeopleHolder peopleHolder = (PeopleHolder) holder1;

                if (feedsList.get(position).is_liked()) {
                    peopleHolder.feed_like_button.setText("Unlike");
                    setColorToButton(peopleHolder,true);

                } else {
                    peopleHolder.feed_like_button.setText("Like");
                    setColorToButton(peopleHolder,false);

                }


                peopleHolder.feed_from_text.setText(feedsList.get(position).getName());
                peopleHolder.feed_card_title.setText(feedsList.get(position).getTitle());
                peopleHolder.feed_people_text.setText(feedsList.get(position).getText());

                Glide.with(context)
                        .load(feedsList.get(position).getImageUrl())
                        .apply(bitmapTransform(new RoundedCornersTransformation(10, 10, RoundedCornersTransformation.CornerType.ALL)))

                        .apply(new RequestOptions()
                                .placeholder(R.mipmap.ic_launcher))
                        .apply(new RequestOptions()
                                .error(R.mipmap.ic_launcher))
                        .into(peopleHolder.feed_people_image);

                peopleHolder.people_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ((Activity) context).startActivityForResult(new Intent(context, FeedDetailActivity.class).putExtra("feed", feedsList.get(position)).putExtra("position", position), 555);
                    }
                });

                peopleHolder.feed_like_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (feedsList.get(position).is_liked()) {
                            feedsList.get(position).setIs_liked(false);
                            peopleHolder.feed_like_button.setText("Like");
                            setColorToButton(peopleHolder,false);

                        } else {
                            feedsList.get(position).setIs_liked(true);
                            peopleHolder.feed_like_button.setText("Unlike");
                            setColorToButton(peopleHolder,true);

                        }

                    }
                });

                break;
        }


    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);

    }

    /*
    Setting up the color changes according to
    like dislike state
    */
    private void setColorToButton(RecyclerView.ViewHolder holder , boolean is_like)
    {
        if(is_like)
        {
            if(holder.getItemViewType()==1)
            {
                ((PlaceHolder)holder).feed_like_button.setBackground(context.getResources().getDrawable(R.drawable.round_button));
                ((PlaceHolder)holder).feed_like_button.setTextColor(context.getResources().getColor(R.color.white));

            }else if(holder.getItemViewType()==2)
            {
                ((QuoteHolder)holder).feed_like_button.setBackground(context.getResources().getDrawable(R.drawable.round_button));
                ((QuoteHolder)holder).feed_like_button.setTextColor(context.getResources().getColor(R.color.white));

            }else if(holder.getItemViewType()==3)
            {
                ((PeopleHolder)holder).feed_like_button.setBackground(context.getResources().getDrawable(R.drawable.round_button));
                ((PeopleHolder)holder).feed_like_button.setTextColor(context.getResources().getColor(R.color.white));

            }

        }else
        {
            if(holder.getItemViewType()==1)
            {
                ((PlaceHolder)holder).feed_like_button.setBackground(context.getResources().getDrawable(R.drawable.round_button_gray));
                ((PlaceHolder)holder).feed_like_button.setTextColor(context.getResources().getColor(R.color.colorPrimary));

            }else if(holder.getItemViewType()==2)
            {
                ((QuoteHolder)holder).feed_like_button.setBackground(context.getResources().getDrawable(R.drawable.round_button_gray));
                ((QuoteHolder)holder).feed_like_button.setTextColor(context.getResources().getColor(R.color.colorPrimary));

            }else if(holder.getItemViewType()==3)
            {
                ((PeopleHolder)holder).feed_like_button.setBackground(context.getResources().getDrawable(R.drawable.round_button_gray));
                ((PeopleHolder)holder).feed_like_button.setTextColor(context.getResources().getColor(R.color.colorPrimary));

            }
        }
    }

    @Override
    public int getItemCount() {
        return feedsList.size();
    }

    private class PeopleHolder extends RecyclerView.ViewHolder {

        private ImageView feed_people_image;
        private TextView feed_card_title, feed_from_text, feed_people_text;
        private Button feed_like_button;
        private CardView people_card;

        PeopleHolder(View itemView) {
            super(itemView);
            feed_people_image = (ImageView) itemView.findViewById(R.id.feed_people_image);
            feed_card_title = (TextView) itemView.findViewById(R.id.feed_card_title);
            feed_from_text = (TextView) itemView.findViewById(R.id.feed_from_text);
            feed_people_text = (TextView) itemView.findViewById(R.id.feed_people_text);
            feed_like_button = (Button) itemView.findViewById(R.id.feed_like_button);
            people_card = (CardView) itemView.findViewById(R.id.people_card);
        }

    }

    private class QuoteHolder extends RecyclerView.ViewHolder {

        private TextView feed_card_title, feed_from_text, feed_quote_text;
        private Button feed_like_button;
        private CardView quote_card;

        public QuoteHolder(View itemView) {
            super(itemView);
            feed_card_title = (TextView) itemView.findViewById(R.id.feed_card_title);
            feed_from_text = (TextView) itemView.findViewById(R.id.feed_from_text);
            feed_quote_text = (TextView) itemView.findViewById(R.id.feed_quote_text);
            feed_like_button = (Button) itemView.findViewById(R.id.feed_like_button);
            quote_card = (CardView) itemView.findViewById(R.id.quote_card);

        }

    }


    private class DateHolder extends RecyclerView.ViewHolder {

        private TextView feed_date;

        public DateHolder(View itemView) {
            super(itemView);
            feed_date = (TextView) itemView.findViewById(R.id.date_text);
        }

    }

    private class PlaceHolder extends RecyclerView.ViewHolder {

        private ImageView feed_place_image;
        private TextView feed_card_title, feed_from_text;
        private Button feed_like_button;
        private CardView place_card;

        PlaceHolder(View itemView) {
            super(itemView);

            feed_place_image = (ImageView) itemView.findViewById(R.id.feed_place_image);
            feed_card_title = (TextView) itemView.findViewById(R.id.feed_card_title);
            feed_from_text = (TextView) itemView.findViewById(R.id.feed_from_text);
            feed_like_button = (Button) itemView.findViewById(R.id.feed_like_button);
            place_card = (CardView) itemView.findViewById(R.id.place_card);

        }

    }



    /*
    * Getting called when any data is changed in the feed detail activity
    * we we are updating that particular item here
    * */

    public void refreshLikeDislike(int position,boolean is_like)
    {
        feedsList.get(position).setIs_liked(is_like);
        notifyItemChanged(position);
    }



}
