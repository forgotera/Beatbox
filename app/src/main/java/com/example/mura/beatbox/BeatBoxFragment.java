package com.example.mura.beatbox;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import com.example.mura.beatbox.databinding.FragmentBeatBoxBinding;
import com.example.mura.beatbox.databinding.ListItemSoundBinding;

import java.util.List;

public class BeatBoxFragment extends Fragment {

    private BeatBox mBeatBox;

    public static BeatBoxFragment newInstance(){
        return new BeatBoxFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //удерживание фрагмента
        setRetainInstance(true);

        mBeatBox = new BeatBox(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        //привязываемся к фрагменту
        FragmentBeatBoxBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_beat_box,container,false);

        binding.recylerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        binding.recylerView.setAdapter(new SoundAdapter(mBeatBox.getmSound()));
        return binding.getRoot();
    }

    private class SoundHandler extends RecyclerView.ViewHolder{
        private ListItemSoundBinding mBinding;

        private SoundHandler(ListItemSoundBinding binding){
            super(binding.getRoot());
            mBinding = binding;
            //подключение viewModel
            mBinding.setViewModel(new SoundViewModel(mBeatBox));
        }

        public void bind(Sound sound){
            mBinding.getViewModel().setmSound(sound);
            mBinding.executePendingBindings();
        }
    }

    private class SoundAdapter extends RecyclerView.Adapter<SoundHandler>{
        private List<Sound> mSounds;

        //связывание со списком объектов Sound
        public SoundAdapter(List<Sound> sounds){
            mSounds = sounds;
        }

        @NonNull
        @Override
        public SoundHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //связывание
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            ListItemSoundBinding binding = DataBindingUtil
                    .inflate(inflater,R.layout.list_item_sound,parent,false);
            return new SoundHandler(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull SoundHandler holder, int position) {
            Sound sound = mSounds.get(position);
            holder.bind(sound);
        }

        @Override
        public int getItemCount() {
            return  mSounds.size();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mBeatBox.realese();
    }
}
