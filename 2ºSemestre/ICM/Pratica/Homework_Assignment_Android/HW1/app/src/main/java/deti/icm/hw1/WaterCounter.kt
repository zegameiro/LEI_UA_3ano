package deti.icm.hw1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatelessCounter(
    onShowModal: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Button(onClick = onShowModal, Modifier.padding(top = 8.dp)) {
            Text("Add a new Movie")
        }
    }
}

@Composable
fun StatefulModalCounter(
    modifier: Modifier = Modifier,
    viewModel: WellNessViewModel
) {
    var showModal by rememberSaveable { mutableStateOf(false) }
    
    StatelessCounter( { showModal = true }, modifier)

    TaskInputModal(
        isVisible = showModal,
        onDismiss = { showModal = false },
        onSubmit = { movieName ->
            viewModel.add(movieName)
            showModal = false
        }
    )
}