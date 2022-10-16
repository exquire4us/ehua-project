package com.example.ehuaproject.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ehuaproject.ui.theme.EhuaAppTypography
import com.example.ehuaproject.ui.theme.Red100

@Composable
fun GeneralButton(
    modifier: Modifier,
    buttonText: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = Red100,
        ), elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation =0.dp,
        ), shape = RoundedCornerShape(10.dp)
    ) {
        Text(
            text = buttonText,
            modifier = Modifier.padding(8.dp),
            fontWeight = FontWeight.SemiBold
        )

    }
}