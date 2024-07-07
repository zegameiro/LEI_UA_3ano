package deti.icm.hw1

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WellNessScreen(
    modifier: Modifier = Modifier,
    wellNessViewModel: WellNessViewModel = viewModel()
) {
    Column (
        modifier = modifier
    ) {
        StatefulModalCounter(modifier, wellNessViewModel)
        WellnessTasksList(
            modifier = modifier,
            viewModel = wellNessViewModel,
            onCheckedTask = { task, checked ->
                wellNessViewModel.changeTaskChecked(task, checked)
            },
            onCloseTask = { task -> wellNessViewModel.remove(task) }
        )
    }
}