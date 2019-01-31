package com.exemplo.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;


public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("um", "lutti"));
        words.add(new Word("dois", "otiiko"));
        words.add(new Word("três", "tolookosu"));
        words.add(new Word("quatro", "oyyisa"));
        words.add(new Word("cinco", "massokka"));
        words.add(new Word("seis", "temmokka"));
        words.add(new Word("sete", "kenekaku"));
        words.add(new Word("oito", "kawinta"));
        words.add(new Word("nove", "wo'e"));
        words.add(new Word("dez", "na'aacha"));

        WordAdpter adpter = new WordAdpter(this, words);

        ListView listView = findViewById(R.id.word_list);
        listView.setAdapter(adpter);
    }
}


