package com.example.mura.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BeatBox {
    private static final String TAG = "BeatBox";

    private static final String SOUNDS_FOLDER = "sample_sounds";

    //ASSET MANAGER - для обращения к активам
    private AssetManager mAssets;

    private List<Sound> mSound = new ArrayList<>();

    public BeatBox(Context context){
        mAssets = context.getAssets();
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
        for(String filename : soundNames){
            String assetPath = SOUNDS_FOLDER + "/" + filename;
            Sound sound = new Sound(assetPath);
            mSound.add(sound);
        }
    }

    public List<Sound> getmSound(){
        return mSound;
    }
}
