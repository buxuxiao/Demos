package com.example.t.fragment;

import com.example.t.R;
import com.example.t.R.layout;
import com.example.t.widget.GameView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GameFragment extends Fragment {
	private GameView gameView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.game_fragment, container, false);
		return view;
	}
}
