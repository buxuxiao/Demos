package com.example.t.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.t.R;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LineChartFragment extends Fragment {
	private LineChartView chart;
	private LineChartData data;
	private int numberOfLines = 1;
	private int maxNumberOfLines = 4;
	private int numberOfPoints = 12;

	float[][] randomNumbersTab = new float[maxNumberOfLines][numberOfPoints];

	private boolean hasAxes = true;
	private boolean hasAxesNames = true;
	private boolean hasLines = true;
	private boolean hasPoints = true;
	private ValueShape shape = ValueShape.CIRCLE;
	private boolean isFilled = false;
	private boolean hasLabels = false;
	private boolean isCubic = false;
	private boolean hasLabelForSelected = false;
	private boolean pointsHaveDifferentColor;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.linechart, container, false);
		chart = (LineChartView) view.findViewById(R.id.linechart);

		generateValues();

		generateData();

		chart.setViewportCalculationEnabled(false);

		resetViewport();

		return view;
	}

	private void generateValues() {
		for (int i = 0; i < maxNumberOfLines; ++i) {
			for (int j = 0; j < numberOfPoints; ++j) {
				randomNumbersTab[i][j] = (float) Math.random() * 100f;
			}
		}
	}

	private void generateData() {

		List<Line> lines = new ArrayList<Line>();
		for (int i = 0; i < numberOfLines; ++i) {

			List<PointValue> values = new ArrayList<PointValue>();
			for (int j = 0; j < numberOfPoints; ++j) {
				values.add(new PointValue(j, randomNumbersTab[i][j]));
			}

			Line line = new Line(values);
			line.setColor(ChartUtils.COLORS[i]);
			line.setShape(shape);
			line.setCubic(isCubic);
			line.setFilled(isFilled);
			line.setHasLabels(hasLabels);
			line.setHasLabelsOnlyForSelected(hasLabelForSelected);
			line.setHasLines(hasLines);
			line.setHasPoints(hasPoints);
			if (pointsHaveDifferentColor) {
				line.setPointColor(ChartUtils.COLORS[(i + 1)
						% ChartUtils.COLORS.length]);
			}
			lines.add(line);
		}

		data = new LineChartData(lines);

		if (hasAxes) {
			Axis axisX = new Axis();
			Axis axisY = new Axis().setHasLines(true);
			if (hasAxesNames) {
				axisX.setName("Axis X");
				axisY.setName("Axis Y");
			}
			data.setAxisXBottom(axisX);
			data.setAxisYLeft(axisY);
		} else {
			data.setAxisXBottom(null);
			data.setAxisYLeft(null);
		}

		data.setBaseValue(Float.NEGATIVE_INFINITY);
		chart.setLineChartData(data);

	}

	private void resetViewport() {
		// Reset viewport height range to (0,100)
		final Viewport v = new Viewport(chart.getMaximumViewport());
		v.bottom = 0;
		v.top = 100;
		v.left = 0;
		v.right = numberOfPoints - 1;
		chart.setMaximumViewport(v);
		chart.setCurrentViewport(v);
	}
}
