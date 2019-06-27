package com.mdgroup.parents.fragcontrol;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.mdgroup.parents.R;

/**
 * Created by Pratibha on 3/1/2017.
 */

public class FragmentSchool_Teacherdetail extends Fragment {
    TextView teacher_name, teacher_experience, teacher_subject, teacher_address, teacher_email, teacher_qualification;
    ImageView image1;

    public FragmentSchool_Teacherdetail() {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onAttachFragment(Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teacherdetail, container, false);
        getID(view);
        Bundle bundle = this.getArguments();
        String name = bundle.getString("name");
        String qualification = bundle.getString("qualification");
        String experience = bundle.getString("experience");
        String image = bundle.getString("image");
        String emaildata = bundle.getString("email");
        if (image == null) {
            if (image == "") {
                Picasso.with(getContext())
                        .load(R.drawable.no_image_available)
                        // .error(R.drawable.no_image_available)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.no_image_available)
                        .into(image1);
            }
        } else
        {
            if(!image.equalsIgnoreCase(""))
            {
                Picasso.with(getContext())
                        .load(image)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.no_image_available)
                        .into(image1);
            }
        }

        teacher_name.setText(name);
        teacher_qualification.setText(qualification);
        teacher_email.setText(emaildata);
        teacher_experience.setText(experience);
        return view;
    }

    private void getID(View view) {
        teacher_name = (TextView) view.findViewById(R.id.teacher_name);
        teacher_address = (TextView) view.findViewById(R.id.teacher_address);
        teacher_email = (TextView) view.findViewById(R.id.teacher_email);
        teacher_experience = (TextView) view.findViewById(R.id.teacher_experience);
        teacher_qualification = (TextView) view.findViewById(R.id.teacher_qualification);
        image1 = (ImageView) view.findViewById(R.id.teacher_image);
    }

    @Override
    public boolean getAllowReturnTransitionOverlap() {
        return super.getAllowReturnTransitionOverlap();
    }

    @Override
    public void setAllowReturnTransitionOverlap(boolean allow) {
        super.setAllowReturnTransitionOverlap(allow);
    }

    @Override
    public boolean getAllowEnterTransitionOverlap() {
        return super.getAllowEnterTransitionOverlap();
    }

    @Override
    public void setAllowEnterTransitionOverlap(boolean allow) {
        super.setAllowEnterTransitionOverlap(allow);
    }

    @Override
    public Object getSharedElementReturnTransition() {
        return super.getSharedElementReturnTransition();
    }

    @Override
    public void setSharedElementReturnTransition(Object transition) {
        super.setSharedElementReturnTransition(transition);
    }

    @Override
    public Object getSharedElementEnterTransition() {
        return super.getSharedElementEnterTransition();
    }

    @Override
    public void setSharedElementEnterTransition(Object transition) {
        super.setSharedElementEnterTransition(transition);
    }

    @Override
    public Object getReenterTransition() {
        return super.getReenterTransition();
    }

    @Override
    public void setReenterTransition(Object transition) {
        super.setReenterTransition(transition);
    }
}
