package com.dms.dmssevent.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.dms.dmssevent.R;
import com.dms.dmssevent.adapters.MyGamesSingleAdapter;
import com.dms.dmssevent.common.DmsEventsAppController;
import com.dms.dmssevent.interfaces.WebServiceResponseCallBack;
import com.dms.dmssevent.models.TodaySinglesListModel;

import java.util.ArrayList;


public class MyGameSinglesFragment extends Fragment implements WebServiceResponseCallBack {
    View rootView;
    ListView listViewMatches;
    MyGamesSingleAdapter myGamesSingleAdapter;
    DmsEventsAppController controller;
    ProgressDialog progressDialog;
    LinearLayout emptyElement, awardDescription;
    TextView retryTextView;
    ArrayList<TodaySinglesListModel> myGamesModelArraySinglesList = new ArrayList<TodaySinglesListModel>();
    //String[] titles = {"Table Tennis", "Carroms", "Chess", "Cricket", "Table Tennis", "Carroms", "Chess", "Cricket"};
    //String[] matches = {"Match 1", "Match 2", "Match 3", "Match 4", "Match 5", "Match 6", "Match 7", "Match 8"};

    public MyGameSinglesFragment() {
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
        rootView = inflater.inflate(R.layout.fragment_todays_match, container, false);
        controller=(DmsEventsAppController)getActivity().getApplicationContext();
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading please wait....");
        progressDialog.setIndeterminate(false);
        progressDialog.setCanceledOnTouchOutside(false);
        emptyElement = (LinearLayout) rootView.findViewById(R.id.emptyElement);
        retryTextView = (TextView) rootView.findViewById(R.id.retryTextView);
        listViewMatches = (ListView) rootView.findViewById(R.id.listViewMatches);
        listViewMatches.setEmptyView(rootView.findViewById(R.id.textViewNoResults));
        myGamesModelArraySinglesList =controller.getMyGamesModelArraySinglesList();
        myGamesSingleAdapter=new MyGamesSingleAdapter(getActivity(), myGamesModelArraySinglesList);
        listViewMatches.setAdapter(myGamesSingleAdapter);
        return rootView;
    }

    @Override
    public void onServiceCallSuccess(String result) {

    }

    @Override
    public void onServiceCallFail(String error) {

    }
}
