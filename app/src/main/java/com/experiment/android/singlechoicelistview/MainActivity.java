package com.experiment.android.singlechoicelistview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lvItems;
    private Button btnConfirm;
    private ArrayList<ItemsModel> itemsList;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.lv_list);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);

        createDataSet();

        adapter = new ListViewAdapter(this, R.layout.layout_lv_item, itemsList);
        adapter.setSelectedPosition(1);
        lvItems.setAdapter(adapter);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Item position " + adapter.getSelectedPosition());
                System.out.println("Item " + ((ItemsModel) adapter.getSelectedItem()).getItemName());
            }
        });
    }

    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetChanged();

                System.out.println("Item position " + position);
                System.out.println("Item " + ((ItemsModel) parent.getItemAtPosition(position)).getItemName());
            }
        };
    }

    private void createDataSet() {
        itemsList = new ArrayList<>();
        itemsList.add(new ItemsModel("Tamil Nadu", "TN"));
        itemsList.add(new ItemsModel("Telangana", "TS"));
        itemsList.add(new ItemsModel("Andhra Pradesh", "AP"));
    }
}