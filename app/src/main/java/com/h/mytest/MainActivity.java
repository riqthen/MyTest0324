package com.h.mytest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.h.mytest.observer.Observer;

public class MainActivity extends AppCompatActivity implements Observer {

    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTv = findViewById(R.id.tv);
        ObservableManager.getObservableManager().add(this);
    }

    public void click(View view) {
        ObservableManager.getObservableManager().changeText("123");
    }


    @Override
    public void updateView(String s) {
        mTv.setText(s);
    }
}
