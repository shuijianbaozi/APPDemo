package com.zk.my.appdemo.ui.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zk.my.appdemo.R;
import com.zk.my.appdemo.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeaListFragment extends BaseFragment {


    @BindView(R.id.type_textview)
    TextView typeTextview;

    public TeaListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tea_list, container, false);
        //取类型
        Bundle bundle = getArguments();
        String type = bundle.getString(MyConstants.KEY_FRAGMENT_TEY);
        typeTextview=(TextView) view.findViewById(R.id.type_textview);
        typeTextview.setText(type);

        ButterKnife.bind(this, view);
        return view;
    }

}
