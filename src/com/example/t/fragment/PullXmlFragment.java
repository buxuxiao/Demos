package com.example.t.fragment;

import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import com.example.t.R;

import android.R.string;
import android.R.xml;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class PullXmlFragment extends Fragment {
	private Button button1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.pull_parser, container, false);

		button1 = (Button) view.findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startParser();
			}
		});

		return view;

	}

	private void startParser() {
		try {

			XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory
					.newInstance();
			XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
			InputStream in = getResources().getAssets().open("books.xml");
			xmlPullParser.setInput(in, "UTF-8");

			int eventType = xmlPullParser.getEventType();

			ArrayList<Book> list = null;
			Book book = null;

			while (eventType != XmlPullParser.END_DOCUMENT) {
				switch (eventType) {
				case XmlPullParser.START_DOCUMENT:
					list = new ArrayList<PullXmlFragment.Book>();
					break;
				case XmlPullParser.START_TAG:
					String tagName = xmlPullParser.getName();
					if ("book".equals(tagName)) {
						book = new Book();
						book.auther = xmlPullParser.getAttributeValue(XmlPullParser.NO_NAMESPACE,"auther");
					} else if ("id".equals(tagName)) {
						book.id = xmlPullParser.nextText();
					} else if ("name".equals(tagName)) {
						book.name = xmlPullParser.nextText();
					}

					break;
				case XmlPullParser.END_TAG:
					if ("book".equals(xmlPullParser.getName())) {
						list.add(book);
					}
					break;
				case XmlPullParser.END_DOCUMENT:
					break;

				default:
					break;

				}
				eventType = xmlPullParser.next();
			}
			for (Book book2 : list) {
				System.out.println(book2.id);
				System.out.println(book2.name);
				System.out.println(book2.auther);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	class Book {
		String id;
		String name;
		String auther;
	}
}
