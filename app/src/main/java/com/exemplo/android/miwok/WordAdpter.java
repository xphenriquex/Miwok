package com.exemplo.android.miwok;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WordAdpter extends ArrayAdapter<Word> {
    private int mColor;

    public WordAdpter(@NonNull Context context, ArrayList<Word> words, int color) {
        super(context, 0, words);
        mColor = color;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Get the data item for this position
        Word currentWord = getItem(position);

        //Check if an existing view is being reused, otherwise inflate the view
        View listView = convertView;

        if (listView == null) {
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //Look up view for data  population
        TextView miwokTranslation = listView.findViewById(R.id.miwok_text_view);
        TextView defaultTranslation = listView.findViewById(R.id.dafault_text_view);
        ImageView img = listView.findViewById(R.id.miwok_image_view);
        View layoutText = listView.findViewById(R.id.layout_text);

        // populate the data into the template view using the data object
        miwokTranslation.setText(currentWord.getMiwokTranslation());

        defaultTranslation.setText(currentWord.getDefaultTranslation());

        layoutText.setBackgroundResource(mColor);

        if(currentWord.hasImage()){
            //set the imageView to the image resource specified in the current Word
            img.setImageResource(currentWord.getImageResourceID());

            //Make sure the view is visible
            img.setVisibility(View.VISIBLE);
        }else{
            //Otherwise hide the ImageView (set visibility to GONE)
            img.setVisibility(View.GONE);
        }


        return listView;
    }
}
