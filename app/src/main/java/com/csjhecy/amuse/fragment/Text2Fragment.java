package com.csjhecy.amuse.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Admin on 2017/7/25.
 */

public class Text2Fragment extends Fragment {
    private static Text2Fragment text1Fragment;
    public static Text2Fragment newInstance(Bundle bundle){
        if (text1Fragment == null){
            text1Fragment = new Text2Fragment();
        }
        text1Fragment.setArguments(bundle);
        return text1Fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(text1Fragment.getClass().getSimpleName());
        textView.setTextColor(Color.RED);
        return textView;
    }
}
