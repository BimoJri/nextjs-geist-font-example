package com.example.sakuhijau

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RegisterOtpScreen(navController: NavController) {
    var otpValue by remember { mutableStateOf("") }
    var hasError by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var showResendMessage by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Step Indicator
        StepIndicator(currentStep = 3)

        Spacer(modifier = Modifier.height(32.dp))

        // OTP Title and Description
        Text(
            text = "Enter Verification Code",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "We've sent a 6-digit code to your phone number",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(32.dp))

        // OTP Input Field
        OutlinedTextField(
            value = otpValue,
            onValueChange = { 
                if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                    otpValue = it
                    hasError = false
                    errorMessage = ""
                }
            },
            label = { Text("Enter 6-digit code") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            isError = hasError,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary
            )
        )

        if (hasError) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Resend Code Button
        TextButton(
            onClick = {
                showResendMessage = true
            },
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                "Resend it",
                color = MaterialTheme.colorScheme.primary
            )
        }

        if (showResendMessage) {
            Text(
                text = "New code has been sent!",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Next Button
        Button(
            onClick = {
                when {
                    otpValue.isEmpty() -> {
                        hasError = true
                        errorMessage = "Please enter the verification code"
                    }
                    otpValue.length < 6 -> {
                        hasError = true
                        errorMessage = "Please enter all 6 digits"
                    }
                    else -> {
                        navController.navigate("registerEmail")
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            enabled = otpValue.isNotEmpty()
        ) {
            Text(
                "Next",
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}
