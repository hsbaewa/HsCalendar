package kr.co.hs.HsCalendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Bae on 2016-12-28.
 */
public class DayDialog extends Dialog implements DayView.OnClickDayListener, ViewPager.OnPageChangeListener, View.OnClickListener{

    private String title;
    private CalendarView.OnDateChangeListener onDateChangeListener;

    public DayDialog(Context context, String title) {
        super(context);
        this.title = title;
    }

    public DayDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public DayDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


    private CustomViewPager mViewPager;
    private CalendarAdapter maAdapter;
    private TextView mTextViewYear;
//    private CardView mCardViewLeft;
//    private CardView mCardViewRight;
    private Button mButtonLeft;
    private Button mButtonRight;

//    private CalendarView mCalendarView;

    private Calendar mCurrentCalendar;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mCalendarView = (CalendarView) findViewById(R.id.CalendarView);
//
//        mCalendarView.setMaxDate(System.currentTimeMillis());
//
//        mCalendarView.setOnDateChangeListener(this);

        setContentView(R.layout.dialog_week);


        mViewPager = (CustomViewPager) findViewById(R.id.ViewPager);
        maAdapter = new CalendarAdapter();
        mViewPager.setAdapter(maAdapter);
        mViewPager.addOnPageChangeListener(this);


        mTextViewYear = (TextView) findViewById(R.id.TextViewYear);

        mButtonLeft = (Button) findViewById(R.id.ButtonLeft);
        mButtonLeft.setOnClickListener(this);
        mButtonRight= (Button) findViewById(R.id.ButtonRight);
        mButtonRight.setOnClickListener(this);


        mViewPager.setCurrentItem(maAdapter.getCount());

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        Window window = getWindow();
        lp.copyFrom(window.getAttributes());
        //This makes the dialog take up the full width
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);

        LinearLayout layoutTitle = (LinearLayout) findViewById(R.id.LayoutTitle);
        TextView tvTitle = (TextView) findViewById(R.id.TextViewTitle);
        if(this.title == null){
            layoutTitle.setVisibility(View.GONE);
        }else{
            layoutTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(this.title);
        }
    }


    @Override
    public void onClickDay(int year, int month, int date) {
//        int year = mCurrentCalendar.get(Calendar.YEAR);
//        int month = mCurrentCalendar.get(Calendar.MONTH);
        if(this.onDateChangeListener != null){
            this.onDateChangeListener.onSelectedDayChange(null, year, month, date);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        Calendar calendar = maAdapter.getItem(position);
        mCurrentCalendar = maAdapter.getItem(position);
        long timeStamp = mCurrentCalendar.getTimeInMillis();

        mTextViewYear.setText(sdf.format(timeStamp));


        if(position == maAdapter.getCount()-1){
            mButtonLeft.setVisibility(View.VISIBLE);
            mButtonRight.setVisibility(View.INVISIBLE);
        }else if(position == 0){
            mButtonLeft.setVisibility(View.INVISIBLE);
            mButtonRight.setVisibility(View.VISIBLE);
        }else{
            mButtonLeft.setVisibility(View.VISIBLE);
            mButtonRight.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.ButtonLeft) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        } else if (i == R.id.ButtonRight) {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
        }
    }


    class CalendarAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            DayView dayView = new DayView(getContext());
            dayView.setValue(getItem(position));
            dayView.setOnClickDayListener(DayDialog.this);

            container.addView(dayView, 0);
            return dayView;
        }


        public Calendar getItem(int position){
            Calendar calendar = Calendar.getInstance();
            int gap = (getCount()-1)-position;
            calendar.add(Calendar.MONTH, gap * (-1));
            return calendar;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }
    }




    public void setOnDateChangeListener(CalendarView.OnDateChangeListener listener){
        this.onDateChangeListener = listener;
    }

}
