package com.dms.dmssevent.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dms.dmssevent.R;
import com.dms.dmssevent.adapters.TeamsListAdapter;
import com.dms.dmssevent.common.DmsEventsAppController;
import com.dms.dmssevent.interfaces.TeamsListItemClickCallBack;
import com.dms.dmssevent.models.TeamsListModel;

import java.util.ArrayList;


public class TeamsFragment extends Fragment implements TeamsListItemClickCallBack {
    View rootView;
    TeamsListAdapter teamsListAdapter;
    ListView listViewTeamsList;
    ArrayList<TeamsListModel> teamsListModelArrayList=new ArrayList<TeamsListModel>();
    DmsEventsAppController controller;
    public TeamsFragment() {
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
        rootView= inflater.inflate(R.layout.fragment_teams, container, false);
        controller=(DmsEventsAppController)getActivity().getApplicationContext();
        listViewTeamsList=(ListView)rootView.findViewById(R.id.listViewTeamsList);
        listViewTeamsList.setEmptyView(rootView.findViewById(R.id.textViewNoResults));
        teamsListModelArrayList=controller.getTeamsListModelArrayList();
        teamsListAdapter=new TeamsListAdapter(getActivity(),teamsListModelArrayList,this);
        listViewTeamsList.setAdapter(teamsListAdapter);
        return rootView;
    }

    @Override
    public void teamsItemLick(View view, int position) {
      /*  TeamsListActivity.listItemSelectPosition=position;
        Intent i=new Intent(getActivity(),TeamsListActivity.class);
        startActivity(i);*/
    }
}
