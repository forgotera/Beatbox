package com.example.mura.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

public class SoundViewModel extends BaseObservable {
    private Sound mSound;
    private BeatBox mBeatBox;

    public SoundViewModel(BeatBox beatBox){
        mBeatBox = beatBox;
    }

    public Sound getmSound() {
        return mSound;
    }

    public void setmSound(Sound mSound) {
        this.mSound = mSound;
        notifyChange();
    }

    @Bindable
    public String getTilte(){
        return mSound.getmName();
    }
}
