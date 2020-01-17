package com.example.rxjava.listchallenge;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<Task> tasks;
    private int travelButtonVisibility=-1;
    private OnButtonClickListener mOnButtonClickListener;

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        mOnButtonClickListener = onButtonClickListener;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView, idTextView;
        private Button travelButton, travellingButton, workButton, stopButton;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.name_text_view);
            idTextView = itemView.findViewById(R.id.id_text_view);
            travelButton = itemView.findViewById(R.id.travel_button);
            travellingButton = itemView.findViewById(R.id.travelling_button);
            workButton = itemView.findViewById(R.id.work_button);
            stopButton = itemView.findViewById(R.id.stop_button);
        }
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListAdapter.ListViewHolder holder, final int position) {
        final Task task = tasks.get(position);

        holder.nameTextView.setText(task.getName());
        holder.idTextView.setText(String.valueOf(task.getId()));
        holder.travelButton.setText(R.string.start_travel);
        holder.travellingButton.setText(R.string.travelling);


        if (travelButtonVisibility <= 0) {
            holder.travelButton.setVisibility(View.VISIBLE);
        }
        else if (position == travelButtonVisibility){
            holder.travelButton.setVisibility(View.GONE);
            holder.travellingButton.setVisibility(View.VISIBLE);
            holder.workButton.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundColor(Color.parseColor("#FFECB3"));
        }

        holder.travelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnButtonClickListener.onTravelButtonClick();
                travelButtonVisibility = position;
                holder.travelButton.setVisibility(View.GONE);

            }
        });
        holder.workButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.workButton.setText(R.string.working);
                holder.travellingButton.setVisibility(View.GONE);
                holder.stopButton.setVisibility(View.VISIBLE);
                holder.itemView.setBackgroundColor(Color.parseColor("#FFCDD2"));
            }
        });
        holder.stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.travelButton.setText(R.string.start_travel);
                holder.travellingButton.setVisibility(View.GONE);
                holder.workButton.setText(R.string.work);
                holder.workButton.setVisibility(View.GONE);
                holder.stopButton.setVisibility(View.GONE);
                holder.itemView.setBackgroundColor(Color.TRANSPARENT);
                travelButtonVisibility = -1;
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasks == null ? 0 : tasks.size();
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public interface OnButtonClickListener {
        void onTravelButtonClick();
    }
}
