package com.example.room.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.room.theme.RoomTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Main : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Ui()
                }
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this@Main, message, Toast.LENGTH_SHORT).show()
    }

    @Composable
    @Preview(showBackground = true)
    fun Ui() {
        var name by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        RoomTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
                    .padding(top = 30.dp),
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = name,
                    onValueChange = { name = it },
                    label = { Text(text = "Name") }
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text(text = "Contact Number") },
                    textStyle = TextStyle(fontSize = 20.sp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next)
                )

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { showMessage("Save") },
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp),
                    ) { Text(text = "Save") }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = { showMessage("Display") },
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp)
                    ) { Text(text = "Display") }
                }
            }
        }
    }
}
