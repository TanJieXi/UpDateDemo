package com.example.stu.updatedemo.mpchart;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.stu.updatedemo.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineActivity extends AppCompatActivity {
    @BindView(R.id.lineChart)
    LineChart lineChart;
    private List<Entry> mEntries;
    int j = 0;
    private LineDataSet mLineDataSet;
    private XAxis mXAxis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line);
        ButterKnife.bind(this);

        initChart();
    }

    private void initChart() {
        lineChart.setDrawBorders(true);//显示边界
        //数据
        mEntries = new ArrayList<>();
        List<Entry> entries2 = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            j = i;
            mEntries.add(new Entry(i, (float) (Math.random()) * 80));
            entries2.add(new Entry(i, (float) (Math.random()) * 40));
        }

        //一个LineDataSet就是一条线
        mLineDataSet = new LineDataSet(mEntries, "温度");
        mLineDataSet.setCircleColor(Color.parseColor("#37CDA4")); //设置点颜色
        mLineDataSet.setColor(Color.parseColor("#37CDA4"));//设置线颜色
        mLineDataSet.setCircleColorHole(Color.parseColor("#37CDA4")); //设置实心颜色
        //线模式为圆滑曲线（默认折线）
        mLineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);


        LineDataSet lineDataSet2 = new LineDataSet(entries2, "湿度");
        //设置折线图填充
        lineDataSet2.setDrawFilled(true);
        lineDataSet2.setFormLineWidth(1f);
        lineDataSet2.setFormSize(15.f);


        LineData data = new LineData(mLineDataSet, lineDataSet2);
        lineChart.setData(data);
        //设置可见6个
        lineChart.setVisibleXRangeMaximum(6);

        mXAxis = lineChart.getXAxis();
        mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置横坐标位置
        mXAxis.setGranularity(1f);//设置x坐标轴的最小间距
        //mXAxis.setLabelCount(50, false);//设置x轴刻度 //第二个参数标识是否平均分配
        mXAxis.setAxisMinimum(0f);//设置x轴最小值
        mXAxis.setAxisMaximum(11f); //设置x轴最大值



        //设置x轴为字符串
        final List<String> mList = new ArrayList<>();
       /* Collections.addAll(mList,"一月","二月","三月","四月","五月","六月","七月",
                "八月","九月","十月","十一月","十二月");
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return  mList.get((int) value);
            }
        });*/

        //设置y轴
        YAxis leftTAxis = lineChart.getAxisLeft();//得到左边y轴配
        YAxis rightTAxis = lineChart.getAxisRight();//得到右边y轴配
        leftTAxis.setAxisMinimum(0f);//设置x轴最小值
        leftTAxis.setAxisMaximum(100f); //设置x轴最大值

        rightTAxis.setAxisMinimum(0f);//设置x轴最小值
        rightTAxis.setAxisMaximum(100f); //设置x轴最大值
        //设置左边为字符串
        leftTAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value + "%";
            }
        });

        //设置右边的y轴是否显示
        rightTAxis.setEnabled(true);
        rightTAxis.setGranularity(1f);
        rightTAxis.setLabelCount(11,false);
        rightTAxis.setTextColor(Color.BLUE); //文字颜色
        rightTAxis.setGridColor(Color.BLACK); //网格线颜色
        rightTAxis.setAxisLineColor(Color.GREEN); //Y轴颜色

        //加上警告线，限制线
        LimitLine limitLine = new LimitLine(95,"警戒线"); //得到限制线
        limitLine.setLineWidth(2f); //宽度
        limitLine.setTextSize(10f);
        limitLine.setTextColor(Color.RED);  //颜色
        limitLine.setLineColor(Color.BLUE);
        leftTAxis.addLimitLine(limitLine); //Y轴添加限制线

        //下面的标识
        Legend legend = lineChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);

        mHandler.postDelayed(run,500);


    }

    Handler mHandler = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            mXAxis.setAxisMaximum(mLineDataSet.getEntryCount()); //设置x轴最大值
            mLineDataSet.addEntry(new Entry(++j , (float) (Math.random()) * 80));
            mLineDataSet.notifyDataSetChanged();
            lineChart.notifyDataSetChanged();

            lineChart.moveViewToX(mLineDataSet.getEntryCount() - 5);//移动

            mHandler.postDelayed(run,500);
        }
    };

}
