package com.xina.doctor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.github.aakira.expandablelayout.ExpandableRelativeLayout;
import com.xina.doctor.Models.PatientListDetails;
import com.xina.doctor.R;

import java.util.List;

public class PatientsListAdapter extends RecyclerView.Adapter<PatientsListAdapter.PatientsHolder> {
    public int i=0;
    Context mctx;
    List<PatientListDetails> patientsListModels;

    public PatientsListAdapter(Context mctx, List<PatientListDetails> patientsListModels) {
        this.mctx = mctx;
        this.patientsListModels = patientsListModels;
    }

    @Override
    public PatientsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        PatientsHolder patientsHolder = new PatientsHolder(view,mctx,patientsListModels);
        return patientsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final PatientsHolder holder, int position) {
        PatientListDetails patientsListModel = patientsListModels.get(position);
        holder.name.setText(patientsListModel.getName());

        holder.mobile_number.setText(patientsListModel.getMobile());
        holder.deseaseName.setText(patientsListModel.getDisease());
        holder.description.setText(patientsListModel.getDescription());
        holder.address.setText(patientsListModel.getAddress());

        holder.expandableButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.expandableLayout1.toggle();
                i++;
                if (i%2==0){
                    holder.expandableButton1.setText("More");

                }


                if(i%2==1){
                    holder.expandableButton1.setText("Less");

                }




            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mctx, "Hello", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return patientsListModels.size();
    }

    public class PatientsHolder extends RecyclerView.ViewHolder {
        TextView name, mobile_number,deseaseName,expandableButton1,address,description;
        ExpandableRelativeLayout expandableLayout1;
        public PatientsHolder(View itemView, Context mctx, List<PatientListDetails> patientsListModels) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            mobile_number =itemView.findViewById(R.id.mobile_number);
            deseaseName = itemView.findViewById(R.id.deseaseName);
            expandableButton1=itemView.findViewById(R.id.expandableButton1);
            expandableLayout1 = itemView.findViewById(R.id.expandableLayout1);
            address = itemView.findViewById(R.id.ViewAddess);
            description = itemView.findViewById(R.id.ViewDesc);


        }
    }
}
