package com.gao.jiefly.abilitychart;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends Activity {
    @InjectView(R.id.ability1)
    AbilityChatView mAbility1;
    @InjectView(R.id.ability2)
    AbilityChatView mAbility2;
    @InjectView(R.id.ability3)
    AbilityChatView mAbility3;
    @InjectView(R.id.ability4)
    AbilityChatView mAbility4;
    @InjectView(R.id.ability5)
    AbilityChatView mAbility5;
    @InjectView(R.id.ability6)
    AbilityChatView mAbility6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        List<Double> data = new ArrayList<>();
        data.add(80d);
        data.add(90d);
        data.add(70d);
        data.add(30d);
        data.add(60d);
        data.add(30d);
        data.add(60d);
        data.add(80d);
        data.add(90d);
        data.add(70d);
        data.add(30d);
        data.add(60d);
        data.add(30d);
        data.add(60d);
        mAbility1.setCount(3);
        mAbility1.setData(data);
        mAbility2.setCoverAlpha(255);
        mAbility2.setData(data);
        mAbility2.setCount(4);
        mAbility2.setCoverStyle(Paint.Style.STROKE);
        mAbility2.setCoverColor(Color.RED);
        mAbility2.setTextColor(Color.GREEN);
        mAbility2.setLineColor(Color.BLUE);
        mAbility2.setProertyLevel(2);
        mAbility2.changeTitles(new String[]{"Math","English","Chinese", "Physical"});
        mAbility3.setCount(5);
        mAbility3.setProertyLevel(3);
        mAbility3.changeTitles(new String[]{"Math","English","Chinese", "Physical", "Biological"});
        mAbility3.setTextSize(50);
        mAbility5.setData(data);
        mAbility5.setCount(7);
        mAbility6.setData(data);
        mAbility6.setCount(7);
        mAbility6.setPolygonStyle(Paint.Style.STROKE);
        mAbility6.setCoverColor(Color.GRAY);
        mAbility6.setPolygonColor(Color.RED);
        mAbility6.setCoverAlpha(190);
        mAbility6.setProertyLevel(6);

        /*AbilityChatView abilityChatView = (AbilityChatView) findViewById(R.id.idAbChart1);
        assert abilityChatView != null;
        abilityChatView.setCount(7);
        abilityChatView.setProertyLevel(3);
        abilityChatView.setPolygonColor(Color.DKGRAY);
        abilityChatView.setCoverColor(Color.RED);
        abilityChatView.setTextColor(Color.RED);
        abilityChatView.setPolygonStyle(Paint.Style.STROKE);
        abilityChatView.setCoverStyle(Paint.Style.STROKE);
        abilityChatView.setCoverAlpha(255);
        abilityChatView.setLineColor(Color.GREEN);
        abilityChatView.setLineWidth(4);
        abilityChatView.init();
        AbilityChatView abilityChatView1 = (AbilityChatView) findViewById(R.id.idAbChart2);
        assert abilityChatView1 != null;
        abilityChatView1.setCoverStyle(Paint.Style.STROKE);
        abilityChatView1.setCoverColor(Color.RED);
        abilityChatView1.setLineColor(Color.BLACK);
        abilityChatView1.setCoverAlpha(255);
        abilityChatView1.init();
        AbilityChatView abilityChatView2 = (AbilityChatView) findViewById(R.id.idAbChart3);
        abilityChatView2.setCoverColor(Color.RED);
        abilityChatView2.init();
        AbilityChatView abilityChatView3 = (AbilityChatView) findViewById(R.id.idAbChart4);

        abilityChatView3.setPolygonStyle(Paint.Style.STROKE);
        abilityChatView3.setLineColor(Color.BLACK);
        abilityChatView3.setLineWidth(2);
        abilityChatView3.setCoverAlpha(199);
        abilityChatView3.init();*/
    }
}
