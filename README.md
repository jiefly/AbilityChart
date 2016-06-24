# AbilityChart
AbilityChart is a custom view that can show each property with lines or cover area.You can 
set every feature you want with a few steps.
#Screenshot
![image](https://github.com/jiefly/AbilityChart/blob/master/app/GIF_AbilityChart.gif)
#How to use
```
//XML
	android:id="@+id/ability2"
	android:layout_width="match_parent"
	android:layout_height="300dp"
//MainActivity
	//set property count
	mAbility1.setCount(5);
	//set property data
	mAbility1.setData(data);
	//set title
	mAbility1.changeTitles(new String[]{"Math","English","Chinese", "Physical", "Biological"});
	//other feature you want（in XML and Code）
	...
```
#Set property data
```
        List<Double> data = new ArrayList<>();
        data.add(80d);
        data.add(90d);
        data.add(70d);
        data.add(30d);
        data.add(60d);
        data.add(30d);
        data.add(60d);
        mAbility1.setData(data);
```
#Change style
you can change style by changing the paint Style
```
	mAbility2.setCoverStyle(Paint.Style.STROKE);
        mAbility2.setCoverColor(Color.RED);
        mAbility6.setPolygonStyle(Paint.Style.STROKE);
        mAbility6.setCoverColor(Color.GRAY);
        mAbility6.setPolygonColor(Color.RED);
```
#Change the number of property level
```
mAbility6.setProertyLevel(6);
```
#More detail in Code```

一个能力值属性分布的控件
可以改变属性个数，内部层数，以及view的各种属性（text大小，各项属性值，属性个数，分割线颜色，线宽····）
#效果图：
![image](https://github.com/jiefly/AbilityChart/blob/master/app/GIF_AbilityChart.gif)
<!--![image](https://raw.githubusercontent.com/jiefly/AbilityChart/master/app/Screenshot_20160522-150549.png)
![image](https://raw.githubusercontent.com/jiefly/AbilityChart/master/app/Screenshot_20160521-203747.png)-->
#如何使用
```

 //XML
	android:id="@+id/ability2"
	android:layout_width="match_parent"
	android:layout_height="300dp"
//MainActivity
	//设置属性的个数（即多边形边数）
	mAbility1.setCount(5);
	//设置每个属性的值
	List<Double> data = new ArrayList<>();
        data.add(80d);
        data.add(90d);
        data.add(70d);
        data.add(30d);
        data.add(60d);
        data.add(30d);
        data.add(60d);
	mAbility1.setData(data);
	//设置每个属性的名称（名称个数必须大于等于count）
	mAbility1.changeTitles(new String[]{"Math","English","Chinese", "Physical", "Biological"});
	//还可以设置size color style 等等。。 
	...
```
#设置属性的标题和数目
```
mAbility3.setCount(4);
mAbility3.changeTitles(new String[]{"Math","English","Chinese", "Physical", "Biological"});
```
#设置view的各个属性
```
<com.gao.jiefly.abilitychart.AbilityChatView
		    android:layout_width="0dp"
		    android:id="@+id/ability3"
		    android:layout_weight="1"
		    android:layout_height="match_parent"
		    custom:textColor="#000"
		    custom:lineColor="#75082d"
		    custom:textSize="3sp"
		    custom:coverColor="#ff0000"
		    custom:coverAlpha="100"
		    custom:polygonAlpha="23"
		    custom:lineWidth="0.2dp"
	    />
```
#在代码中设置view的各个属性
```
        abilityChatView.setCount(6);
        abilityChatView.setProertyLevel(4);
        abilityChatView.setPolygonColor(Color.DKGRAY);
        abilityChatView.setCoverColor(Color.RED);
        abilityChatView.setTextColor(Color.RED);
        abilityChatView.setPolygonStyle(Paint.Style.STROKE);
        abilityChatView.setCoverStyle(Paint.Style.STROKE);
        abilityChatView.setCoverAlpha(255);
        abilityChatView.setLineColor(Color.GREEN);
        abilityChatView.setLineWidth(4);
```
#更多详细信息请查看代码
