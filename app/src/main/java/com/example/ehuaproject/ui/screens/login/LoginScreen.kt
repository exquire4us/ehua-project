package com.example.ehuaproject.ui.screens.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.ehuaproject.R
import com.example.ehuaproject.ui.components.DefaultTextField
import com.example.ehuaproject.ui.components.GeneralButton
import com.example.ehuaproject.ui.components.LargeTextComponent
import com.example.ehuaproject.ui.components.LogoCard
import com.example.ehuaproject.ui.theme.EhuaAppTypography
import com.example.ehuaproject.ui.util.CompanyLogo

@Composable
fun LoginScreen(
    userName: String,
    password: String,
    onLogin: () -> Unit,
    onPasswordChange: (String) -> Unit,
    onUserNameChange: (String) -> Unit,
    onRegister: () -> Unit,
) {
    val lottieComposition by  rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.cooking_animation))
    val animationProgress by animateLottieCompositionAsState(
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = 1f,
    )
    val companyLogos = remember{
        CompanyLogo.values().toList()
    }
    var rowSize by remember {
        mutableStateOf(Size.Zero)
    }
    Spacer(modifier = Modifier.height(16.dp))
    LazyColumn(
        modifier = Modifier,
        contentPadding = PaddingValues(horizontal = 16.dp)
    ){
        item {
            Column(modifier = Modifier.height(250.dp)) {
                LottieAnimation(
                    composition = lottieComposition,
                    progress = { animationProgress },
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.Fit
                )
            }
            Column {
                LargeTextComponent(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.login)
                )
                DefaultTextField(
                    value = userName,
                    onValueChange = onUserNameChange,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Email,
                            contentDescription = null
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email,imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = {}),
                    placeHolderText = stringResource(R.string.email)
                )
                Spacer(modifier = Modifier.height(8.dp))
                DefaultTextField(
                    value = password,
                    onValueChange = onPasswordChange,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Rounded.Lock,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        TextButton(onClick = {}) {
                            Text(text = "Forgot?", style = EhuaAppTypography.bodySmall.copy(
                                color = Color.Black,
                                fontWeight = FontWeight.SemiBold
                            ))
                        }
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {onLogin.invoke()}),
                    placeHolderText = stringResource(R.string.password),
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(32.dp))
                GeneralButton(
                    modifier = Modifier.fillMaxWidth(),
                    buttonText = "Login",
                    onClick = onLogin
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = stringResource(R.string.or_login_with),
                    style = EhuaAppTypography.bodySmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center

                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates ->
                        rowSize = coordinates.size.toSize()
                    },
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
//                    contentPadding = PaddingValues(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    companyLogos.forEach {
                        LogoCard(
                            modifier = Modifier.width(with(LocalDensity.current) { (rowSize.width / companyLogos.size).toDp() }),
                            logoResourceId = it,
                            onClick = {
                                when (it) {
                                    CompanyLogo.GOOGLE -> {}
                                    CompanyLogo.FACEBOOK -> {}
                                    CompanyLogo.APPLE -> {}
                                }
                            }
                        )

                    }

                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().clickable { onRegister() },
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = stringResource(R.string.don_t_have_an_account), style = EhuaAppTypography.bodySmall)
                    Text(
                        text = stringResource(R.string.register),
                        style = EhuaAppTypography.bodySmall.copy(
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }
        }
    }
}