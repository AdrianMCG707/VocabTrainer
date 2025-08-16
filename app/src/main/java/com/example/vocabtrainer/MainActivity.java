package com.example.vocabtrainer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    View dashboardLayout;
    FragmentContainerView fragmentContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        dashboardLayout = findViewById(R.id.dashboardLayout); // this is a layout container
        fragmentContainerView = findViewById(R.id.fragmentContainerView); //learn fragment

        // 👇 Reference the dashboard title
        TextView title = findViewById(R.id.dashboardTitle);

        // ⏱️ Auto-hide after 3 seconds
        new Handler().postDelayed(() -> {
            title.setVisibility(View.GONE);
        }, 3000);

        // 👆 OR hide on tap
        dashboardLayout.setOnClickListener(v -> title.setVisibility(View.GONE));

        // Show dashboard by default
        showDashboard();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        showDashboard();
                        break;
                    case 1:
                        showFragment(new LearnFragment());
                        break;
                    case 2:
                        showFragment(new ProgressFragment());
                        break;
                    case 3:
                        showFragment(new SettingsFragment());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showDashboard() {
        dashboardLayout.setVisibility(View.VISIBLE);
        fragmentContainerView.setVisibility(View.GONE); // this is the learn fragment being closed
    }

    private void showFragment(Fragment fragment) {
        dashboardLayout.setVisibility(View.GONE);
        fragmentContainerView.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, fragment) // learn fragment
                .commit();
    }
}
