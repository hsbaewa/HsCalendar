package kr.co.hs.HsCalendar;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Bae on 2016-12-28.
 */
public class WeekView extends LinearLayout implements View.OnClickListener {
    private Calendar mCurrentCalendar;
    private OnClickWeekListener mOnClickWeekListener;


    public WeekView(Context context) {
        super(context);
        init();
    }

    public WeekView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WeekView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private TextView[][] tvDate;
    private CardView[] mCardView;


    private void init(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_calendar, null);
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

        mCardView = new CardView[6];
        mCardView[0] = (CardView) view.findViewById(R.id.CardView0);
        mCardView[0].setTag(0);
        mCardView[1] = (CardView) view.findViewById(R.id.CardView1);
        mCardView[1].setTag(1);
        mCardView[2] = (CardView) view.findViewById(R.id.CardView2);
        mCardView[2].setTag(2);
        mCardView[3] = (CardView) view.findViewById(R.id.CardView3);
        mCardView[3].setTag(3);
        mCardView[4] = (CardView) view.findViewById(R.id.CardView4);
        mCardView[4].setTag(4);
        mCardView[5] = (CardView) view.findViewById(R.id.CardView5);
        mCardView[5].setTag(5);

        for(int i=0;i<mCardView.length;i++){
            mCardView[i].setOnClickListener(this);
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
        int maxLine = -1;
        int dayCount = 1;

        //이번 달의 1일이 무슨요일?
        calendar.set(Calendar.DATE, 1);
        int dow = calendar.get(Calendar.DAY_OF_WEEK);
        for(;dow<8;dow++){
            if(isLastdayCheck && dayCount>currentDate){
                if(maxLine < 0)
                    maxLine = currentLine;
                tvDate[currentLine][dow-1].setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey100));
                if(currentLine > maxLine)
                    mCardView[currentLine].setOnClickListener(null);
            }else{
                if((dow-1) == 0)
                    tvDate[currentLine][dow-1].setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed500));
                else if((dow-1) == 6)
                    tvDate[currentLine][dow-1].setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlue500));
            }
            tvDate[currentLine][dow-1].setText(""+dayCount++);
        }
        dow = calendar.get(Calendar.DAY_OF_WEEK);


        int lastIdx = -1;

        //다음줄부터 채워나감
        while(dayCount <= maxDate){
            currentLine++;
            for(lastIdx=0;lastIdx<7;lastIdx++){
                if(isLastdayCheck && dayCount>currentDate){
                    if(maxLine < 0)
                        maxLine = currentLine;
                    if(currentLine > maxLine)
                        mCardView[currentLine].setOnClickListener(null);
                    tvDate[currentLine][lastIdx].setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey100));
                }else{
                    if(lastIdx == 0)
                        tvDate[currentLine][lastIdx].setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed500));
                    else if(lastIdx == 6)
                        tvDate[currentLine][lastIdx].setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlue500));
                }
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
            tvDate[0][i].setText(""+(lastDate-(dow-1)+i+1));
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
                    if(maxLine < 0)
                        maxLine = currentLine;
                    if(currentLine > maxLine)
                        mCardView[currentLine].setOnClickListener(null);
                }else{
                    if(i == 0)
                        tvDate[currentLine][i].setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed200));
                    else if(i == 6)
                        tvDate[currentLine][i].setTextColor(ContextCompat.getColor(getContext(), R.color.colorBlue200));
                    else
                        tvDate[currentLine][i].setTextColor(ContextCompat.getColor(getContext(), R.color.colorGrey300));
                }
                tvDate[currentLine][i].setText(""+firstDate++);
            }
            lastIdx = 0;
        }
    }


    private void setValue(){
        //달력 만들기
        //지금 날짜 가져와
        Calendar calendar = Calendar.getInstance();
        setValue(calendar);
    }


    public void setOnClickWeekListener(OnClickWeekListener onClickWeekListener){
        this.mOnClickWeekListener = onClickWeekListener;
    }

    @Override
    public void onClick(View view) {
        if(this.mOnClickWeekListener != null){
            int cnt = (int) view.getTag();
            this.mOnClickWeekListener.onClickWeek(getWeekFirstTimeStamp(cnt), getWeekLastTimeStamp(cnt));
        }
    }


    // 사용편리를 위해 작성한 함수 : dip 값을 pixel 값으로 변환하는 함수
    public float getDip(float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }

    public long getWeekFirstTimeStamp(int idx){
        Calendar tempCal = (Calendar) this.mCurrentCalendar.clone();
        long result = tempCal.getTimeInMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        String log = sdf.format(result);

        tempCal.set(Calendar.WEEK_OF_MONTH, idx+1);
        tempCal.set(Calendar.DAY_OF_WEEK, 1);
        result = tempCal.getTimeInMillis();
        log = sdf.format(result);
        return result;
    }

    public long getWeekLastTimeStamp(int idx){
        Calendar tempCal = (Calendar) this.mCurrentCalendar.clone();
        tempCal.set(Calendar.WEEK_OF_MONTH, idx+1);
        tempCal.set(Calendar.DAY_OF_WEEK, 7);
        long result = tempCal.getTimeInMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
        String log = sdf.format(result);
        return result;
    }


    public interface OnClickWeekListener{
        void onClickWeek(long start, long end);
    }
}
