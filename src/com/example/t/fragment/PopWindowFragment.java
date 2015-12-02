package com.example.t.fragment;



import com.example.t.R;
import com.example.t.R.anim;
import com.example.t.R.id;
import com.example.t.R.layout;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class PopWindowFragment extends Fragment implements OnClickListener {

	private RelativeLayout parentLL;

	private Button button1;

	private Button button2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.popwindow_fragment, container,
				false);
		parentLL = (RelativeLayout) view.findViewById(R.id.parentLL);
		button1 = (Button) view.findViewById(R.id.button1);
		button2 = (Button) view.findViewById(R.id.button2);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			showSelectPopWindow();
			break;

		case R.id.button2:
			showPopMenu();

			break;

		default:
			break;
		}
	}

	private void showPopMenu() {
		final String[] items = { "item1", "item2", "item3", "item4", "item5" };
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.pop_menu, null);
		PopupWindow popMenu = new PopupWindow(view, 120,
				LayoutParams.WRAP_CONTENT);
		popMenu.setBackgroundDrawable(new BitmapDrawable());
		ListView listView = (ListView) view.findViewById(R.id.listView);

		listView.setFocusableInTouchMode(true);
		listView.setFocusable(true);

		listView.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, items));
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), items[position], 0).show();

			}
		});
		popMenu.showAsDropDown(button2, 10, 10);
		popMenu.setFocusable(true);
		popMenu.setOutsideTouchable(true);
		popMenu.update();
	}

	private void showSelectPopWindow() {
		final PopupWindow mPopupWindow = new PopupWindow();

		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.bottom_pop_window, null);

		final LinearLayout ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

		mPopupWindow.setWidth(LayoutParams.MATCH_PARENT);
		mPopupWindow.setHeight(LayoutParams.WRAP_CONTENT);
		mPopupWindow.setFocusable(true);
		mPopupWindow.setOutsideTouchable(true);
		mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
		mPopupWindow.setContentView(view);

		RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
		parent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mPopupWindow.dismiss();
				ll_popup.clearAnimation();
			}
		});

		Button button1 = (Button) view.findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getActivity(), "popwindow button1", 0).show();
			}
		});
		ll_popup.setAnimation(AnimationUtils.loadAnimation(getActivity(),
				R.anim.activity_translate_in));
		mPopupWindow.showAtLocation(parentLL, Gravity.BOTTOM, 0, 0);
	}

}
