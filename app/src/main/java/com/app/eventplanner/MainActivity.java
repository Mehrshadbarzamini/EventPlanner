package com.app.eventplanner;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.eventplanner.adapter.PlanAdapter;
import com.app.eventplanner.model.Plan;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ImageView profileImageView;
    private ImageView addPlanImageView;
    private RecyclerView plansRecyclerView;
    private PlanAdapter planAdapter;
    private List<Plan> plansList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profileImageView = findViewById(R.id.profile_image);
        addPlanImageView = findViewById(R.id.add_plan);
        plansRecyclerView = findViewById(R.id.plans_recycler_view);

        // Setup RecyclerView
        plansList = new ArrayList<>();
        planAdapter = new PlanAdapter(this, plansList);
        plansRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        plansRecyclerView.setAdapter(planAdapter);

        // Dummy data
        addDummyPlans();

        profileImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle profile image click
                Toast.makeText(MainActivity.this, "Profile Image Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        addPlanImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle add plan button click
                Toast.makeText(MainActivity.this, "Add Plan Clicked", Toast.LENGTH_SHORT).show();
                // Code to add new plan
            }
        });
    }

    private void addDummyPlans() {
        // Add some dummy plans
        plansList.add(new Plan("Work", "Complete the report"));
        plansList.add(new Plan("Gym", "Workout session at 5 PM"));
        planAdapter.notifyDataSetChanged();
    }
}
