package com.exemplo.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("pai", "әpә", R.drawable.family_father));
        words.add(new Word("mãe", "әta", R.drawable.family_mother));
        words.add(new Word("filho", "angsi", R.drawable.family_son));
        words.add(new Word("filha", "tune", R.drawable.family_daughter));
        words.add(new Word("irmão mais velho", "taachi", R.drawable.family_older_brother));
        words.add(new Word("irmão mais novo", "chalitti", R.drawable.family_younger_brother));
        words.add(new Word("irmã mais velha", "teṭe", R.drawable.family_older_sister));
        words.add(new Word("irmã mais nova", "kolliti", R.drawable.family_younger_sister));
        words.add(new Word("avó", "ama", R.drawable.family_grandmother));
        words.add(new Word("avô", "paapa", R.drawable.family_grandfather));

//        WordAdpter adpter = new WordAdpter(this, words, R.color.category_family);
        WordAdpter adpter = new WordAdpter(this, words, R.color.category_family);

        ListView listView = findViewById(R.id.word_list);
        listView.setAdapter(adpter);
    }
}
