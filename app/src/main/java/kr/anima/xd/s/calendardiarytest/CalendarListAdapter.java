package kr.anima.xd.s.calendardiarytest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alfo6-10 on 8/24/2017.
 */

public class CalendarListAdapter extends RecyclerView.Adapter<CalendarListAdapter.ViewHolder> {

    private Context context;

    public CalendarListAdapter() {
    }

    public CalendarListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    @Override
    public CalendarListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_calendar_list, parent, false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle;
        TextView tvTime;
        TextView tvDescription;
        TextView tvPurpose;
        ImageView ivBtn;

        private View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle=itemView.findViewById(R.id.tvTitle);
            tvTime=itemView.findViewById(R.id.tvTime);
            tvDescription=itemView.findViewById(R.id.tvDescription);
            tvPurpose=itemView.findViewById(R.id.tvPurpose);
            ivBtn=itemView.findViewById(R.id.ivBtn);
            ivBtn.setOnClickListener(listener);
        }
    }
}
