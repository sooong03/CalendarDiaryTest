package kr.anima.xd.s.calendardiarytest;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import kr.anima.xd.s.calendardiarytest.db.DBOpener;
import kr.anima.xd.s.calendardiarytest.utils.*;

import static kr.anima.xd.s.calendardiarytest.utils.DatabaseContents.COL_ALARM;
import static kr.anima.xd.s.calendardiarytest.utils.DatabaseContents.COL_ALLDAY;
import static kr.anima.xd.s.calendardiarytest.utils.DatabaseContents.COL_DESCRIPTION;
import static kr.anima.xd.s.calendardiarytest.utils.DatabaseContents.COL_DURATION;
import static kr.anima.xd.s.calendardiarytest.utils.DatabaseContents.COL_END;
import static kr.anima.xd.s.calendardiarytest.utils.DatabaseContents.COL_RANK;
import static kr.anima.xd.s.calendardiarytest.utils.DatabaseContents.COL_RDATE;
import static kr.anima.xd.s.calendardiarytest.utils.DatabaseContents.COL_RRULE;
import static kr.anima.xd.s.calendardiarytest.utils.DatabaseContents.COL_START;
import static kr.anima.xd.s.calendardiarytest.utils.DatabaseContents.COL_TITLE;
import static kr.anima.xd.s.calendardiarytest.utils.DatabaseContents.INSERT_SUCCESSFUL;


public class PurposeActivity extends AppCompatActivity implements View.OnClickListener,
                                                        AdapterView.OnItemClickListener,
                                                        DatePicker.OnDateChangedListener{

    private SQLiteDatabase db;
    private String tableName="Table Name";

    private String title;
    private String description; // 일정 설명
    private int dtStart; // 일정 시작
    private int dtEnd; // 일정 종료
    private int dtDuration; // 일정 지속기간
    private Boolean allDay=false;
    private Boolean hasAlarm=false;
    private int rRule; // 일정 반복 타입
    private int rDate; // 반복 날짜
    private int rank; // 중요도 1~10

    private DBOpener opener;
    private ContentValues purposeValues;
    private ContentValues values;

    EditText etTitle;
    EditText etDescription;
    CheckBox cbAllday;
    DatePicker dpStart;
    DatePicker dpEnd;
    Spinner spiRrule;
    Spinner spiRdate;
    Spinner spiAlram;
    TextView tvCancel;
    TextView tvOk;
    ImageView ivClose;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purpose);

        findId();
        setSpinner();

        opener=new DBOpener(this, title, null, 1); // DB OPEN
        db=opener.getWritableDatabase();
        purposeValues=new ContentValues();
        // 날짜 테이블
        opener=new DBOpener(this, String.valueOf(dtStart), null, 1);
        db=opener.getWritableDatabase();
        values=new ContentValues();
    }

    public void saveJson(){
        try {

            FileOutputStream fos=openFileOutput(tableName+".json", MODE_APPEND);
            PrintWriter writer=new PrintWriter(fos);

            JSONObject data=new JSONObject();
            data.put(COL_TITLE, title);
            data.put(COL_DESCRIPTION, description);
            data.put(COL_START, dtStart);
            data.put(COL_END, dtEnd);

            String strJson=data.toString();
            writer.print(strJson);

            writer.flush();
            writer.close();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void insertData(){

        purposeValues.put(COL_TITLE, title);
        purposeValues.put(COL_DESCRIPTION, description);
        purposeValues.put(COL_START, dtStart);
        purposeValues.put(COL_END, dtEnd);
        purposeValues.put(COL_DURATION, dtDuration);
        purposeValues.put(COL_ALLDAY, allDay);
        purposeValues.put(COL_ALARM, hasAlarm);
        purposeValues.put(COL_RRULE, rRule);
        purposeValues.put(COL_RDATE, rDate);
        purposeValues.put(COL_RANK, rank);

        long row=db.insert(tableName, null, purposeValues);
        Toast.makeText(this, INSERT_SUCCESSFUL, Toast.LENGTH_SHORT).show();
        // 날짜 테이블에 데이터 저장
        values.put(COL_TITLE, String.valueOf(dtStart));
        values.put(COL_DESCRIPTION, title);
        long rowDate=db.insert(String.valueOf(dtStart), null, values);

    }

    private void setSpinner(){

    }

    public void findId(){
        etTitle= (EditText) findViewById(R.id.etTitle);
        etDescription= (EditText) findViewById(R.id.etDescription);

        cbAllday= (CheckBox) findViewById(R.id.cbAllday);

        dpStart= (DatePicker) findViewById(R.id.dpStart);
        dpEnd= (DatePicker) findViewById(R.id.dpEnd);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            dpStart.setOnDateChangedListener(this);
            dpEnd.setOnDateChangedListener(this);
        }

        spiRrule= (Spinner) findViewById(R.id.spiRrule);
        spiRrule.setOnItemClickListener(this);

        spiRdate= (Spinner) findViewById(R.id.spiRdate);
        spiRdate.setOnItemClickListener(this);
        spiAlram= (Spinner) findViewById(R.id.spiAlarm);
        spiAlram.setOnItemClickListener(this);

        tvCancel= (TextView) findViewById(R.id.tvCancel);
        tvCancel.setOnClickListener(this);
        tvOk= (TextView) findViewById(R.id.tvOk);
        tvOk.setOnClickListener(this);

        ivClose= (ImageView) findViewById(R.id.ivClose);
        ivClose.setOnClickListener(this);
    }

    public void readData(){
        title=etTitle.getText().toString();
        tableName=title;
        description=etDescription.getText().toString();
        if (cbAllday.isChecked()) allDay=true; //알림없음
        dtStart=dpStart.getYear()+(dpStart.getMonth()<10?0+dpStart.getMonth():dpStart.getMonth())+dpStart.getDayOfMonth();
        dtEnd=dpEnd.getYear()+(dpEnd.getMonth()<10?0+dpEnd.getMonth():dpEnd.getMonth())+dpEnd.getDayOfMonth();
        dtDuration=dtEnd-dtStart;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cbAllday:
                if(cbAllday.isChecked()) { //하루종일
                    dpStart.setActivated(false);
                    dpEnd.setActivated(false);
                } else {
                    dpStart.setActivated(true);
                    dpEnd.setActivated(true);
                }
            case R.id.tvOk:
                readData();
                insertData();
                saveJson();
                break;
            case R.id.tvCancel:
                AlertDialog dialog=new AlertDialog.Builder(this).setMessage(getResources().getString(R.string.noti_cancel)).create();
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                if (dialog.getButton(AlertDialog.BUTTON_NEGATIVE).isActivated()) return;
                else finish(); // purpose activity finish
                break;
            case R.id.ivClose:
                finish();
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        switch (view.getId()){
            case R.id.menu_rrule_none: //반복없음
                spiRdate.setActivated(false);
                break;
            case R.id.menu_rrule_day: //요일반복
                // mon ~ sun
                break;
        }
    }

    @Override
    public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
        switch (datePicker.getId()){
            case R.id.dpStart:
//                TODO :: 선택날짜 받기
                break;
            case R.id.dpEnd:
                break;
        }
    }
}
