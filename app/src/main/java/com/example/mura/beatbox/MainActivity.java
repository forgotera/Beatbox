package com.example.mura.beatbox;

import android.support.v4.app.Fragment;

public class MainActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){
     return BeatBoxFragment.newInstance();
 }

}
