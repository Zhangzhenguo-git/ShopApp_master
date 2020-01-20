package com.dc.shopapp.weight;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.dc.shopapp.R;

/**
 * @author Zhangzhenguo
 * @create 2019/12/13
 * @Email 18311371235@163.com
 * @Describe
 */
public class IMGRigTopPointView extends ImageView {
    // 默认模式
    private int pointMode = NO_POINT;
    // 1.不显示红点
    public static final int NO_POINT = 1;
    // 2.只显示一个红点,表示有新消息
    public static final int ONLY_POINT = 2;
    // 3.显示一个红点,红点中间还有消息的数量
    public static final int NUMBER_POINT = 3;
    //设置圆的X轴，Y轴坐标
    private float PNUMX;
    private float PNUMY;
    private float cx;
    private float cy;
    private float radius;
    //消息的数量
    private  int  number = 0;
    //记录当前是否有新消息
    public boolean  isHaveMesage = false;
    //画圆
    private Paint paint;
    // 画消息条数,有未画先知的功能,可以提前知道需要画的文字的长宽
    private TextPaint paintText;
    //画圆角
    private Path mPath;
    private RectF mRectF;
    private Paint mPaint;


    public IMGRigTopPointView(Context context) {
        super(context);
    }

    public IMGRigTopPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context,attrs);
    }

    private void initView(Context context, AttributeSet attrs){
        //画圆
        paint=new Paint();
        //设置圆形状
        paint.setStyle(Paint.Style.FILL);
        //设置圆背景色
        paint.setColor(getResources().getColor(R.color.shop_msg_listitem_righttop));
        //抗锯齿
        paint.setAntiAlias(true);

        //设置文字
        paintText=new TextPaint();
        //设置字体颜色
        paintText.setColor(Color.WHITE);
        //设置显示条数的文本大小
        paintText.setTextSize(30);
        //抗锯齿
        paintText.setAntiAlias(true);
        //设置字体样式
        paint.setStyle(Paint.Style.FILL);

        mPaint=new Paint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(15);
        mPath = new Path();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isHaveMesage)return;
        switch (pointMode){
            case NO_POINT:
                //不显示红点
                break;
            case ONLY_POINT:
                //只显示红点
                canvas.drawCircle(getWidth()-getPaddingRight()-10,getPaddingTop()+10,getPaddingRight()+2,paint);
                break;
            case NUMBER_POINT:
                //显示红点和对应数字
                canvas.drawCircle(getWidth()-getPaddingRight()-10,getPaddingTop()+10,getPaddingRight()+2,paint);
                //设置文字基准线
                String tvTextName="";
                if (number>0 && number<=100){
                    tvTextName=number+"";
                }else {
                    tvTextName="+99";
                }
                //测量文字内容的宽度
                float tvWidth=paintText.measureText(tvTextName);
                //图片右顶点减去文本的一半,使文本中心与图片右顶点重合
                float x = getWidth() - getPaddingRight() - tvWidth / 2;
                //y抽坐标,文字的基准线为图片右顶点下面点
                float y = (float) (getPaddingTop() + paintText.getFontMetrics().bottom*1.5);
                canvas.drawText(tvTextName,x ,y,paintText);
                break;
                default:
                    break;
        }
    }
    /**
     * 设置消息条数
     */
    public void setMessageNum(int number){
        this.number=number;
    }

    /**
     * 是否有新消息
     * @param isHaveMesage true代表有
     */
    public void setHaveMesage(boolean isHaveMesage){
        this.isHaveMesage=isHaveMesage;
        //如果不能即时刷新,使用postInvalidate()就好了
        invalidate();
    }
    /**
     * 设置显示模式
     * @param mode
     */
    public void setPointMode(int mode){
        if(mode>0 && mode<=3){
            pointMode = mode;
        }else {
            throw new RuntimeException("设置的模式有误");
        }
    }

}
