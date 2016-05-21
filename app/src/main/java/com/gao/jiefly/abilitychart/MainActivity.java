package com.gao.jiefly.abilitychart;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AbilityChatView abilityChatView = (AbilityChatView) findViewById(R.id.idAbChart);
        abilityChatView.setCount(5);
        abilityChatView.setTextColor(Color.RED);
        abilityChatView.init();
    }
}
