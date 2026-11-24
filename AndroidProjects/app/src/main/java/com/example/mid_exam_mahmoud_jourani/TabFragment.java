package com.example.mid_exam_mahmoud_jourani;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TabFragment extends Fragment {

    private static final String ARG_TITLE = "title";

    public TabFragment() {
        super(R.layout.fragment_tab);
    }

    public static TabFragment newInstance(String title) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv = view.findViewById(R.id.tvTab);
        String title = getArguments() != null ? getArguments().getString(ARG_TITLE) : "";
        tv.setText(title + " Tab");
    }
}