package com.exemplo.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("Onde você está indo?", "minto wuksus", R.raw.phrase_where_are_you_going));
        words.add(new Word("Qual seu nome?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        words.add(new Word("Meu nome é...", "oyaaset...", R.raw.phrase_my_name_is));
        words.add(new Word("Como você está se sentido?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        words.add(new Word("Estou me sentindo bem.", "kuchi achit", R.raw.phrase_im_feeling_good));
        words.add(new Word("Você está vindo?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        words.add(new Word("Sim, estou indo.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        words.add(new Word("Estou indo.", "әәnәm", R.raw.phrase_im_coming));
        words.add(new Word("Vamos lá.", "yoowutis", R.raw.phrase_lets_go));
        words.add(new Word("Venha aqui.", "әnni'nem", R.raw.phrase_come_here));

        WordAdpter adpter = new WordAdpter(this, words, R.color.category_phrases);

        ListView listView = findViewById(R.id.word_list);
        listView.setAdapter(adpter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word item = (Word) parent.getItemAtPosition(position);

                MediaPlayer mp = MediaPlayer.create(getApplication(), item.getAudioResourceId());
                mp.start();
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }
        });
    }
}
