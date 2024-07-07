package deti.icm.hw1

import androidx.lifecycle.ViewModel

class WellNessViewModel: ViewModel() {
    private val _tasks = getWellNessTasks().toMutableList()
    val tasks: List<WellNessTask>
        get() = _tasks

    fun remove(item: WellNessTask) {
        _tasks.remove(item)
    }

    fun add(movieName: String) {
        val newTask = WellNessTask(id = _tasks.size + 1, label = movieName)
        _tasks.add(newTask)
    }

    fun changeTaskChecked(item: WellNessTask, checked: Boolean) =
        _tasks.find { it.id == item.id }?.let { task ->
            task.checked = checked
        }
}

private fun getWellNessTasks() = List(5) { i -> WellNessTask(i, "Movie # $i") }