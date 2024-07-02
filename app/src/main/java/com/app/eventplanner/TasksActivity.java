package com.app.eventplanner;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.app.eventplanner.adapter.TaskAdapter;
import com.app.eventplanner.model.Task;
import java.util.ArrayList;
import java.util.List;

public class TasksActivity extends AppCompatActivity {

    private ImageView addTaskImageView, sortTaskImageView;
    private RecyclerView tasksRecyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> tasksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        addTaskImageView = findViewById(R.id.add_task);
        sortTaskImageView = findViewById(R.id.sort_task);
        tasksRecyclerView = findViewById(R.id.tasks_recycler_view);

        // Setup RecyclerView
        tasksList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, tasksList);
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksRecyclerView.setAdapter(taskAdapter);

        addTaskImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddTaskDialog();
            }
        });

        sortTaskImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSortTaskMenu(v);
            }
        });
    }

    private void showAddTaskDialog() {
        // Inflate the dialog layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_add_task, null);

        // Create the dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);

        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        // Get references to the dialog views
        EditText etTaskTitle = dialogView.findViewById(R.id.et_task_title);
        EditText etTaskDescription = dialogView.findViewById(R.id.et_task_description);
        EditText etTaskDeadline = dialogView.findViewById(R.id.et_task_deadline);
        Button btnAddTask = dialogView.findViewById(R.id.btn_add_task);

        // Set the button click listener
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskTitle = etTaskTitle.getText().toString();
                String taskDescription = etTaskDescription.getText().toString();
                String taskDeadline = etTaskDeadline.getText().toString();

                if (taskTitle.isEmpty() || taskDescription.isEmpty() || taskDeadline.isEmpty()) {
                    Toast.makeText(TasksActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Add the new task to the list and notify the adapter
                    tasksList.add(new Task(taskTitle, taskDescription, taskDeadline));
                    taskAdapter.notifyItemInserted(tasksList.size() - 1);
                    dialog.dismiss();
                }
            }
        });
    }

    private void showSortTaskMenu(View v) {
        PopupMenu popupMenu = new PopupMenu(this, v);
        popupMenu.inflate(R.menu.task_options_menu);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
//                    case R.id.sort_alphabetically:
//                        taskAdapter.sortTasksAlphabetically();
//                        return true;
//                    case R.id.sort_by_deadline:
//                        taskAdapter.sortTasksByDeadline();
//                        return true;
                    default:
                        return false;
                }
            }
        });
        popupMenu.show();
    }
}
