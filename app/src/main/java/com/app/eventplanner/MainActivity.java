package com.app.eventplanner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
                showAddPlanDialog();
            }
        });
    }

    private void addDummyPlans() {
        // Add some dummy plans
        plansList.add(new Plan("Work", "Complete the report"));
        plansList.add(new Plan("Gym", "Workout session at 5 PM"));
        plansList.add(new Plan("College", "My Lectures and Homeworks"));
        plansList.add(new Plan("Chores", ""));
        planAdapter.notifyDataSetChanged();
    }

    private void showAddPlanDialog() {
        // Inflate the dialog layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_add_plan, null);

        // Create the dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);

        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        // Get references to the dialog views
        EditText etPlanTitle = dialogView.findViewById(R.id.et_plan_title);
        EditText etPlanDescription = dialogView.findViewById(R.id.et_plan_description);
        Button btnAddPlan = dialogView.findViewById(R.id.btn_add_plan);

        // Set the button click listener
        btnAddPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String planTitle = etPlanTitle.getText().toString();
                String planDescription = etPlanDescription.getText().toString();

                if (planTitle.isEmpty() || planDescription.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Add the new plan to the list and notify the adapter
                    plansList.add(new Plan(planTitle, planDescription));
                    planAdapter.notifyItemInserted(plansList.size() - 1);
                    dialog.dismiss();
                }
            }
        });
    }
}
