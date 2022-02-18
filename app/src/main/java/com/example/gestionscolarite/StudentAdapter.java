package com.example.gestionscolarite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    RecyclerView rv_student;
    List<Student> studentsList;
    Context context;
    final View.OnClickListener onClickListener=new MyOnclickListener();

    public StudentAdapter(Context context,List<Student> studentsList,RecyclerView rv_student){
        this.context = context;
        this.rv_student=rv_student;
        this.studentsList=studentsList;


    }




    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView student_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            student_name=itemView.findViewById(R.id.student_name);

        }
    }




    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view=layoutInflater.inflate(R.layout.single_student,viewGroup,false);
        view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentsList.get(position);
        holder.student_name.setText(student.getNom()+" "+student.getPrenom());


    }

    @Override
    public int getItemCount() {
        return studentsList.size();
    }


    private class MyOnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition=rv_student.getChildLayoutPosition(v);
            String FName=studentsList.get(itemPosition).getPrenom();
            String LName=studentsList.get(itemPosition).getNom();
            String Cne=studentsList.get(itemPosition).getCne();
            Toast.makeText(context, ""+LName+" "+FName+" | "+Cne, Toast.LENGTH_LONG).show();


        }
    }
}
