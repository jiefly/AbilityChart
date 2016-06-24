package com.gao.jiefly.abilitychartlibrary;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by jiefly on 2016/5/21.
 * Fighting_jiiiiie
 */
public class AbilityChatView extends View {
    private static final int DEFAULT_COUNT = 6;
    private static final int DEFAULT_LINE_COLOR = Color.RED;
    private static final int DEFAULT_LINE_WIDTH = 3;
    private static final int DEFAULT_TEXT_COLOR = Color.BLACK;
    private static final int DEFAULT_TEXT_SIZE = 42;
    private static final int DEFAULT_COVER_COLOR = Color.RED;
    private static final int DEFAULT_COVER_WIDTH = 2;
    private static final Paint.Style DEFAULT_COVER_STYLE = Paint.Style.FILL;
    private static final int DEFAULT_COVER_ALPHA = 123;
    private static final int DEFAULT_POLYGON_COLOR = Color.BLACK;
    private static final int DEFAULT_POLGON_WIDTH = 3;
    private static final Paint.Style DEFAULT_POLGON_STYLE = Paint.Style.FILL;
    private static final int DEFAULT_POLGON_ALPHA = 123;
    private static final int DEFAULT_PROERTY_LEVEL = DEFAULT_COUNT - 2;
    private static final double DEFAULT_MAX_VALUE = 100d;
    private static final String[] DEFAULT_TITLES = new String[]{"物理", "魔法", "防御", "金钱", "击杀", "生存", "助攻", "物理", "魔法", "防御", "金钱", "击杀", "生存", "助攻"};

    private static final String TAG = "jiefly";
    //divide color
    private int lineColor;
    //divide width
    private int lineWidth;
    private int textColor;
    private int textSize;
    private int coverColor;
    private int coverWidth;
    private Paint.Style coverStyle;
    private int coverAlpha;
    private int polygonColor;
    private int polygonWidth;
    private Paint.Style polygonStyle;
    private int polygonAlpha;
    private int proertyLevel;
    private List<String> property;
    private List<Double> data;
    private Double maxValue;
    //count of you property
    private int count;


    private Paint textPaint;
    private Paint mainPaint;
    private Paint coverPaint;
    private Paint linePaint;


    private float angle;
    //max radius
    private float radius;
    private Point centerPoint;

    private List<String> titles = new ArrayList<>();


    //force the view in center of layout
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = (float) (0.7 * Math.min(w, h) / 2);
        centerPoint.x = w / 2;
        centerPoint.y = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    public AbilityChatView(Context context, int count) {
        this(context, null);
        this.count = count;
    }

