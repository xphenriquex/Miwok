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

        words.add(new Word("um", "lutti", R.drawable.number_one));
        words.add(new Word("dois", "otiiko", R.drawable.number_two));
        words.add(new Word("três", "tolookosu", R.drawable.number_three));
        words.add(new Word("quatro", "oyyisa", R.drawable.number_four));
        words.add(new Word("cinco", "massokka", R.drawable.number_five));
        words.add(new Word("seis", "temmokka", R.drawable.number_six));
        words.add(new Word("sete", "kenekaku", R.drawable.number_seven));
        words.add(new Word("oito", "kawinta", R.drawable.number_eight));
        words.add(new Word("nove", "wo'e", R.drawable.number_nine));
        words.add(new Word("dez", "na'aacha", R.drawable.number_ten));

        WordAdpter adpter = new WordAdpter(this, words, R.color.category_numbers);

        ListView listView = findViewById(R.id.word_list);
        listView.setAdapter(adpter);
    }
}


