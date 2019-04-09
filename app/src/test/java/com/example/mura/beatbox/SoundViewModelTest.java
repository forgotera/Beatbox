package com.example.mura.beatbox;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class SoundViewModelTest {
    private BeatBox mBeatBox;
    private Sound mSound;
    private SoundViewModel  mSubject;

    @Before
    public void setUp() throws Exception {
        //создание фиктивного класса
        mBeatBox = mock(BeatBox.class);
        mSound = new Sound("assetPath");
        mSubject = new SoundViewModel(mBeatBox);
        mSubject.setmSound(mSound);
    }

    @Test
    public void exposesSoundNameAsTitle(){
        assertThat(mSubject.getTilte(),is(mSound.getmName()));
    }

    @Test
    public void callsBeatBoxPlayOnButtonClicked(){
        mSubject.onButtonClicked();
        //Проверить, что метод play(…) был вызван для mBeatBox с передачей mSound в параметре
        verify(mBeatBox).play(mSound);
    }
}