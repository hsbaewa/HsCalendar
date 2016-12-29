package kr.co.hs.HsCalendar;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Calendar;

/**
 * Created by Bae on 2016-12-28.
 */
public class MonthView extends LinearLayout implements View.OnClickListener {
    public MonthView(Context context) {
        super(context);
        init();
    }

    public MonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MonthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private CardView[] mCardView;
//    private Calendar mCurrentCalendar;
    private Calendar mMaxCalendar;

    private void init(){
        mCardView = new CardView[12];
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_month, null);
        mCardView[0] = (CardView) view.findViewById(R.id.CardView01);
        mCardView[0].setTag(0);
        mCardView[1] = (CardView) view.findViewById(R.id.CardView02);
        mCardView[1].setTag(1);
        mCardView[2] = (CardView) view.findViewById(R.id.CardView03);
        mCardView[2].setTag(2);
        mCardView[3] = (CardView) view.findViewById(R.id.CardView04);
        mCardView[3].setTag(3);
        mCardView[4] = (CardView) view.findViewById(R.id.CardView05);
        mCardView[4].setTag(4);
        mCardView[5] = (CardView) view.findViewById(R.id.CardView06);
        mCardView[5].setTag(5);
        mCardView[6] = (CardView) view.findViewById(R.id.CardView07);
        mCardView[6].setTag(6);
        mCardView[7] = (CardView) view.findViewById(R.id.CardView08);
        mCardView[7].setTag(7);
        mCardView[8] = (CardView) view.findViewById(R.id.CardView09);
        mCardView[8].setTag(8);
        mCardView[9] = (CardView) view.findViewById(R.id.CardView10);
        mCardView[9].setTag(9);
        mCardView[10] = (CardView) view.findViewById(R.id.CardView11);
        mCardView[10].setTag(10);
        mCardView[11] = (CardView) view.findViewById(R.id.CardView12);
        mCardView[11].setTag(11);


        for(int i=0;i<mCardView.length;i++){
            mCardView[i].setOnClickListener(this);
        }

        addView(view);
    }


    public void setMax(Calendar calendar){
        this.mMaxCalendar = calendar;
        setView();
    }

    public void setView(){
        Calendar calendar = this.mMaxCalendar;
        if(calendar == null){
            for(int i=0;i<mCardView.length;i++){
                mCardView[i].setVisibility(View.VISIBLE);
            }
        }else{
            int month = calendar.get(Calendar.MONTH);
            for(int i=month+2;i<mCardView.length;i++){
                mCardView[i].setVisibility(View.INVISIBLE);
            }
        }
    }


    private OnClickMonthListener onClickMonthListener;
    public void setOnClickMonthListener(OnClickMonthListener listener){
        this.onClickMonthListener = listener;
    }


    @Override
    public void onClick(View view) {
        int month = (int) view.getTag();
        if(this.onClickMonthListener != null)
            this.onClickMonthListener.onClickMonth(month);
    }


    public interface OnClickMonthListener{
        void onClickMonth(int month);
    }
}
