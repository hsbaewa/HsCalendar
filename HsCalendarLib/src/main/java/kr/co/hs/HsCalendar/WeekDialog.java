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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Bae on 2016-12-28.
 */
public class WeekDialog extends Dialog implements ViewPager.OnPageChangeListener, View.OnClickListener, WeekView.OnClickWeekListener{

    private WeekView.OnClickWeekListener mOnClickWeekListener;
    private String title = null;

    public WeekDialog(Context context, String title) {
        super(context);
        this.title = title;
    }

    public WeekDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected WeekDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


//    private HsRecyclerView mRecyclerView;
//    private WeekAdapter mAdapter;
//    private CalendarView mCalendarView;

//    private WeekView mWeekView;
    private CustomViewPager mViewPager;
    private CalendarAdapter maAdapter;
    private TextView mTextViewYear;
//    private CardView mCardViewLeft;
//    private Button mLeft;
//    private CardView mCardViewRight;
    private Button mButtonLeft;
    private Button mButtonRight;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_week);


        mViewPager = (CustomViewPager) findViewById(R.id.ViewPager);
        maAdapter = new CalendarAdapter();
        mViewPager.setAdapter(maAdapter);
        mViewPager.addOnPageChangeListener(this);


        mTextViewYear = (TextView) findViewById(R.id.TextViewYear);

//        mCardViewLeft = (CardView) findViewById(R.id.CardViewLeft);
//        mCardViewLeft.setOnClickListener(this);
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

    public void setOnClickWeekListener(WeekView.OnClickWeekListener listener){
        this.mOnClickWeekListener = listener;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Calendar calendar = maAdapter.getItem(position);
        long timeStamp = calendar.getTimeInMillis();

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
        if(view.getId() == R.id.ButtonLeft){
            mViewPager.setCurrentItem(mViewPager.getCurrentItem()-1);
        }else if(view.getId() == R.id.ButtonRight){
            mViewPager.setCurrentItem(mViewPager.getCurrentItem()+1);
        }
    }

    @Override
    public void onClickWeek(long start, long end) {
        if(mOnClickWeekListener != null)
            mOnClickWeekListener.onClickWeek(start, end);
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
//            View v = null;
            WeekView weekView = new WeekView(getContext());
            weekView.setValue(getItem(position));
            weekView.setOnClickWeekListener(WeekDialog.this);

            container.addView(weekView, 0);
            return weekView;
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
}

