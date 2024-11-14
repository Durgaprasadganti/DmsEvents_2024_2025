package com.dms.dmssevent.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dms.dmssevent.R;
import com.dms.dmssevent.adapters.MyGamesDoublesAdapter;
import com.dms.dmssevent.common.DmsEventsAppController;
import com.dms.dmssevent.models.TodayDoublesListModel;

import java.util.ArrayList;

/**
 * Created by Sandeep.Kumar on 02-02-2018.
 */

public class ResultsDoublesFragment extends Fragment {
    View rootView;
    MyGamesDoublesAdapter myGamesDoublesAdapter;
    ListView listViewScheduleList;
    ArrayList<TodayDoublesListModel> scheduleListGamesModelArrayList=new ArrayList<TodayDoublesListModel>();
    DmsEventsAppController controller;
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
        scheduleListGamesModelArrayList=controller.getTodayDoublesListOfGamesTypeResultArrayList();
        myGamesDoublesAdapter=new MyGamesDoublesAdapter(getActivity(),scheduleListGamesModelArrayList);
        listViewScheduleList.setAdapter(myGamesDoublesAdapter);
        return rootView;
    }

}

