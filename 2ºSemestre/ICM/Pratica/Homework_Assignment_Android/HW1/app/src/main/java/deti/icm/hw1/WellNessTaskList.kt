package deti.icm.hw1

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.items

@Composable
fun WellnessTasksList(
    modifier: Modifier = Modifier,
    viewModel: WellNessViewModel,
    onCloseTask: (WellNessTask) -> Unit,
    onCheckedTask: (WellNessTask, Boolean) -> Unit
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = viewModel.tasks,
            key = { task -> task.id }
        ) { task ->
            WellNessTaskItem(
                taskName = task.label,
                checked = task.checked,
                onCheckedChange = { checked -> onCheckedTask(task, checked) },
                onClose = { onCloseTask(task) }
            )
        }
    }
}