package com.example.t.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
	public static final int NUM = 4;
	public static final int SUM = NUM * NUM;
	public static final int LEN = SUM / 2;

	private float screenX = 0;
	private float screenY = 0;

	private float len_x = 0;
	private float len_y = 0;

	private Item[][] pos = new Item[NUM][NUM];

	public GameView(Context context) {
		this(context, null);
	}

	public GameView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		initData();
	}

	private void initData() {
		// ���ݳ�ʼ��
		int[] tmp = new int[SUM];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = 0;
		}

		// �����������
		int step = 0;
		int index = 0;
		for (int i = 0; i < 2 * LEN; i++) {
			step = (int) (Math.random() * 10);
			step++;
			int j = 0;
			while (j < step) {
				index = (index + 1) % SUM;
				if (tmp[index] == 0) {
					j++;
				}
			}

			tmp[index] = 1 + i / 2;
		}

		for (int i = 0; i < tmp.length; i++) {
			Item item = new Item();
			item.num = tmp[i];
			item.red = false;
			pos[i / NUM][i % NUM] = item;

		}

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		screenX = getWidth();
		screenY = getHeight();

		len_x = screenX / NUM;
		len_y = screenY / NUM;

		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(2);

		Paint paint2 = new Paint();
		paint2.setColor(Color.BLUE);
		paint2.setStrokeWidth(10);

		Paint paint3 = new Paint();
		paint3.setColor(Color.BLUE);
		paint3.setStrokeWidth(5);
		paint3.setStyle(Paint.Style.FILL);

		/**
		 * ������
		 */
		for (int i = 0; i < NUM + 1; i++) {
			canvas.drawLine(i * len_x, 0, i * len_x, screenY, paint);
			canvas.drawLine(0, i * len_y, screenX, i * len_y, paint);
		}

		for (int i = 0; i < NUM; i++) {
			for (int j = 0; j < NUM; j++) {
				// д����
				if (pos[i][j].num != 0) {
					canvas.drawText(String.valueOf(pos[i][j].num),
							(float) (j + 0.5) * len_x, (float) (i + 0.5)
									* len_y, paint2);
				}
				// Ϳɫ
				if (pos[i][j].red) {
					canvas.drawRect(j * len_x, i * len_y, (j + 1) * len_x,
							(i + 1) * len_y, paint3);
				}
			}
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX();
		float y = event.getY();

		int i = (int) (x / len_x);
		int j = (int) (y / len_y);
		pos[j][i].num = 0;
		invalidate();

		return true;
	}

	class Item {
		int num;
		boolean red;
	}
}
