package com.example.stu.updatedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.stu.updatedemo.mpchart.LineActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MpChartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_chart);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_line})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_line:
                Intent intent = new Intent(this, LineActivity.class);
                startActivity(intent);
                break;
        }
    }

}
