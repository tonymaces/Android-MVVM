package com.tonymaces.architectural.desing.patterns.mvvm.View;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.tonymaces.architectural.desing.patterns.mvvm.R;
import com.tonymaces.architectural.desing.patterns.mvvm.ViewModel.TodoViewModel;

public class TodoActivity extends AppCompatActivity {

    public static String APP_TAG = TodoActivity.class.getName();
    private ListView taskView;
    private Button btNewTask;
    private EditText etNewTask;
    private ITaskListManager delegate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        this.delegate = new TodoViewModel(this);
        this.taskView = (ListView) this.findViewById(R.id.tasklist);
        this.btNewTask= (Button) this.findViewById(R.id.btNewTask);
        this.etNewTask = (EditText) this.findViewById(R.id.etNewTask);
        this.delegate.registerTaskList(taskView);
        this.delegate.registerTaskAdder(btNewTask,etNewTask);

    }


}


