package com.gao.jiefly.abilitychart;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiefly on 2016/5/21.
 * Fighting_jiiiiie
 */
public class AbilityChatView extends View {
    //分割线颜色
    private int lineColor;
    //分割线宽
    private int lineWidth;
    //文字颜色
    private int textColor;

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public int getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public int getTextSize() {
        return textSize;
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
    }

    public int getCoverColor() {
        return coverColor;
    }

    public void setCoverColor(int coverColor) {
        this.coverColor = coverColor;
    }

    public int getCoverAlpha() {
        return coverAlpha;
    }

    public void setCoverAlpha(int coverAlpha) {
        this.coverAlpha = coverAlpha;
    }

    public int getPolygonColor() {
        return polygonColor;
    }

    public void setPolygonColor(int polygonColor) {
        this.polygonColor = polygonColor;
    }

    public int getPolygonAlpha() {
        return polygonAlpha;
    }

    public void setPolygonAlpha(int polygonAlpha) {
        this.polygonAlpha = polygonAlpha;
    }

    public int getProertyLevel() {
        return proertyLevel;
    }

    public void setProertyLevel(int proertyLevel) {
        this.proertyLevel = proertyLevel;
    }

    public List<String> getProperty() {
        return property;
    }

    public void setProperty(List<String> property) {
        this.property = property;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    //文字大小
    private int textSize;
    //覆盖区颜色
    private int coverColor;
    //覆盖区画笔宽度
    private int coverWidth;
    //覆盖区画笔style
    private Paint.Style coverStyle;
    //覆盖区透明度
    private int coverAlpha;
    //多边形主体颜色
    private int polygonColor;
    //多边形画笔宽度
    private int polygonWidth;
    //多边形画笔style
    private Paint.Style polygonStyle;
    //多边形初始透明度
    private int polygonAlpha;
    //每项属性的等级数(默认等于count数)
    private int proertyLevel;
    //各个属性项的值
    private List<String> property;
    //各项数据的值
    private List<Double> data;
    //最大数据值
    private Double maxValue;
    //多边形的边数
    private int count = 7;
    //每个区域所占的角度（360/count）
    private float angle;
    //最大半径
    private float radius;
    //多边形中心点
    private Point centerPoint;
    //文字画笔
    private Paint textPaint;
    //多边形画笔
    private Paint mainPaint;
    //数据画笔
    private Paint valuePaint;
    //分割线画笔
    private Paint linePaint;

    //让view居中
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = (float) (0.6 * Math.min(w, h) / 2);
        //中心点坐标
        centerPoint.x = w / 2;
        centerPoint.y = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public AbilityChatView(Context context, int count) {
        super(context);
        this.count = count;
        setDefault();
        init();

    }

    public AbilityChatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setDefault();
        init();
    }

