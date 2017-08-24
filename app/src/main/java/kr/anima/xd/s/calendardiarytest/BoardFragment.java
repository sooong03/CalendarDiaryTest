package kr.anima.xd.s.calendardiarytest;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class BoardFragment extends Fragment {

    private Context context;

    public BoardFragment() {
        // Required empty public constructor
    }

    public BoardFragment(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_board, container, false);
        return view;
    }

}
