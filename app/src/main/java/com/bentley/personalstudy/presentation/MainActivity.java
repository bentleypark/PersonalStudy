package com.bentley.personalstudy.presentation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;

import com.bentley.personalstudy.R;
import com.bentley.personalstudy.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private final int FRAGMENT_NEW_INDEX = 0;
    private final int FRAGMENT_BOOKMARK_INDEX = 1;
    private final int FRAGMENT_HISTORY_INDEX = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupViews();
    }

    private void setupViews() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        binding.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case FRAGMENT_NEW_INDEX: {
                        navController.navigate(R.id.newFragment);
                    }
                    case FRAGMENT_BOOKMARK_INDEX: {
                        navController.navigate(R.id.bookMarkFragment);
                    }
                    case FRAGMENT_HISTORY_INDEX: {
                        navController.navigate(R.id.historyFragment);
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}