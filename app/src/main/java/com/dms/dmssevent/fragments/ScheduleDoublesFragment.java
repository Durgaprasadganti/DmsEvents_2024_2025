package com.dms.dmssevent.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dms.dmssevent.R;
import com.dms.dmssevent.adapters.MyGamesScheduleDoublesAdapter;
import com.dms.dmssevent.common.DmsEventsAppController;
import com.dms.dmssevent.models.ScheduleDoublesListModel;

import java.util.ArrayList;


public class ScheduleDoublesFragment extends Fragment {
    View rootView;
    MyGamesScheduleDoublesAdapter myGamesDoublesAdapter;
    ListView listViewScheduleList;
    ArrayList<ScheduleDoublesListModel> scheduleListGamesModelArrayList=new ArrayList<ScheduleDoublesListModel>();
    DmsEventsAppController controller;
    public ScheduleDoublesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_schedule, container, false);

        controller=(DmsEventsAppController)getActivity().getApplicationContext();
        listViewScheduleList=(ListView)rootView.findViewById(R.id.listViewScheduleList);
        listViewScheduleList.setEmptyView(rootView.findViewById(R.id.textViewNoResults));
        scheduleListGamesModelArrayList=controller.getTodayDoublesListOfGamesTypeScheduleArrayList();
        myGamesDoublesAdapter=new MyGamesScheduleDoublesAdapter(getActivity(),scheduleListGamesModelArrayList);
        listViewScheduleList.setAdapter(myGamesDoublesAdapter);
        return rootView;
    }

}
