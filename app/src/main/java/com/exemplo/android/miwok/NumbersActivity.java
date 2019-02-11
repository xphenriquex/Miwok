package com.exemplo.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;


public class NumbersActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;

    //Criando uma só instancia do Media Player
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releseaMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("um", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("dois", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("três", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("quatro", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("cinco", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("seis", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("sete", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("oito", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nove", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("dez", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

        final WordAdpter adpter = new WordAdpter(this, words, R.color.category_numbers);


        ListView listView = findViewById(R.id.word_list);
        listView.setAdapter(adpter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word item = words.get(position);

                //Liberando o recuros do Media Player, caso o usuário clique varias vezes
                //ele ainda pode estar tocando, com isso não irá ocorrer nenhum bug
                releseaMediaPlayer();

                //criando e setando ao MediaPlayer o audio e o recurso associado a palavra atua
                mMediaPlayer = MediaPlayer.create(NumbersActivity.this, item.getAudioResourceId());
                mMediaPlayer.start();

                //setando um listener ao media player, com isso o audio e parado
                //e os recursos que estão sendo utilizados são limpos
                mMediaPlayer.setOnCompletionListener(completionListener);
            }
        });
    }

    private void releseaMediaPlayer(){
        //if o Media Player não for nulo, ele está tocando algum som
        if(mMediaPlayer != null){

            //Limpanndo os recursos do Media Player pois não precisamos
            //mais dele
            mMediaPlayer.release();

            //setando o Media Player para nulo
            mMediaPlayer = null;
        }
    }
}


