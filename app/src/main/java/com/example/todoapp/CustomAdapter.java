package com.example.todoapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todoapp.models.Todo;

import java.util.ArrayList;


public class CustomAdapter extends ArrayAdapter {

    Activity context;
    ArrayList<Todo> todos;

    CustomAdapter(Activity cntx, ArrayList<Todo> todos){
        super(cntx, R.layout.customadapter, todos);
        this.context = cntx;
        this.todos = todos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final View result;

        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView =  inflater.inflate(R.layout.customadapter, parent, false);

        TextView title = (TextView) convertView.findViewById(R.id.titleAdapter);
        TextView priority = (TextView) convertView.findViewById(R.id.priorityAdapter);

        result = convertView;

        Todo todo =  todos.get(position);
        title.setText(todo.getTitle());
        priority.setText(todo.getPriority());



        result.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
//                Toast.makeText(CustomAdapter.super.getContext(), "Long Click", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return result;


    }






}