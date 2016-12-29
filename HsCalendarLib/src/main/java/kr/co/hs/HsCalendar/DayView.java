package kr.co.hs.HsCalendar;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by Bae on 2016-12-29.
 */
public class DayView extends LinearLayout implements View.OnClickListener{

    private Calendar mCurrentCalendar;
    private OnClickDayListener onClickDayListener;

    public DayView(Context context) {
        super(context);
        init();
    }

    public DayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private TextView[][] tvDate;
    private CardView[][] mCardView;

    private void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_day, null);


        tvDate = new TextView[6][7];
        tvDate[0][0] = (TextView) view.findViewById(R.id.TextView00);
        tvDate[0][1] = (TextView) view.findViewById(R.id.TextView01);
        tvDate[0][2] = (TextView) view.findViewById(R.id.TextView02);
        tvDate[0][3] = (TextView) view.findViewById(R.id.TextView03);
        tvDate[0][4] = (TextView) view.findViewById(R.id.TextView04);
        tvDate[0][5] = (TextView) view.findViewById(R.id.TextView05);
        tvDate[0][6] = (TextView) view.findViewById(R.id.TextView06);

        tvDate[1][0] = (TextView) view.findViewById(R.id.TextView10);
        tvDate[1][1] = (TextView) view.findViewById(R.id.TextView11);
        tvDate[1][2] = (TextView) view.findViewById(R.id.TextView12);
        tvDate[1][3] = (TextView) view.findViewById(R.id.TextView13);
        tvDate[1][4] = (TextView) view.findViewById(R.id.TextView14);
        tvDate[1][5] = (TextView) view.findViewById(R.id.TextView15);
        tvDate[1][6] = (TextView) view.findViewById(R.id.TextView16);

        tvDate[2][0] = (TextView) view.findViewById(R.id.TextView20);
        tvDate[2][1] = (TextView) view.findViewById(R.id.TextView21);
        tvDate[2][2] = (TextView) view.findViewById(R.id.TextView22);
        tvDate[2][3] = (TextView) view.findViewById(R.id.TextView23);
        tvDate[2][4] = (TextView) view.findViewById(R.id.TextView24);
        tvDate[2][5] = (TextView) view.findViewById(R.id.TextView25);
        tvDate[2][6] = (TextView) view.findViewById(R.id.TextView26);

        tvDate[3][0] = (TextView) view.findViewById(R.id.TextView30);
        tvDate[3][1] = (TextView) view.findViewById(R.id.TextView31);
        tvDate[3][2] = (TextView) view.findViewById(R.id.TextView32);
        tvDate[3][3] = (TextView) view.findViewById(R.id.TextView33);
        tvDate[3][4] = (TextView) view.findViewById(R.id.TextView34);
        tvDate[3][5] = (TextView) view.findViewById(R.id.TextView35);
        tvDate[3][6] = (TextView) view.findViewById(R.id.TextView36);

        tvDate[4][0] = (TextView) view.findViewById(R.id.TextView40);
        tvDate[4][1] = (TextView) view.findViewById(R.id.TextView41);
        tvDate[4][2] = (TextView) view.findViewById(R.id.TextView42);
        tvDate[4][3] = (TextView) view.findViewById(R.id.TextView43);
        tvDate[4][4] = (TextView) view.findViewById(R.id.TextView44);
        tvDate[4][5] = (TextView) view.findViewById(R.id.TextView45);
        tvDate[4][6] = (TextView) view.findViewById(R.id.TextView46);

        tvDate[5][0] = (TextView) view.findViewById(R.id.TextView50);
        tvDate[5][1] = (TextView) view.findViewById(R.id.TextView51);
        tvDate[5][2] = (TextView) view.findViewById(R.id.TextView52);
        tvDate[5][3] = (TextView) view.findViewById(R.id.TextView53);
        tvDate[5][4] = (TextView) view.findViewById(R.id.TextView54);
        tvDate[5][5] = (TextView) view.findViewById(R.id.TextView55);
        tvDate[5][6] = (TextView) view.findViewById(R.id.TextView56);

        mCardView = new CardView[6][7];
        mCardView[0][0] = (CardView) view.findViewById(R.id.CardView00);
        mCardView[0][1] = (CardView) view.findViewById(R.id.CardView01);
        mCardView[0][2] = (CardView) view.findViewById(R.id.CardView02);
        mCardView[0][3] = (CardView) view.findViewById(R.id.CardView03);
        mCardView[0][4] = (CardView) view.findViewById(R.id.CardView04);
        mCardView[0][5] = (CardView) view.findViewById(R.id.CardView05);
        mCardView[0][6] = (CardView) view.findViewById(R.id.CardView06);

        mCardView[1][0] = (CardView) view.findViewById(R.id.CardView10);
        mCardView[1][1] = (CardView) view.findViewById(R.id.CardView11);
        mCardView[1][2] = (CardView) view.findViewById(R.id.CardView12);
        mCardView[1][3] = (CardView) view.findViewById(R.id.CardView13);
        mCardView[1][4] = (CardView) view.findViewById(R.id.CardView14);
        mCardView[1][5] = (CardView) view.findViewById(R.id.CardView15);
        mCardView[1][6] = (CardView) view.findViewById(R.id.CardView16);

        mCardView[2][0] = (CardView) view.findViewById(R.id.CardView20);
        mCardView[2][1] = (CardView) view.findViewById(R.id.CardView21);
        mCardView[2][2] = (CardView) view.findViewById(R.id.CardView22);
        mCardView[2][3] = (CardView) view.findViewById(R.id.CardView23);
        mCardView[2][4] = (CardView) view.findViewById(R.id.CardView24);
        mCardView[2][5] = (CardView) view.findViewById(R.id.CardView25);
        mCardView[2][6] = (CardView) view.findViewById(R.id.CardView26);

        mCardView[3][0] = (CardView) view.findViewById(R.id.CardView30);
        mCardView[3][1] = (CardView) view.findViewById(R.id.CardView31);
        mCardView[3][2] = (CardView) view.findViewById(R.id.CardView32);
        mCardView[3][3] = (CardView) view.findViewById(R.id.CardView33);
        mCardView[3][4] = (CardView) view.findViewById(R.id.CardView34);
        mCardView[3][5] = (CardView) view.findViewById(R.id.CardView35);
        mCardView[3][6] = (CardView) view.findViewById(R.id.CardView36);

        mCardView[4][0] = (CardView) view.findViewById(R.id.CardView40);
        mCardView[4][1] = (CardView) view.findViewById(R.id.CardView41);
        mCardView[4][2] = (CardView) view.findViewById(R.id.CardView42);
        mCardView[4][3] = (CardView) view.findViewById(R.id.CardView43);
        mCardView[4][4] = (CardView) view.findViewById(R.id.CardView44);
        mCardView[4][5] = (CardView) view.findViewById(R.id.CardView45);
        mCardView[4][6] = (CardView) view.findViewById(R.id.CardView46);

        mCardView[5][0] = (CardView) view.findViewById(R.id.CardView50);
        mCardView[5][1] = (CardView) view.findViewById(R.id.CardView51);
        mCardView[5][2] = (CardView) view.findViewById(R.id.CardView52);
        mCardView[5][3] = (CardView) view.findViewById(R.id.CardView53);
        mCardView[5][4] = (CardView) view.findViewById(R.id.CardView54);
        mCardView[5][5] = (CardView) view.findViewById(R.id.CardView55);
        mCardView[5][6] = (CardView) view.findViewById(R.id.CardView56);

        for(int i=0;i<mCardView.length;i++){
            for(int j=0;j<mCardView[i].length;j++){
                mCardView[i][j].setOnClickListener(this);
            }
        }

        addView(view);
    }

    private void initValue(){
        if(tvDate != null){
            for(int i=0;i<tvDate.length;i++){
                for(int j=0;j<tvDate[i].length;j++){
                    tvDate[i][j].setText("");
                }
            }
        }
    }


    public void setValue(Calendar calendar){
        initValue();

        mCurrentCalendar = (Calendar) calendar.clone();

        //이번달의 마지막날
        int month = calendar.get(Calendar.MONTH);
        int maxDate = calendar.getActualMaximum(Calendar.DATE);
        int year = calendar.get(Calendar.YEAR);
        int date = calendar.get(Calendar.DATE);

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
        int currentDate = Calendar.getInstance().get(Calendar.DATE);

        boolean isLastdayCheck = false;
        if(currentYear == year && currentMonth == month && currentDate == date)
            isLastdayCheck = true;

        int currentLine = 0;
//        int maxLine = -1;
        int dayCount = 1;

        //이번 달의 1일이 무슨요일?
        calendar.set(Calendar.DATE, 1);
        int dow = calendar.get(Calendar.DAY_OF_WEEK);
        for(;dow<8;dow++){
            if(isLastdayCheck && dayCount>currentDate){
                tvDate[currentLine][dow-1].setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey100));
                mCardView[currentLine][dow-1].setOnClickListener(null);
            }else{
                if((dow-1) == 0){
                    tvDate[currentLine][dow-1].setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed500));
                }
                else if((dow-1) == 6){
                    tvDate[currentLine][dow-1].setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlue500));
                }
            }
            DateInfo info = new DateInfo();
            info.year = calendar.get(Calendar.YEAR);
            info.month = calendar.get(Calendar.MONTH);
            info.date = dayCount;
            mCardView[currentLine][dow-1].setTag(info);
            tvDate[currentLine][dow-1].setText(""+dayCount++);
        }
        dow = calendar.get(Calendar.DAY_OF_WEEK);


        int lastIdx = -1;

        //다음줄부터 채워나감
        while(dayCount <= maxDate){
            currentLine++;
            for(lastIdx=0;lastIdx<7;lastIdx++){
                if(isLastdayCheck && dayCount>currentDate){
                    tvDate[currentLine][lastIdx].setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey100));
                    mCardView[currentLine][lastIdx].setOnClickListener(null);
                }else{
                    if(lastIdx == 0)
                        tvDate[currentLine][lastIdx].setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed500));
                    else if(lastIdx == 6)
                        tvDate[currentLine][lastIdx].setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlue500));
                }
                DateInfo info = new DateInfo();
                info.year = calendar.get(Calendar.YEAR);
                info.month = calendar.get(Calendar.MONTH);
                info.date = dayCount;
                mCardView[currentLine][lastIdx].setTag(info);
                tvDate[currentLine][lastIdx].setText(""+dayCount++);
                if(dayCount > maxDate){
                    lastIdx++;
                    break;
                }
            }
        }

        //이전달 날짜 채우기
        calendar.add(Calendar.MONTH, -1);
        int lastDate = calendar.getActualMaximum(Calendar.DATE);
        for(int i=0;i<(dow-1);i++){
            int beforeDate = (lastDate-(dow-1)+i+1);
            DateInfo info = new DateInfo();
            info.year = calendar.get(Calendar.YEAR);
            info.month = calendar.get(Calendar.MONTH);
            info.date = beforeDate;
            mCardView[0][i].setTag(info);
            tvDate[0][i].setText(""+beforeDate);
            if(i == 0)
                tvDate[0][i].setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed200));
            else if(i == 6)
                tvDate[0][i].setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlue200));
            else
                tvDate[0][i].setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey300));
        }
        //다음달 날짜 채우기
        calendar.add(Calendar.MONTH, 2);
        int firstDate = calendar.getActualMinimum(Calendar.DATE);
        for(;currentLine<tvDate.length;currentLine++){
            for(int i = lastIdx;i<7;i++){
                if(isLastdayCheck){
                    tvDate[currentLine][i].setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey100));
                    mCardView[currentLine][i].setOnClickListener(null);
                }else{
                    if(i == 0)
                        tvDate[currentLine][i].setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed200));
                    else if(i == 6)
                        tvDate[currentLine][i].setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlue200));
                    else
                        tvDate[currentLine][i].setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey300));
                }
                DateInfo info = new DateInfo();
                info.year = calendar.get(Calendar.YEAR);
                info.month = calendar.get(Calendar.MONTH);
                info.date = firstDate;
                mCardView[currentLine][i].setTag(info);
                tvDate[currentLine][i].setText(""+firstDate++);
            }
            lastIdx = 0;
        }
    }

    @Override
    public void onClick(View view) {
//        int day = (int) view.getTag();
        DateInfo info = (DateInfo) view.getTag();

        if(this.onClickDayListener != null)
            this.onClickDayListener.onClickDay(info.year, info.month, info.date);
    }


    public void setOnClickDayListener(OnClickDayListener listener){
        this.onClickDayListener = listener;
    }


    public interface OnClickDayListener{
        void onClickDay(int year, int month, int date);
    }


    class DateInfo{
        int year;
        int month;
        int date;
    }
}
