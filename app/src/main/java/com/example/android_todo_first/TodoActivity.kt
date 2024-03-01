package com.example.android_todo_first

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_todo_first.ui.theme.TodoTheme

class TodoActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {
            TodoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoWithButtons(LocalContext.current.resources.getStringArray(R.array.todos))
                }
            }
        }
    }
}

@Composable
fun TodoWithButtons(todos:Array<String> , modifier: Modifier = Modifier) {
    var stringIndex by rememberSaveable { mutableStateOf(0) }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(
            text = "${todos[stringIndex]}!",
            fontSize = 48.sp,
            lineHeight = 48.sp,
            textAlign = TextAlign.Center
        )
        Spacer(
            modifier = Modifier.size(32.dp)
        )


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { stringIndex = onPrevButton(stringIndex, todos.size) }) {
                Text(stringResource(R.string.prev))
            }

            Button(onClick = { stringIndex = onNextButton(stringIndex, todos.size) }) {
                Text(stringResource(R.string.next))
            }
        }
    }
}

fun onPrevButton(index:Int, length:Int): Int {
    return if (index > 0) {
        index - 1
    } else {
        length - 1
    }
}


fun onNextButton(index:Int, length:Int): Int {
    return if (index < length - 1) {
        index + 1
    } else {
        0
    }
}

@Preview(showBackground = true)
@Composable
fun TodosPreview() {
    TodoTheme {
       TodoWithButtons(LocalContext.current.resources.getStringArray(R.array.todos))
    }
}

