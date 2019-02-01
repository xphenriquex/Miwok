package com.exemplo.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("Onde você está indo?", "minto wuksus"));
        words.add(new Word("Qual seu nome?", "tinnә oyaase'nә"));
        words.add(new Word("Meu nome é...", "oyaaset..."));
        words.add(new Word("Como você está se sentido?", "michәksәs?"));
        words.add(new Word("Estou me sentindo bem.", "kuchi achit"));
        words.add(new Word("Você está vindo?", "әәnәs'aa?"));
        words.add(new Word("Sim, estou indo.", "hәә’ әәnәm"));
        words.add(new Word("Estou indo.", "әәnәm"));
        words.add(new Word("Vamos lá.", "yoowutis"));
        words.add(new Word("Venha aqui.", "әnni'nem"));

        WordAdpter adpter = new WordAdpter(this, words, R.color.category_phrases);

        ListView listView = findViewById(R.id.word_list);
        listView.setAdapter(adpter);
    }
}
