package deti.icm.hw1

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class WellNessTask(
    val id: Int,
    val label: String,
    watched: Boolean = false
) {
    var checked by mutableStateOf(watched)
}