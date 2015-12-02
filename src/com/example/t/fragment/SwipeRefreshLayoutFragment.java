package com.example.t.fragment;

import java.util.ArrayList;

import com.example.t.R;
import com.example.t.R.id;
import com.example.t.R.layout;

import android.R.color;
import android.R.menu;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SwipeRefreshLayoutFragment extends Fragment implements
		OnRefreshListener {
	// private Recycler
	private View view;

	private SwipeRefreshLayout freshlayout;

	private ListView listView;

	private ArrayAdapter<String> adapter;

	private ArrayList<String> list;

	private int j = -1;

	private Handler mHandler;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		view = inflater.inflate(R.layout.recycle_fragment, null);
		initView();
		initData();

		return view;
	}

	private void initView() {
		// TODO Auto-generated method stub
		freshlayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
		listView = (ListView) view.findViewById(R.id.listView);

		list = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter);
		freshlayout.setOnRefreshListener(this);
		freshlayout.setColorSchemeColors(Color.GREEN, Color.YELLOW, Color.BLUE,
				Color.RED);

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				if (msg.what == 0x123) {
					freshlayout.setRefreshing(false);
				}
				adapter.notifyDataSetChanged();
			}
		};

		listView.setOnScrollListener(new OnScrollListener() {

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				if (scrollState == SCROLL_STATE_IDLE) {
					if (view.getLastVisiblePosition() == (list.size() - 1)) {
						for (int i = 0; i < 5; i++) {
							list.add("item " + list.size());
						}
						mHandler.sendEmptyMessage(0x124);
					}
				}
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void initData() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 20; i++) {
			list.add("item " + i);
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onRefresh() {
		// TODO Auto-generated method stub
		list.add(0, "item " + (j--));
		mHandler.sendEmptyMessageDelayed(0x123, 3000);
	}
}
