package com.khairul.practical9_roompersistence;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MoodNoteViewHolder extends RecyclerView.ViewHolder {

    private final TextView noteDate, noteContent;
    private final ImageView noteMood, noteDayNight;

    private MoodNoteViewHolder(@NonNull View itemView) {
        super(itemView);

        //init
        noteDate = itemView.findViewById(R.id.TVDateContent);
        noteContent= itemView.findViewById(R.id.TVNoteContent);

        noteMood= itemView.findViewById(R.id.IVMood);
        noteDayNight= itemView.findViewById(R.id.IVDayNight);
    }


    //bind
    public void bind(String date, int mood, boolean dayNight, String note){

        noteDate.setText(date);
        noteContent.setText(note);

        //mood
        switch (mood){
            case 1:
                noteMood.setImageResource(R.drawable.ic_baseline_sentiment_very_satisfied_24);
                break;

            case 2:
                noteMood.setImageResource(R.drawable.ic_baseline_sentiment_satisfied_24);
                break;

            default:
                noteMood.setImageResource(R.drawable.ic_baseline_sentiment_very_dissatisfied_24);
        }


        //daynight
        if (dayNight){
            noteDayNight.setImageResource(R.drawable.ic_baseline_wb_sunny_24);
        }
        else {
            noteDayNight.setImageResource(R.drawable.ic_baseline_shield_moon_24);
        }


    }//bind


    static MoodNoteViewHolder create(ViewGroup parent){

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.individual_item_view, parent, false);

        return new MoodNoteViewHolder(view);
    }








}
