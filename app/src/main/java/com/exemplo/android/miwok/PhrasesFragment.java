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
public class PhrasesFragment extends Fragment {
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


    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);


        final ArrayList<Word> words = new ArrayList<>();

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

        WordAdpter adpter = new WordAdpter(rootView.getContext(), words, R.color.category_phrases);

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
