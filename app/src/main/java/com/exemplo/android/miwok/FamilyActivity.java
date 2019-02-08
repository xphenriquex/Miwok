package com.exemplo.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<>();
        LinearLayout layoutText = findViewById(R.id.layout_text);

        words.add(new Word("pai", "әpә", R.drawable.family_father, R.raw.family_father));
        words.add(new Word("mãe", "әta", R.drawable.family_mother, R.raw.family_mother));
        words.add(new Word("filho", "angsi", R.drawable.family_son, R.raw.family_son));
        words.add(new Word("filha", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        words.add(new Word("irmão mais velho", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        words.add(new Word("irmão mais novo", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        words.add(new Word("irmã mais velha", "teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        words.add(new Word("irmã mais nova", "kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        words.add(new Word("avó", "ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        words.add(new Word("avô", "paapa", R.drawable.family_grandfather, R.raw.family_grandfather));

        WordAdpter adpter = new WordAdpter(this, words, R.color.category_family);

        ListView listView = findViewById(R.id.word_list);
        listView.setAdapter(adpter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word item = words.get(position);
                mp = MediaPlayer.create(FamilyActivity.this, item.getAudioResourceId());
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
