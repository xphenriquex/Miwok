package com.exemplo.android.miwok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        ArrayList<String> words = new ArrayList<>();

        words.add("um");
        words.add("dois");
        words.add("três");
        words.add("quatro");
        words.add("cinco");
        words.add("seis");
        words.add("sete");
        words.add("oito");
        words.add("nove");
        words.add("dez");


        ArrayAdapter<String> itemsAdpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, words);

        ListView listView = findViewById(R.id.rootView);

        listView.setAdapter(itemsAdpter);
    }
}


