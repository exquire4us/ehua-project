package com.example.ehuaproject.ui.screens.signUp

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
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
fun SignUpScreen(
    email: String,
    password: String,
    fullName: String,
    phoneNumber: String,
    onSignUp: () -> Unit,
    onPasswordChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onFullNameChange: (String) -> Unit,
    onPhoneNumberChange: (String) -> Unit,
    showSignUpMessage: Boolean

) {
    val lottieComposition by  rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.cooking_boiling))
    val animationProgress by animateLottieCompositionAsState(
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true,
        speed = 1f,
    )
    var rowSize by remember {
        mutableStateOf(Size.Zero)
    }
    val companyLogos = remember {
        CompanyLogo.values().toList()
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = showSignUpMessage ){
        if (showSignUpMessage){
            Toast.makeText(context, "Sign Up Successful", Toast.LENGTH_SHORT).show()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
            )
            .padding(top = 16.dp)
            .verticalScroll(rememberScrollState()),

    ){

            Column(modifier = Modifier.height(250.dp)) {
                LottieAnimation(
                    composition = lottieComposition,
                    progress = { animationProgress },
                    alignment = Alignment.TopCenter,
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column {
                LargeTextComponent(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.sign_up)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().onGloballyPositioned { coordinates ->
                        rowSize = coordinates.size.toSize()
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                        companyLogos.forEach {
                            LogoCard(
                                modifier = Modifier.width(width = with(LocalDensity.current) {
                                    (rowSize.width / companyLogos.size).toDp()
                                }),
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
                Text(
                    text = stringResource(R.string.or_register_with_email),
                    style = EhuaAppTypography.bodySmall,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center

                )
                Spacer(modifier = Modifier.height(16.dp))

            }

                    DefaultTextField(
                        value = email,
                        onValueChange = onEmailChange,
                        placeHolderText = stringResource(R.string.email),
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(imageVector = Icons.Rounded.Email, contentDescription = "")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {})

                    )


                    DefaultTextField(
                        value = password,
                        onValueChange = onPasswordChange,
                        placeHolderText = stringResource(R.string.password),
                        modifier = Modifier.fillMaxWidth(),
                        leadingIcon = {
                            Icon(imageVector = Icons.Rounded.Lock, contentDescription = "")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {}),
                        visualTransformation = PasswordVisualTransformation()

                    )

                    DefaultTextField(
                        value = fullName,
                        onValueChange = onFullNameChange,
                        placeHolderText = stringResource(R.string.full_name),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {}),
                        leadingIcon = {
                            Icon(imageVector = Icons.Rounded.AccountCircle, contentDescription = "")
                        },

                    )
                    DefaultTextField(
                        value = phoneNumber,
                        onValueChange = onPhoneNumberChange,
                        placeHolderText = stringResource(R.string.phone_number),
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(onDone = {}),
                        leadingIcon = {
                            Icon(imageVector = Icons.Rounded.Phone, contentDescription = "")
                        },

                    )
            Spacer(modifier = Modifier.height(32.dp).weight(1f))
            GeneralButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, top = 32.dp),
                buttonText = stringResource(R.string.sign_up)
            ) {
                onSignUp()
            }
    }
}