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

        words.add(new Word("pai", "әpә"));
        words.add(new Word("mãe", "әta"));
        words.add(new Word("filho", "angsi"));
        words.add(new Word("filha", "tune"));
        words.add(new Word("irmão mais velho", "taachi"));
        words.add(new Word("irmão mais novo", "chalitti"));
        words.add(new Word("irmã mais velha", "teṭe"));
        words.add(new Word("irmã mais nova", "kolliti"));
        words.add(new Word("avó", "ama"));
        words.add(new Word("avô", "paapa"));

        WordAdpter adpter = new WordAdpter(this, words);

        ListView listView = findViewById(R.id.word_list);
        listView.setAdapter(adpter);
    }
}
