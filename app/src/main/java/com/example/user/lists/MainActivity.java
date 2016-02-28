package com.example.user.lists;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Wietske Dotinga - 10781889
 * Todolist app with one screen. Todos can be added and can later be deleted with a longclick.
 * Using ArrayAdapter to add and delete todos from the listview.
 */
public class MainActivity extends AppCompatActivity {

    EditText todoEdit;
    ListView todoListview;
    Button addButton;

    ArrayList<String> todoArraylist;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On opening app check if there is a saved ArrayList of todos
        if (savedInstanceState != null) {
            todoArraylist = savedInstanceState.getStringArrayList("ARRAY_LIST");
        } else {
            todoArraylist = new ArrayList<>();
        }

        // Initialize listview, edittext and button
        todoListview = (ListView) findViewById(R.id.todoListview);
        todoEdit = (EditText) findViewById(R.id.todoEdit);
        addButton = (Button) findViewById(R.id.addButton);

        // Initialize adapter
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoArraylist);
        todoListview.setAdapter(adapter);

        // Set onclicklistener for the button
        addButton.setOnClickListener(buttonClicked);

        todoListview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                todoArraylist.remove(position);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    View.OnClickListener buttonClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Add the text from the edittext into the arraylist of todos
            String todoText = todoEdit.getText().toString();
            todoArraylist.add(todoText);
            adapter.notifyDataSetChanged();
            todoEdit.setText("");
        }
    };

    // TODO tekst file storen? Ipv in oninstancestate....?

    // Save todolist with onsaveinstancestate
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("ARRAY_LIST", todoArraylist);
    }

    // Restore the saved todolist on restoring the app
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        todoArraylist = savedInstanceState.getStringArrayList("ARRAY_LIST");
    }
}