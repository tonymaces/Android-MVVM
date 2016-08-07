package com.tonymaces.architectural.desing.patterns.mvvm.View;

import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by tonym on 09/07/2016.
 */
public interface ITaskListManager {
    void registerTaskList(ListView list);
    void registerTaskAdder(View button, EditText input);
}
