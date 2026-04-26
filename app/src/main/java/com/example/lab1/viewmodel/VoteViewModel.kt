package com.example.lab1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab1.model.VoteOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class VoteViewModel : ViewModel() {
    private val _options = MutableStateFlow<List<VoteOption>>(
        listOf(
            VoteOption(title = "Піца", votes = 12),
            VoteOption(title = "Суші", votes = 8),
            VoteOption(title = "Бургер", votes = 5),
            VoteOption(title = "Салат", votes = 2)
        )
    )

    val sortedOptions: StateFlow<List<VoteOption>> = _options
        .map { list -> list.sortedByDescending { it.votes } }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val leader: StateFlow<VoteOption?> = _options
        .map { list ->
            val maxVotes = list.maxOfOrNull { it.votes } ?: 0
            if (maxVotes == 0) null else list.maxByOrNull { o -> o.votes }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    fun vote(id: String) {
        _options.value = _options.value.map { option ->
            if (option.id == id) {
                option.copy(votes = option.votes + 1)
            } else {
                option
            }
        }
    }

    fun addOption(title: String) {
        if (title.isNotBlank()) {
            val newOption = VoteOption(title = title.trim())
            _options.value = _options.value + newOption
        }
    }
}