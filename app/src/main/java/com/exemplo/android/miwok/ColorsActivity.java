package com.exemplo.android.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private int audioResourceIdGlobal = 0;

    //Criando uma só instancia do Media Player
    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releseaMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    switch (focusChange){
                        case AudioManager.AUDIOFOCUS_GAIN:
                            play(ColorsActivity.this);
                            break;

                        case AudioManager.AUDIOFOCUS_LOSS:
                            releseaMediaPlayer();
                            break;

                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                            mMediaPlayer.pause();
                            mMediaPlayer.seekTo(0);
                            break;

                        case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                            mMediaPlayer.pause();
                            mMediaPlayer.seekTo(0);
                            break;

                        default:
                            //
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("vermelho", "wetetti", R.drawable.color_red, R.raw.color_red));
        words.add(new Word("verde", "chokokki", R.drawable.color_green, R.raw.color_green));
        words.add(new Word("marron", "takaakki", R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("cinza", "topoppi", R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("preto", "kululli", R.drawable.color_black, R.raw.color_black));
        words.add(new Word("branco", "kelelli", R.drawable.color_white, R.raw.color_white));
        words.add(new Word("amarelo empoeirado", "topiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("amarelo mostarda", "chiwiitә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        WordAdpter adpter = new WordAdpter(this, words, R.color.category_colors);

        ListView listView = findViewById(R.id.word_list);
        listView.setAdapter(adpter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word item = words.get(position);

                //Criado um AudioResourceID global para
                //poder utilizar no audioFocusListener
                audioResourceIdGlobal = item.getAudioResourceId();

                // Retorna status da permissão do focus
                //Sendo eles, Concedido e não concedido
                boolean focus = requestAudioFocus(ColorsActivity.this);

                //Se focus for concedido executar o audio
                if(focus){
                    play(ColorsActivity.this);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releseaMediaPlayer();
    }


    private void releseaMediaPlayer(){
        //if o Media Player não for nulo, ele está tocando algum som
        if(mMediaPlayer != null){

            //Limpanndo os recursos do Media Player pois não precisamos
            //mais dele
            mMediaPlayer.release();

            //setando o Media Player para nulo
            mMediaPlayer = null;

            //abandonado o audio focus
            mAudioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

    private void play(Context context){

        //Liberando o recuros do Media Player, caso o usuário clique varias vezes
        //ele ainda pode estar tocando, com isso não irá ocorrer nenhum bug
        releseaMediaPlayer();

        //criando e setando ao MediaPlayer o audio e o recurso associado a palavra atua
        mMediaPlayer = MediaPlayer.create(context, audioResourceIdGlobal);
        mMediaPlayer.start();

        //setando um listener ao media player, com isso o audio e parado
        //e os recursos que estão sendo utilizados são limpos
        mMediaPlayer.setOnCompletionListener(completionListener);
    }

    private boolean requestAudioFocus(Context context){

        //Criando gerenciado de audio
        mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        //Requisitando focus para o audio
        int resut = mAudioManager.requestAudioFocus(
                audioFocusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
        );

        //Verificando se permissão do focus foi concedido pelo sistema
        if(resut == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
            return true;
        }else{
            return false;
        }

    }
}