    public AbilityChatView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbilityChatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//
        setDefault();
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.AbilityChatView, defStyleAttr, 0);
        int n = typedArray.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.AbilityChatView_lineColor) {
                lineColor = typedArray.getColor(attr, DEFAULT_LINE_COLOR);
            } else if (attr == R.styleable.AbilityChatView_textColor) {
                textColor = typedArray.getColor(attr, DEFAULT_TEXT_COLOR);
            } else if (attr == R.styleable.AbilityChatView_textSize) {
                textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, typedArray.getDimension(attr, DEFAULT_TEXT_SIZE), context.getResources().getDisplayMetrics());
            } else if (attr == R.styleable.AbilityChatView_coverAlpha) {
                coverAlpha = typedArray.getInteger(attr, DEFAULT_COVER_ALPHA);
            } else if (attr == R.styleable.AbilityChatView_coverColor) {
                coverColor = typedArray.getColor(attr, DEFAULT_COVER_COLOR);
            } else if (attr == R.styleable.AbilityChatView_coverLineWidth) {
                coverWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, typedArray.getDimension(attr, DEFAULT_COVER_WIDTH), context.getResources().getDisplayMetrics());
            } else if (attr == R.styleable.AbilityChatView_lineWidth) {
                lineWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, typedArray.getDimension(attr, DEFAULT_LINE_WIDTH), context.getResources().getDisplayMetrics());
            } else if (attr == R.styleable.AbilityChatView_polygonAlpha) {
                polygonAlpha = typedArray.getInteger(attr, DEFAULT_POLGON_ALPHA);
            } else if (attr == R.styleable.AbilityChatView_polygonColor) {
                polygonColor = typedArray.getColor(attr, DEFAULT_POLYGON_COLOR);
            } else if (attr == R.styleable.AbilityChatView_polygonLineWidth) {
                polygonWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, typedArray.getDimension(attr, DEFAULT_POLGON_WIDTH), context.getResources().getDisplayMetrics());
            }
        }
        typedArray.recycle();
        init();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AbilityChatView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Log.e(TAG, "LOLIPOP");
        setDefault();
        init();
    }

    public void setCount(int count) {
        this.count = count;
        changeAngle();
        changeProperty();
        invalidate();
    }

    public void setCoverStyle(Paint.Style coverStyle) {
        this.coverStyle = coverStyle;
        changeCoverPaint();
    }

    public void setPolygonWidth(int polygonWidth) {
        this.polygonWidth = polygonWidth;
        changeMainPaint();
    }

    public void setCoverWidth(int coverWidth) {
        this.coverWidth = coverWidth;
        changeCoverPaint();
    }

    public void setPolygonStyle(Paint.Style polygonStyle) {
        this.polygonStyle = polygonStyle;
        changeMainPaint();
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
        changeLinePaint();
    }

    public void setLineWidth(int lineWidth) {
        this.lineWidth = lineWidth;
        changeLinePaint();
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
        changeTextPaint();
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;
        changeTextPaint();
    }

    public void setCoverColor(int coverColor) {
        this.coverColor = coverColor;
        changeCoverPaint();
    }

    public void setCoverAlpha(int coverAlpha) {
        this.coverAlpha = coverAlpha;
        changeCoverPaint();
    }

    public void setPolygonColor(int polygonColor) {
        this.polygonColor = polygonColor;
        changeMainPaint();
    }

    public void setPolygonAlpha(int polygonAlpha) {
        this.polygonAlpha = polygonAlpha;
        changeMainPaint();
    }

    public void setProertyLevel(int proertyLevel) {
        this.proertyLevel = proertyLevel;
        invalidate();
    }

    public void setProperty(List<String> property) {
        this.property = property;
    }

    public void setData(List<Double> data) {
        this.data = data;
        invalidate();
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    private void setDefault() {
        count = DEFAULT_COUNT;
        textColor = DEFAULT_TEXT_COLOR;
        textSize = DEFAULT_TEXT_SIZE;
        lineColor = DEFAULT_LINE_COLOR;
        lineWidth = DEFAULT_LINE_WIDTH;
        coverColor = DEFAULT_COVER_COLOR;
        coverAlpha = DEFAULT_COVER_ALPHA;
        polygonAlpha = DEFAULT_POLGON_ALPHA;
        polygonWidth = DEFAULT_POLGON_WIDTH;
        polygonColor = DEFAULT_POLYGON_COLOR;
        polygonStyle = DEFAULT_POLGON_STYLE;
        proertyLevel = DEFAULT_PROERTY_LEVEL;
        coverStyle = DEFAULT_COVER_STYLE;
        coverWidth = DEFAULT_COVER_WIDTH;
        maxValue = DEFAULT_MAX_VALUE;
    }

    public void init() {
        changeAngle();

        centerPoint = new Point();
        mainPaint = new Paint();
        textPaint = new TextPaint();
        linePaint = new Paint();
        coverPaint = new Paint();

        changeCoverPaint();
        changeTextPaint();
        changeMainPaint();
        changeLinePaint();

        data = new ArrayList<>();
        property = new ArrayList<>();

        changeData();
        changeTitles(DEFAULT_TITLES);
    }

    public void changeTitles(String[] strings) {
        if (titles.size() > 0)
            titles.clear();
        Collections.addAll(titles, strings);
        changeProperty();
    }

    private void changeAngle() {
        angle = (float) (Math.PI * 2 / count);
        Log.e(TAG, "angle in every zone:" + Math.toDegrees(angle));
    }

    private void changeMainPaint() {

        mainPaint.setAntiAlias(true);
        mainPaint.setColor(polygonColor);
        mainPaint.setAlpha(polygonAlpha);
        mainPaint.setStyle(polygonStyle);
        mainPaint.setStrokeWidth(polygonWidth);
    }

    private void changeTextPaint() {
        textPaint.setTextSize(textSize);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(textColor);
        textPaint.setAntiAlias(true);
    }

    private void changeLinePaint() {
        linePaint.setAntiAlias(true);
        linePaint.setAntiAlias(true);
        linePaint.setColor(lineColor);
        linePaint.setStrokeWidth(lineWidth);
        linePaint.setStyle(Paint.Style.STROKE);
    }

    private void changeCoverPaint() {
        coverPaint.setColor(coverColor);
        coverPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        coverPaint.setAlpha(coverAlpha);
        coverPaint.setStyle(coverStyle);
        coverPaint.setAntiAlias(true);
        coverPaint.setStrokeWidth(coverWidth);
    }

    private void changeProperty() {

        for (int i = 0; i < count; i++) {
            property.add(i, titles.get(i));
        }
    }

    private void changeData() {
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
                    //move start point to angle : 0,radui : 0
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
            double currentAngle = angle * i;
            float x = (float) (radius * Math.sin(currentAngle));
            float y = (float) (radius * Math.cos(currentAngle));
            float dis = textPaint.measureText(property.get(i));
//            Log.e(TAG, (int) Math.toDegrees(currentAngle) / 90 + "<-angle->");
            if (Math.toDegrees(currentAngle) == 0) {
//                0
                canvas.drawText(property.get(i), (float) (x - 0.5 * dis), y + fontHeight, textPaint);
            } else if (Math.toDegrees(currentAngle) < 181 && Math.toDegrees(currentAngle) > 179) {
//                180
                canvas.drawText(property.get(i), (float) (x - 0.5 * dis), y - fontMetrics.bottom, textPaint);
            } else if (Math.toDegrees(currentAngle) < 91 && Math.toDegrees(currentAngle) > 89) {
//                90
                canvas.drawText(property.get(i), x, y + fontMetrics.bottom, textPaint);
            } else if (Math.toDegrees(currentAngle) < 271 && Math.toDegrees(currentAngle) > 269) {
//                270
                canvas.drawText(property.get(i), x - dis, y + fontMetrics.bottom, textPaint);
            } else if (Math.toDegrees(currentAngle) < 89 && Math.toDegrees(currentAngle) > 1) {
                canvas.drawText(property.get(i), x, y + fontMetrics.bottom, textPaint);
            } else if (Math.toDegrees(currentAngle) <= 179 && Math.toDegrees(currentAngle) > 90) {
                canvas.drawText(property.get(i), x, y, textPaint);
            } else if (Math.toDegrees(currentAngle) < 271 && Math.toDegrees(currentAngle) >= 181) {
                canvas.drawText(property.get(i), x - dis, y, textPaint);
            } else if (Math.toDegrees(currentAngle) <= 359 && Math.toDegrees(currentAngle) >= 271) {
                canvas.drawText(property.get(i), x - dis, y + fontMetrics.bottom, textPaint);
            }
        }
    }

    private void drawRegion(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            double percent = data.get(i) / maxValue;
            //Log.e("drawRegion", "data:" + percent + "---" + i);
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
        canvas.drawPath(path, coverPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //move canvas to center
        canvas.translate(centerPoint.x, centerPoint.y);
        drawPolygon(canvas);
        //draw divide
        drawLine(canvas);
        //draw text
        drawText(canvas);
        //draw cover
        drawRegion(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST)
            width = 800;
        if (heightSpecMode == MeasureSpec.AT_MOST)
            height = 800;
        setMeasuredDimension(width, height);
    }
}
