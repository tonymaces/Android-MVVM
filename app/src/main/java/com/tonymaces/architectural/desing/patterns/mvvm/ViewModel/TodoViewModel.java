package com.tonymaces.architectural.desing.patterns.mvvm.ViewModel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.tonymaces.architectural.desing.patterns.mvvm.Model.TodoModel;
import com.tonymaces.architectural.desing.patterns.mvvm.View.ITaskListManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonym on 09/07/2016.
 */
public class TodoViewModel implements ITaskListManager {
    private TodoModel db_model;
    private List<String> tasks;
    private Context main_activity;
    private  ListView taskView;
    private  EditText newTask;

    public TodoViewModel(Context app_context) {
        tasks = new ArrayList<>();
        this.main_activity = app_context;
        db_model = new TodoModel(app_context);
    }

    public  void  addTask(View view){
        final ContentValues data = new ContentValues();
        data.put("title", ((TextView)view).getText().toString());
        db_model.addEntry(data);
    }

    public  void deleteTask(View view){
        db_model.deleteEntry("title=" + ((TextView)view).getText().toString() + "");
    }

    public  void deleteAll(){
        db_model.deleteEntry(null);
    }

    public  List<String> getTasks(){
        Cursor c = db_model.findAll();
        tasks.clear();

        if (c != null){
            c.moveToFirst();
            while (c.isAfterLast() == false){
                tasks.add(c.getString(0));
                c.moveToNext();
            }
            c.close();
        }
        return  tasks;
    }

    private  void  renderTodos(){
        taskView.setAdapter(new ArrayAdapter<String>(main_activity,
                android.R.layout.simple_list_item_1,getTasks().toArray(new String[]{})));
    }

    @Override
    public void registerTaskList(ListView list) {
        this.taskView = list;
        if (list.getAdapter()== null){
            renderTodos();
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener (){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteTask(view);
                renderTodos();
            }
        });
    }

    @Override
    public void registerTaskAdder(View button, EditText input) {
        this.newTask=input;
        button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                addTask(view);
                renderTodos();
                newTask.setText("");
            }
        });
    }
}
