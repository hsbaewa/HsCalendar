package kr.co.hs.HsCalendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Bae on 2016-12-28.
 */
public class MonthDialog extends Dialog implements MonthView.OnClickMonthListener, View.OnClickListener, ViewPager.OnPageChangeListener{

    private String title;
    private OnMonthSelectListener onMonthSelectListener;
    public interface OnMonthSelectListener{
        void onSelectMonth(int year, int month);
    }

    public MonthDialog(Context context, String title) {
        super(context);
        this.title = title;
    }

    public MonthDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected MonthDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private CustomViewPager mViewPager;
    private CalendarAdapter mAdapter;
    private TextView mTextViewYear;
//    private CardView mCardViewLeft;
//    private CardView mCardViewRight;

    private Button mButtonLeft;
    private Button mButtonRight;

    private int year;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy년");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_month);

        mViewPager = (CustomViewPager) findViewById(R.id.ViewPager);
        mTextViewYear = (TextView) findViewById(R.id.TextViewYear);

        mButtonLeft = (Button) findViewById(R.id.ButtonLeft);
        mButtonRight = (Button) findViewById(R.id.ButtonRight);

        mAdapter = new CalendarAdapter();
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(this);

        mViewPager.setCurrentItem(mAdapter.getCount()-1);


        mButtonLeft.setOnClickListener(this);
        mButtonRight.setOnClickListener(this);


        LinearLayout layoutTitle = (LinearLayout) findViewById(R.id.LayoutTitle);
        TextView tvTitle = (TextView) findViewById(R.id.TextViewTitle);
        if(this.title == null){
            layoutTitle.setVisibility(View.GONE);
        }else{
            layoutTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(this.title);
        }
    }

    public void setOnMonthSelectListener(OnMonthSelectListener onMonthSelectListener){
        this.onMonthSelectListener = onMonthSelectListener;
    }

    @Override
    public void onClickMonth(int month) {
        if(this.onMonthSelectListener != null){
            this.onMonthSelectListener.onSelectMonth(year, month);
        }
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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Calendar calendar = mAdapter.getItem(position);

        year = calendar.get(Calendar.YEAR);

        long timeStamp = calendar.getTimeInMillis();

        mTextViewYear.setText(sdf.format(timeStamp));

        if(position == mAdapter.getCount()-1){
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


    class CalendarAdapter extends PagerAdapter {

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

            MonthView monthView = new MonthView(getContext());

            int year = Calendar.getInstance().get(Calendar.YEAR);
            int currentYear = getItem(position).get(Calendar.YEAR);

            if(year == currentYear){
                monthView.setMax(Calendar.getInstance());
            }else{
                monthView.setMax(null);
            }
            monthView.setOnClickMonthListener(MonthDialog.this);

            container.addView(monthView, 0);
            return monthView;
        }


        public Calendar getItem(int position){
            Calendar calendar = Calendar.getInstance();
            int gap = (getCount()-1)-position;
            calendar.add(Calendar.YEAR, gap * (-1));
            return calendar;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }
    }
}
