package com.app.eventplanner.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.app.eventplanner.R;
import com.app.eventplanner.model.Plan;
import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.PlanViewHolder> {

    private Context context;
    private List<Plan> plansList;

    public PlanAdapter(Context context, List<Plan> plansList) {
        this.context = context;
        this.plansList = plansList;
    }

    @NonNull
    @Override
    public PlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_plan, parent, false);
        return new PlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Plan plan = plansList.get(position);
        holder.planTitle.setText(plan.getTitle());
        holder.planDescription.setText(plan.getDescription());

        holder.planOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.planOptions);
                popupMenu.inflate(R.menu.plan_options_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete_plan:
                                plansList.remove(position);
                                notifyItemRemoved(position);
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return plansList.size();
    }

    static class PlanViewHolder extends RecyclerView.ViewHolder {

        TextView planTitle, planDescription;
        ImageView planOptions;

        public PlanViewHolder(@NonNull View itemView) {
            super(itemView);
            planTitle = itemView.findViewById(R.id.plan_title);
            planDescription = itemView.findViewById(R.id.plan_description);
            planOptions = itemView.findViewById(R.id.plan_options);
        }
    }
}
