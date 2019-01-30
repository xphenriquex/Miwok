package com.exemplo.android.miwok;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WordAdpter extends ArrayAdapter<Word> {

    public WordAdpter(@NonNull Context context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //Get the data item for this position
        Word word = getItem(position);

        //Check if an existing view is being reused, otherwise inflate the view
        View listView = convertView;

        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //Look up view for data  population
        TextView miwokTranslation = listView.findViewById(R.id.miwok_text_view);
        TextView defaultTranslation = listView.findViewById(R.id.dafault_text_view);

        // populate the data into the template view using the data object
        miwokTranslation.setText(word.getMwordMiwokTranslation());
        defaultTranslation.setText(word.getmWordDefaultTranslation());

        return listView;
    }
}
