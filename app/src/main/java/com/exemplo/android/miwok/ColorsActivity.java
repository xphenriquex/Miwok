package com.exemplo.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("vermelho", "wetetti"));
        words.add(new Word("verde", "chokokki"));
        words.add(new Word("marron", "takaakki"));
        words.add(new Word("cinza", "topoppi"));
        words.add(new Word("preto", "kululli"));
        words.add(new Word("branco", "kelelli"));
        words.add(new Word("amarelo empoeirado", "topiisә"));
        words.add(new Word("amarelo mostarda", "chiwiitә"));

        WordAdpter adpter = new WordAdpter(this, words);

        ListView listView = findViewById(R.id.word_list);
        listView.setAdapter(adpter);

    }
}
