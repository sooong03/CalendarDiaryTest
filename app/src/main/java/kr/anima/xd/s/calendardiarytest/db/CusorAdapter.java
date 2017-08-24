package kr.anima.xd.s.calendardiarytest.db;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;

/**
 * Created by alfo6-10 on 8/23/2017.
 */

public class CusorAdapter extends SimpleCursorAdapter {

    public CusorAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
//        super.bindView(view, context, cursor);
        // table에서 값 받기
    }

}
