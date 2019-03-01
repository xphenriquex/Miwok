package com.exemplo.android.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {
    private MediaPlayer mMediaPlayer;
    private AudioManager mAudioManager;
    private int audioResourceIdGlobal = 0;

    //Criando uma só instancia do Media Player
    private MediaPlayer.OnCompletionListener completionListener =
            new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    releseaMediaPlayer();
                }
            };

    private AudioManager.OnAudioFocusChangeListener
            audioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    switch (focusChange) {
                        case AudioManager.AUDIOFOCUS_GAIN:
                            play(getActivity());
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        final ArrayList<Word> words = new ArrayList<>();

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

        WordAdpter adpter = new WordAdpter(rootView.getContext(), words, R.color.category_family);


        ListView listView = rootView.findViewById(R.id.word_list);
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
                boolean focus = requestAudioFocus(getActivity());

                //Se focus for concedido executar o audio
                if (focus) {
                    play(getActivity());
                }

            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releseaMediaPlayer();
    }

    private void releseaMediaPlayer() {
        //if o Media Player não for nulo, ele está tocando algum som
        if (mMediaPlayer != null) {

            //Limpanndo os recursos do Media Player pois não precisamos
            //mais dele
            mMediaPlayer.release();

            //setando o Media Player para nulo
            mMediaPlayer = null;

            //abandonado o audio focus
            mAudioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }

    private void play(Context context) {

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

    private boolean requestAudioFocus(Context context) {
        if (context != null) {

            //Criando gerenciado de audio
            mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        }

        //Requisitando focus para o audio
        assert mAudioManager != null;
        int resut = mAudioManager.requestAudioFocus(
                audioFocusChangeListener,
                AudioManager.STREAM_MUSIC,
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
        );

        //Verificando se permissão do focus foi concedido pelo sistema
        return resut == AudioManager.AUDIOFOCUS_REQUEST_GRANTED;
    }
}
