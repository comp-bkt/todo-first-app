package com.example.android_todo_first

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.view.View
import android.widget.Button

class TodoActivity : AppCompatActivity() {
    private lateinit var mTodos: Array<String>
    private var mTodoIndex = 0
    private lateinit var todoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {

        /* check for saved state due to changes such as rotation
            and restore any saved state such as the TODO_INDEX */
        if (savedInstanceState != null) {
            mTodoIndex = savedInstanceState.getInt(TODO_INDEX, 0)
        }

        /* call the super class onCreate to complete the creation
            of activity with  any state changes */super.onCreate(savedInstanceState)

        /*  Refactor model
            read into mTodos array from res/values/strings.xml */
        val res = resources
        mTodos = res.getStringArray(R.array.todos)

        /* set the user interface layout for this Activity */
        setContentView(R.layout.activity_todo)

        /* initialize member TextView so we can manipulate it later */
        todoTextView = findViewById<View>(R.id.textViewTodo) as TextView

        /* display the first task from mTodo array in the TodoTextView */
        todoTextView.text = mTodos[mTodoIndex]

        /* setup navigation buttons */
        /* next button to cycle through mTodos */
        val buttonNext: Button
        buttonNext = findViewById<View>(R.id.buttonNext) as Button

        /* OnClick listener for the  Next button */
        buttonNext.setOnClickListener { //check index boundary for mTodos
            if (mTodoIndex < mTodos.size - 1) {
                mTodoIndex += 1
            } else {
                mTodoIndex = 0
            }


            //   mTodoIndex += 1;
            todoTextView.text = mTodos[mTodoIndex]
        }

        /* TODO setup previous button object and listener
        *   to cycle through mTodos */
    }

    /*
    This callback is called only when there is a saved instance that is previously saved by using
    onSaveInstanceState(). We restore some state in onCreate(), while we can optionally restore
    other state here, possibly usable after onStart() has completed.
    The savedInstanceState Bundle is same as the one used in onCreate(). */
    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        mTodoIndex = savedInstanceState.getInt(TODO_INDEX)
        todoTextView.text = mTodos[mTodoIndex]
    }

    /* invoked when the activity may be temporarily destroyed, save the instance state here */
    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putInt(TODO_INDEX, mTodoIndex)
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(savedInstanceState)
    }

    companion object {
        /* In case of state change, such as rotating the phone,
       store the mTodoIndex */
        private const val TODO_INDEX = "com.example.android_todo_first.todoIndex"
    }
}