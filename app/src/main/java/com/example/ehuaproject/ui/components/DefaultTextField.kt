package com.example.ehuaproject.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.ehuaproject.ui.theme.EhuaAppTypography
import com.example.ehuaproject.ui.theme.Red100

@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit = {},
    placeHolderText: String= "" ,
    label: String = "",
    trailingIcon :@Composable ()-> Unit = {},
    leadingIcon: @Composable () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions(),
    keyboardActions: KeyboardActions = KeyboardActions(),

) {
    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(text = placeHolderText, style = EhuaAppTypography.bodySmall, maxLines = 1)
        },
        leadingIcon = {leadingIcon.invoke()},
        trailingIcon = {trailingIcon.invoke()},
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            unfocusedIndicatorColor = Red100,
            focusedIndicatorColor = Red100,
            placeholderColor = Color.LightGray,
            focusedLeadingIconColor = Color.DarkGray,
            unfocusedLeadingIconColor = Color.DarkGray,
            focusedTrailingIconColor = Red100,
            unfocusedTrailingIconColor = Red100,
            textColor = Color.Black,
        ),
        textStyle = TextStyle.Default.copy(
            color = Color.Black,
            textDecoration = TextDecoration.None,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        ),
    )
}