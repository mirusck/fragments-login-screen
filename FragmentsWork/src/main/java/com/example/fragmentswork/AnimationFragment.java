package com.example.fragmentswork;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class AnimationFragment extends Fragment implements View.OnClickListener{
    ArrayList<Animation> animations;
    TextView tvLogo;
    Random randomGenerator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_animation, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvLogo = (TextView) getActivity().findViewById(R.id.tvLogo);
        randomGenerator = new Random();

        // load anim
        Animation animFadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        Animation animRotate = AnimationUtils.loadAnimation( getActivity(), R.anim.rotate);
        Animation animBounce = AnimationUtils.loadAnimation( getActivity(), R.anim.bounce);
        Animation animSlideDown = AnimationUtils.loadAnimation( getActivity(), R.anim.slide_down);
        animations = new ArrayList<Animation>();
        animations.add(animFadeIn);
        animations.add(animRotate);
        animations.add(animBounce);
        animations.add(animSlideDown);

        tvLogo.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvLogo:
                int index = randomGenerator.nextInt(animations.size());
                Animation animation = animations.get(index);
                tvLogo.startAnimation(animation);
                break;
            default:
                break;
        }

    }
}
