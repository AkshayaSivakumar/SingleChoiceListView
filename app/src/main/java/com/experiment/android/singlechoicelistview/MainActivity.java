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
    private ArrayList<LanguageModel> langList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvItems = (ListView) findViewById(R.id.lv_list);
        btnConfirm = (Button) findViewById(R.id.btn_confirm);

        createDataSet();

        ListViewAdapter adapter = new ListViewAdapter(this, R.layout.layout_lv_item, langList);
        adapter.setSelectedPosition(1);
        lvItems.setAdapter(adapter);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Item position " + adapter.getSelectedPosition());
                System.out.println("Item " + ((LanguageModel) adapter.getSelectedItem()).getLanguageName());
            }
        });
    }

    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("Item position " + position);
                System.out.println("Item " + ((LanguageModel) parent.getItemAtPosition(position)).getLanguageName());
            }
        };
    }

    private void createDataSet() {
        langList = new ArrayList<>();
        langList.add(new LanguageModel("English", "ENG"));
        langList.add(new LanguageModel("Tamil", "TAM"));
        langList.add(new LanguageModel("Hindi", "HIN"));
    }
}