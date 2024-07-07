package deti.icm.hw1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskInputModal(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (String) -> Unit
) {
    if (isVisible) {
        var taskName by remember { mutableStateOf("") }
        AlertDialog(
            onDismissRequest = { onDismiss() },
            modifier = Modifier,
            properties = DialogProperties(usePlatformDefaultWidth = false),
            content = {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Add New Movie")
                    TextField(
                        value = taskName,
                        onValueChange = { taskName = it },
                        label = { Text("Movie Name") }
                    )
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        Button(onClick = { onSubmit(taskName); onDismiss() }) {
                            Text("Add")
                        }
                        Spacer(Modifier.width(8.dp))
                        Button(onClick = { onDismiss() }) {
                            Text("Cancel")
                        }
                    }
                }
            }
        )
    }
}
