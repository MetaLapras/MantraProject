package com.pasistence.mantrafingerprint.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.pasistence.mantrafingerprint.Interface.ItemClickListener;
import com.pasistence.mantrafingerprint.Models.WorkerList;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;
import com.pasistence.mantrafingerprint.Main.WorkerDetails;

import java.util.List;

class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public TextView name, number1, number2, gender;
    private ItemClickListener itemClickListener;

    public SearchViewHolder(View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.txt_worker_name);
        number1 = (TextView) itemView.findViewById(R.id.txt_worker_number);
        number2 = (TextView) itemView.findViewById(R.id.txt_worker_number2);
        gender = (TextView) itemView.findViewById(R.id.txt_worker_gender);


        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public  void  setItemClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }


    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;

    }
}



    public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {

       // private final List<String> suggestList;
        private Context context;
        private List<WorkerModel> workerModels;
        

        public SearchAdapter(Context context, List<WorkerModel> workerModels) {
            this.context = context;
            this.workerModels = workerModels;
        }



        @NonNull
        @Override
        public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View itemView = inflater.inflate(R.layout.custom_worker_template, parent, false);
            return new SearchViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
            holder.name.setText(workerModels.get(position).getName());
            holder.number1.setText(workerModels.get(position).getContact1());
            holder.number2.setText(workerModels.get(position).getContact2());
            holder.gender.setText(workerModels.get(position).getGender());


            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int position, boolean isLongClick) {
                    if (isLongClick) {
                        Context context = view.getContext();

                    /*    Intent intent = new Intent(context, WorkerDetails.class);
                        context.startActivity(intent);*/

                        Toast.makeText(context, "Long Click : ", Toast.LENGTH_SHORT).show();
                    }

                    else
                        Toast.makeText(context, "  "+workerModels.get(position), Toast.LENGTH_SHORT).show();

                }

            });

        }

        @Override
        public int getItemCount() {
            return workerModels.size();
        }
    }


   /* public TextView text_firstname,text_empid,text_contactnum;
    public SearchViewHolder(View itemView) {
        super(itemView);

        text_firstname = (TextView)itemView.findViewById(R.id.text_first_name);
        text_empid = (TextView)itemView.findViewById(R.id.text_empId);
        text_contactnum = (TextView)itemView.findViewById(R.id.text_first_name);

    }
}

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder>{

    private Context context;
    private List<WorkerList> workerLists;

    public SearchAdapter(Context context, List<WorkerList> workerLists) {
        this.context = context;
        this.workerLists = workerLists;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.activity_item_member_list,parent,false);
        return new SearchViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.text_firstname.setText(workerLists.get(position).getFirstName());
        holder.text_empid.setText(workerLists.get(position).getEmpId());
        holder.text_contactnum.setText(workerLists.get(position).getContactNo());

    }

    @Override
    public int getItemCount() {
        return workerLists.size();
    }
}*/


