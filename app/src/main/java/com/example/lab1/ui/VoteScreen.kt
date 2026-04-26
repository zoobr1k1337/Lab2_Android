package com.example.lab1.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab1.viewmodel.VoteViewModel

@Composable
fun VoteScreen(viewModel: VoteViewModel = viewModel()) {
    val options by viewModel.sortedOptions.collectAsState()
    val leader by viewModel.leader.collectAsState()
    var newOptionText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "🏆 Голосування",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))

        if (leader == null) {
            Text(
                text = "Голосування ще не розпочато",
                fontSize = 18.sp,
                color = Color.Gray
            )
        } else {
            Text(
                text = "Лідер: ${leader!!.title} (${leader!!.votes} голосів)",
                fontSize = 18.sp,
                color = Color.Gray
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = newOptionText,
                onValueChange = { newOptionText = it },
                placeholder = { Text("Додати варіант...") },
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    viewModel.addOption(newOptionText)
                    newOptionText = ""
                },
                modifier = Modifier.height(56.dp),
                shape = RoundedCornerShape(12.dp),
                enabled = newOptionText.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5C5CFF))
            ) {
                Text("+ Додати", fontSize = 16.sp)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(
                items = options,
                key = { option -> option.id }
            ) { option ->
                VoteCard(
                    option = option,
                    onVoteClick = { id -> viewModel.vote(id) }
                )
            }
        }
    }
}