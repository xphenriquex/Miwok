package com.exemplo.android.miwok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CategoryAdapter adapter = new CategoryAdapter(getSupportFragmentManager());
        adapter.addFragmentTab(new NumbersFragment(), "Números");
        adapter.addFragmentTab(new FamilyFragment(), "Família");
        adapter.addFragmentTab(new ColorsFragment(), "Cores");
        adapter.addFragmentTab(new PhrasesFragment(), "Frases");

        ViewPager viewPager = findViewById(R.id.category_pager_view);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }


}

