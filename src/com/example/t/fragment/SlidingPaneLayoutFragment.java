package com.example.t.fragment;

import com.example.t.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * 
 * 将SlidingPaneLayout放在fragment里 会出现手势不响应的现象
 * 
 * @author Administrator
 *
 */
public class SlidingPaneLayoutFragment extends Fragment {
	private SlidingPaneLayout slidingPaneLayout;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.sliding_pane_layout_fragment,
				null);
		return view;
	}

}
