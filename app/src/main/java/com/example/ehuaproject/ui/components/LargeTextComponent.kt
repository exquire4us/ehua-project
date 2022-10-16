package com.example.ehuaproject.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ehuaproject.ui.theme.EhuaAppTypography

@OptIn(ExperimentalTextApi::class)
@Composable
fun LargeTextComponent(
    modifier: Modifier,
    text: String,
    textSize: TextUnit = 24.sp,
    textColor: Color = Color.Black,
) {
    Text(
        text = text,
        style = EhuaAppTypography.titleLarge.copy(
            fontSize = textSize,
            fontWeight = FontWeight.Bold,
            color = textColor,
            textAlign = TextAlign.Left,
            lineHeight = 50.sp,


        ),
        modifier = modifier
    )
}
