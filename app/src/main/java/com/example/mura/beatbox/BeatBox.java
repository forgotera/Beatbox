package com.example.mura.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final String TAG = "BeatBox";

    private static final String SOUNDS_FOLDER = "sample_sounds";

    private static final int MAX_SOUNDS = 5;

    //ASSET MANAGER - для обращения к активам
    private AssetManager mAssets;

    private List<Sound> mSound = new ArrayList<>();

    private SoundPool mSoundPoll;

    public BeatBox(Context context){
        mAssets = context.getAssets();
        mSoundPoll = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC,0);
        loadsSounds();
    }

    private void loadsSounds() {
        String[] soundNames;
        try{
            //возвразает список имен наход. в папке файлов
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG,"Found "+ soundNames.length+ "sounds");
        }catch (IOException e){
            Log.e(TAG,"Could not list assets",e);
            return;
        }
        for(String filename : soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSound.add(sound);
            }catch (IOException e){
                Log.e(TAG,"Could not load sound"+filename,e);
            }
        }
    }

    //загрузка файлов для воспр.
    private void load(Sound sound) throws IOException {
        AssetFileDescriptor afd = mAssets.openFd(sound.getmAssetPath());
        int soundId = mSoundPoll.load(afd,1);
        sound.setmSoundId(soundId);
    }

    public List<Sound> getmSound(){
        return mSound;
    }

    public void play(Sound sound){
        Integer soundId = sound.getmSoundId();
        if(sound == null){
            return;
        }
        mSoundPoll.play(soundId,1.0f,1.0f,1,0,1.0f);
    }

    public void realese(){
        mSoundPoll.release();
    }
}
