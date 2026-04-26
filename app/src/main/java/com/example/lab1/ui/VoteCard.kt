package com.example.lab1.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab1.model.VoteOption

@Composable
fun VoteCard(
    option: VoteOption,
    onVoteClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE0E0FF))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = option.title,
                fontSize = 20.sp,
                color = Color.Black
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = option.votes.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5C5CFF),
                    modifier = Modifier.padding(end = 12.dp)
                )
                FilledIconButton(
                    onClick = { onVoteClick(option.id) },
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = Color(0xFFE8E8FF),
                        contentColor = Color(0xFF5C5CFF)
                    ),
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ThumbUp,
                        contentDescription = "Vote",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}