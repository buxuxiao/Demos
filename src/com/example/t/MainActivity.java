package com.example.t;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Window;

import com.example.t.adapter.FragmentAdapter;
import com.example.t.fragment.GameFragment;
import com.example.t.fragment.LineChartFragment;
import com.example.t.fragment.PopWindowFragment;
import com.example.t.fragment.PullXmlFragment;
import com.example.t.fragment.SlidDrawFragment;
import com.example.t.fragment.SlidingPaneLayoutFragment;
import com.example.t.fragment.SwipeRefreshLayoutFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;

@ContentView(R.layout.activity_main)
public class MainActivity extends FragmentActivity {

	@ViewInject(R.id.viewPager)
	private ViewPager viewPager;

	private ArrayList<Fragment> list;

	private FragmentAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		ViewUtils.inject(this);

		// getSupportFragmentManager().beginTransaction()
		// .replace(R.id.container, new PullXmlFragment()).commit();

		list = new ArrayList<Fragment>();
		adapter = new FragmentAdapter(getSupportFragmentManager(), list);
		viewPager.setAdapter(adapter);

		// list.add(new PullXmlFragment());
		// list.add(new LineChartFragment());
		// list.add(new GameFragment());
		// list.add(new PopWindowFragment());
		// list.add(new SwipeRefreshLayoutFragment());
		// list.add(new SlidDrawFragment());
		list.add(new SlidingPaneLayoutFragment());

		adapter.notifyDataSetChanged();

	}
}
