package com.example.lab04;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etTaskTitle, etTaskCategory;
    private Button btnAddTask;
    private RecyclerView recyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etTaskTitle = findViewById(R.id.etTaskTitle);
        etTaskCategory = findViewById(R.id.etTaskCategory);
        btnAddTask = findViewById(R.id.btnAddTask);
        recyclerView = findViewById(R.id.recyclerViewTasks);


        taskList = new ArrayList<>();
        taskList.add(new Task("Zrobić sprawozdanie", "Szkoła"));
        taskList.add(new Task("Zrobić zakupy spożywcze", "Dom"));


        taskAdapter = new TaskAdapter(taskList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);


        btnAddTask.setOnClickListener(v -> addTask());
    }

    private void addTask() {
        String title = etTaskTitle.getText().toString().trim();
        String category = etTaskCategory.getText().toString().trim();


        if (TextUtils.isEmpty(title)) {
            etTaskTitle.setError("Nazwa zadania jest wymagana!");
            return;
        }

        if (TextUtils.isEmpty(category)) {
            category = "Inne";
        }


        Task newTask = new Task(title, category);
        taskList.add(newTask);


        taskAdapter.notifyItemInserted(taskList.size() - 1);
        recyclerView.scrollToPosition(taskList.size() - 1);


        etTaskTitle.setText("");
        etTaskCategory.setText("");
        etTaskTitle.setError(null);

        Toast.makeText(this, "Dodano zadanie pomyślnie!", Toast.LENGTH_SHORT).show();
    }
}