package com.example.todoapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todoapp.databinding.ActivityHomeScreenBinding;
import com.example.todoapp.models.Todo;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {
    ArrayList<Todo> todos = new ArrayList<Todo>();

    ArrayList<Todo> alltodos = new ArrayList<Todo>();
    CustomAdapter adapter;
    ListView l;
    private ActivityHomeScreenBinding binding;
    ActivityResultLauncher<Intent> startActivityForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == AppCompatActivity.RESULT_OK) {
                    Intent data = result.getData();
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        todos.add(new Todo("shopping", "aaaaa "));
        todos.add(new Todo("dot net", "revision"));

        alltodos = todos;


        setContentView(R.layout.activity_home_screen);

        AutoCompleteTextView search =(AutoCompleteTextView) findViewById(R.id.filterTextview);

        search.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                filterData(s.toString());
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        l = findViewById(R.id.listPasswords);
        refreshData();
        l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDialogModifTodo(todos.get(position));
                return false;
            }
        });
        Button add = findViewById(R.id.AddBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAddTodo();
            }
        });
    }
    private void filterData(String query){
        todos = new ArrayList<>();
        for (Todo p : alltodos){
            if (p.getTitle().toLowerCase().contains(query.toLowerCase())){
                todos.add(p);
            }
        }
        adapter = new CustomAdapter(HomeScreen.this, todos);
        l.setAdapter(adapter);
    }

    private void showDialogAddTodo(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.fragment_addtodo);

        Todo todo = new Todo("","");

        EditText title = dialog.findViewById(R.id.titleAdd);
        title.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                todo.setTitle(s.toString());

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        Button addBtn = dialog.findViewById(R.id.addTodo);
        EditText title_ = dialog.findViewById(R.id.titleAdd);
        EditText priority = dialog.findViewById(R.id.priorityAdd);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                todo.setTitle(title_.getText().toString());
                todo.setPriority(priority.getText().toString());
                todos.add(todo);
                adapter = new CustomAdapter(HomeScreen.this, todos);
                alltodos = todos;
                l.setAdapter(adapter);
                Toast.makeText(getApplicationContext(), "New action added", Toast.LENGTH_SHORT).show();

            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
    private void showDialogModifTodo(Todo todo){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_modif_todo);


        EditText title = dialog.findViewById(R.id.titleModif);
        title.setText(todo.getTitle());
        title.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                todo.setTitle(s.toString());

            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });
        Button addBtn = dialog.findViewById(R.id.saveTodoModif);

        EditText priority = dialog.findViewById(R.id.priorityModif);
        priority.setText(todo.getPriority());
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                todo.setTitle(title.getText().toString());
                todo.setPriority(priority.getText().toString());
//                todos.add(todo);
//                adapter = new CustomAdapter(HomeScreen.this, todos);
//                alltodos = todos;
//                l.setAdapter(adapter);
                Toast.makeText(getApplicationContext(), "Action modified", Toast.LENGTH_SHORT).show();

            }
        });
        Button cancel = dialog.findViewById(R.id.cancelTodo);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }
    void refreshData(){

        adapter = new CustomAdapter(HomeScreen.this, todos);
        l.setAdapter(adapter);
    }

}