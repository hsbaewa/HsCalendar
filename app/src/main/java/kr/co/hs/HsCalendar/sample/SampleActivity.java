package kr.co.hs.HsCalendar.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import kr.co.hs.HsCalendar.WeekDialog;
import kr.co.hs.HsCalendar.WeekView;

/**
 * Created by Bae on 2016-12-29.
 */
public class SampleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        Button btnWeek = (Button) findViewById(R.id.Dlg1);
        btnWeek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final WeekDialog dialog = new WeekDialog(SampleActivity.this, "제목입니다.");
                dialog.setOnClickWeekListener(new WeekView.OnClickWeekListener() {
                    @Override
                    public void onClickWeek(long start, long end) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }
}
