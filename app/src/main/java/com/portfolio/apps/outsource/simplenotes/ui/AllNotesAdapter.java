package com.portfolio.apps.outsource.simplenotes.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.portfolio.apps.outsource.simplenotes.AppController;
import com.portfolio.apps.outsource.simplenotes.R;
import com.portfolio.apps.outsource.simplenotes.RecyclerViewClickListener;
import com.portfolio.apps.outsource.simplenotes.model.Notes;
import com.portfolio.apps.outsource.simplenotes.repository.NotesRepository;
import com.portfolio.apps.outsource.simplenotes.utils.AppUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AllNotesAdapter extends RecyclerView.Adapter<AllNotesAdapter.ViewHolder> {

    private ArrayList<Notes> localDataSet;
    private static  RecyclerViewClickListener recyclerViewClickListener = new MainActivity();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notes_list_item_row, parent, false);

        return new ViewHolder(view, recyclerViewClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull  AllNotesAdapter.ViewHolder viewHolder, int position) {
        Context context = AppController.getContext();
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTitleOfTheNote().setText(localDataSet.get(position).getId()+" "+localDataSet.get(position).getTitle());
        viewHolder.getDescriptionOfTheNote().setText(localDataSet.get(position).getDescription());
        viewHolder.getTimestampOfTheNote().setText(AppUtils.getFormattedDateString(localDataSet.get(position).getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        TextView description;
        TextView timestamp;
        private ImageView deleteBtn;
        public ViewHolder(View itemView, RecyclerViewClickListener recyclerViewClickListener) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title_of_note);
            description = (TextView) itemView.findViewById(R.id.description_of_note);
            timestamp = (TextView) itemView.findViewById(R.id.timestamp);
            deleteBtn = (ImageView) itemView.findViewById(R.id.delete);
            deleteBtn.setOnClickListener(this);
        }
        public TextView getTitleOfTheNote(){
            return  title;
        }

        public TextView getDescriptionOfTheNote(){
            return  description;
        }

        public TextView getTimestampOfTheNote(){
            return  timestamp;
        }


        @Override
        public void onClick(View v) {
            int id = getAbsoluteAdapterPosition()+1;
            Log.i("Snath, ID ","ID "+id);
            recyclerViewClickListener.onClick(itemView,id );
        }
    }

    public AllNotesAdapter(ArrayList<Notes> dataset, RecyclerViewClickListener listener) {
        this.localDataSet = dataset;
        this.recyclerViewClickListener = listener;
    }
    public Notes getItem(int position) {
        return localDataSet.get(position);
    }

}
