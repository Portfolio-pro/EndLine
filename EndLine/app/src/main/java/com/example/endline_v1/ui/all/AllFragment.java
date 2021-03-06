package com.example.endline_v1.ui.all;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.endline_v1.DisplayDataFromFirebase;
import com.example.endline_v1.R;

public class AllFragment extends Fragment {

    private AllViewModel AllViewModel;
    private RecyclerView recyclerView;
    private Activity activity;
    private Spinner spinner_orderby;
    private String orderby;

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        AllViewModel = new ViewModelProvider(this).get(AllViewModel.class);
        View root = inflater.inflate(R.layout.fragment_all, container, false);
        /*final TextView textView = root.findViewById(R.id.text_all);
        AllViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView_all);
        spinner_orderby = (Spinner) root.findViewById(R.id.spinner_orderby);

        spinner_orderby.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                orderby = parent.getItemAtPosition(position).toString();
                DisplayDataFromFirebase displayer = new DisplayDataFromFirebase("All",orderby, recyclerView, activity.getApplicationContext());
                displayer.DisplayData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return root;
    }
}