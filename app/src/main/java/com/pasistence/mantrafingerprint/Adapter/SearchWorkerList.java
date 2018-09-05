package com.pasistence.mantrafingerprint.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pasistence.mantrafingerprint.Interface.ItemClickListener;
import com.pasistence.mantrafingerprint.Models.WorkerModel;
import com.pasistence.mantrafingerprint.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

class SearchWorkerListHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{

    public TextView workername, workernumber1, workernumber2, workergender,workerId;
    public CircleImageView workerimg;
    private ItemClickListener itemClickListener;
    public Button BtnEdit,BtnDelete,Btndetails;
    public LinearLayout layoutWorker;

    public SearchWorkerListHolder(View itemView) {


        super(itemView);
        workerId       = (TextView)itemView.findViewById(R.id.txt_worker_id) ;
        workername     = (TextView) itemView.findViewById(R.id.txt_worker_name);
        workernumber1  = (TextView) itemView.findViewById(R.id.txt_worker_number);
        workernumber2  = (TextView) itemView.findViewById(R.id.txt_worker_number2);
        workergender   = (TextView) itemView.findViewById(R.id.txt_worker_gender);
        workerimg     = (CircleImageView)itemView.findViewById(R.id.worker_photo);
        BtnEdit       = (Button)itemView.findViewById(R.id.btn_worker_edit);
        BtnDelete     = (Button)itemView.findViewById(R.id.btn_worker_Delete);
        Btndetails    = (Button)itemView.findViewById(R.id.btn_worker_Details);
        layoutWorker  = (LinearLayout)itemView.findViewById(R.id.worker_linear_layout) ;



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

    /*transferBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.transfer_dialog, null);
            layoutToAdd.addView(view);


        }
    });*/

    @Override
    public boolean onLongClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),true);
        return true;

    }
}



class SearchWokerList extends RecyclerView.Adapter<SearchWorkerListHolder> {

    // private final List<String> suggestList;
    private Context context;
    private List<WorkerModel> workerModels;
    Activity activity;


    public SearchWokerList(Activity activity,Context context, List<WorkerModel> workerModels) {
        this.activity = activity;
        this.context = context;
        this.workerModels = workerModels;
    }



    @NonNull
    @Override
    public SearchWorkerListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.custom_worker_template, parent, false);
        return new SearchWorkerListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchWorkerListHolder holder, int position) {

        holder.workerId.setText("Worker Id : "+workerModels.get(position).getAdharcard_id());
        holder.workername.setText("Name : "+workerModels.get(position).getName());
        holder.workernumber1.setText("Contact no : "+workerModels.get(position).getContact1());
        holder.workernumber2.setText("alternate no : "+workerModels.get(position).getContact2());
        holder.workergender.setText("gender : "+workerModels.get(position).getGender());


        Glide.with(context)
                .load(workerModels.get(position).getImageUrl().toString())
                .into(holder.workerimg);

        holder.BtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTransferDialog();

            }
        });


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

    private void showTransferDialog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        View changePwdLayout = layoutInflater.inflate(R.layout.transfer_dialog,null);

        alertDialog.setView(changePwdLayout);

        alertDialog.show();


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


