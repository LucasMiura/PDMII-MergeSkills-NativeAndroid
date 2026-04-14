package com.fatec.merge_skills.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fatec.merge_skills.ui.theme.Surface

@Composable
fun StitchCard(
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    containerColor: Color = Surface,
    content: @Composable ColumnScope.() -> Unit
) {
    ElevatedCard(
        onClick = { onClick?.invoke() },
        enabled = onClick != null,
        modifier = modifier,
        colors = CardDefaults.elevatedCardColors(
            containerColor = containerColor
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 6.dp
        ),
        content = content
    )
}
