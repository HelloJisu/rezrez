package com.reziena.user.reziena_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.reziena.user.reziena_1.R;

public class Explain4Activity extends Fragment
{
    ExplainActivity explainActivity = (ExplainActivity)ExplainActivity.explainactivity;

    public Explain4Activity()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.activity_explain4, container, false);
        return layout;
    }
}