    public AbilityChatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setDefault();
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AbilityChatView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setDefault();
        init();
    }

    public void setCoverStyle(Paint.Style coverStyle) {
        this.coverStyle = coverStyle;
    }

    public void setPolygonWidth(int polygonWidth) {
        this.polygonWidth = polygonWidth;
    }

    public void setCoverWidth(int coverWidth) {
        this.coverWidth = coverWidth;
    }

    public void setPolygonStyle(Paint.Style polygonStyle) {
        this.polygonStyle = polygonStyle;
    }

    private void setDefault(){
        textColor = Color.BLACK;
        textSize = 40;
        lineColor = Color.WHITE;
        lineWidth = 5;
        coverColor = Color.GRAY;
        coverAlpha = 123;
        polygonAlpha = 123;
        polygonWidth = 3;
        polygonColor = Color.BLUE;
        polygonStyle = Paint.Style.FILL;
        proertyLevel = count-3;
        coverStyle = Paint.Style.FILL;
        coverWidth = 3;

    }
    public void init() {
        angle = (float) (Math.PI * 2 / count);

        centerPoint = new Point();

        valuePaint = new Paint();
        valuePaint.setColor(coverColor);
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        valuePaint.setAlpha(coverAlpha);
        valuePaint.setStyle(coverStyle);
        valuePaint.setAntiAlias(true);
        valuePaint.setStrokeWidth(coverWidth);

        textPaint = new TextPaint();
        textPaint.setTextSize(textSize);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);

        mainPaint = new Paint();
        mainPaint.setAntiAlias(true);
        mainPaint.setColor(polygonColor);
        mainPaint.setAlpha(polygonAlpha);
        mainPaint.setStyle(polygonStyle);
        mainPaint.setStrokeWidth(polygonWidth);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setAntiAlias(true);
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineWidth);
        linePaint.setStyle(Paint.Style.STROKE);

        maxValue = 70d;

        data = new ArrayList<>();
        property = new ArrayList<>();


        initData();
        initProperty();
    }

    private void initProperty() {
        String[] strings = new String[]{"物理","魔法","防御","金钱","击杀","生存","助攻"};
        for (int i = 0; i < count; i++) {
            property.add(i, strings[i]);
        }
    }

    private void initData() {
        for (int i = 0; i < count; i++) {
            data.add(i, (double) (10 * i) + 10);
        }
    }

    private void drawLine(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.reset();
            float x = (float) (radius * Math.sin(angle * i));
            float y = (float) (radius * Math.cos(angle * i));
            path.lineTo(x, y);
            canvas.drawPath(path, linePaint);
        }

    }

    private void drawPolygon(Canvas canvas) {
        Path path = new Path();
        float delatR = radius / (proertyLevel);

        for (int i = proertyLevel; i > 0; i--) {
            float currentR = delatR * i;
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    //将起点移到角度为0半径为当前半径的点
                    path.moveTo((float) (currentR * Math.sin(0)), (float) (currentR * Math.cos(0)));
                } else {
                    float currentAngle = angle * j;
                    float x = (float) (currentR * Math.sin(currentAngle));
                    float y = (float) (currentR * Math.cos(currentAngle));
                    path.lineTo(x, y);
                }
            }
            path.close();
            if (polygonStyle == Paint.Style.STROKE)
                mainPaint.setAlpha(255);
            else
                mainPaint.setAlpha(polygonAlpha * i / proertyLevel);
            canvas.drawPath(path, mainPaint);
        }

    }

    private void drawText(Canvas canvas) {
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;

        for (int i = 0; i < count; i++) {
            float currentAngle = angle * i;
            float x = (float) (radius * Math.sin(currentAngle));
            float y = (float) (radius * Math.cos(currentAngle));
            switch ((int) (2 * currentAngle / Math.PI)) {
                case 0:
                    Log.e("jiefly", "第四象限---0" + currentAngle + "anglg" + angle);
                    float dis = textPaint.measureText(property.get(i));
                    canvas.drawText(property.get(i), (float) (x - 0.5 * dis), y + fontHeight, textPaint);
                    break;
                case 1:
                    Log.e("jiefly", "第一象限---1");
                    canvas.drawText(property.get(i), x + 20, y + 10, textPaint);
                    break;
                case 2:
                    Log.e("jiefly", "第二象限---2");
                    dis = textPaint.measureText(property.get(i));//文本长度
                    canvas.drawText(property.get(i), x - dis, y, textPaint);
                    break;
                case 3:
                    Log.e("jiefly", "第三象限---3");
                    dis = textPaint.measureText(property.get(i));//文本长度
                    canvas.drawText(property.get(i), x - dis, y, textPaint);
                    break;
            }
        }
    }

    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {

            double percent = data.get(i) / maxValue;
            Log.e("drawRegion", "data:" + percent + "---" + i);
            float currentAngle = angle * i;
            float x = (float) (radius * Math.sin(currentAngle) * percent);
            float y = (float) (radius * Math.cos(currentAngle) * percent);
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        path.close();
        canvas.drawPath(path, valuePaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //将画布坐标系移到中心点
        canvas.translate(centerPoint.x, centerPoint.y);
        //画正多边形
        drawPolygon(canvas);
        //画正多边形的分割线
        drawLine(canvas);
        //画文字
        drawText(canvas);
        //画覆盖区域
        drawRegion(canvas);
    }
}
