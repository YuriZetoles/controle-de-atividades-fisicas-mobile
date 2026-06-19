package dev.fslab.academia.ui.screens.auth

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.fslab.academia.ui.theme.LocalAcademiaColors
import dev.fslab.academia.ui.theme.LocalDimens

@Composable
fun ForgotPasswordEmailScreen(
    modifier: Modifier = Modifier,
    initialEmail: String = "",
    isDarkTheme: Boolean = true,
    isLoading: Boolean = false,
    errorMessage: String? = null,
    onToggleTheme: () -> Unit = {},
    onSubmit: (String) -> Unit = {}
) {
    val colors = LocalAcademiaColors.current
    val dimens = LocalDimens.current

    var email by remember { mutableStateOf(initialEmail) }

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }
    val fadeAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(1000),
        label = "fadeIn"
    )

    val backgroundBrush = remember(colors.isDark) {
        if (colors.isDark) {
            object : ShaderBrush() {
                override fun createShader(size: androidx.compose.ui.geometry.Size): Shader {
                    return RadialGradientShader(
                        colors = listOf(Color(0xFF131F11), Color(0xFF0F0F0F)),
                        center = Offset(size.width / 2f, 0f),
                        radius = size.width * 1.5f
                    )
                }
            }
        } else {
            SolidColor(colors.background)
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundBrush)
    ) {
        if (colors.isDark) {
            Canvas(modifier = Modifier.fillMaxWidth().height(256.dp).alpha(0.15f)) {
                drawRect(
                    brush = Brush.verticalGradient(
                        colors = listOf(colors.primary, Color.Transparent)
                    )
                )
            }
        }

        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.safeDrawing)
        ) {
        val alturaTela = maxHeight
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = alturaTela)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = dimens.screenPaddingH),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = onToggleTheme,
                    modifier = Modifier
                        .size(dimens.minTouchTarget)
                        .clip(CircleShape)
                        .background(colors.surface.copy(alpha = 0.5f))
                        .border(1.dp, colors.inputBorder.copy(alpha = 0.2f), CircleShape)
                ) {
                    Icon(
                        imageVector = if (isDarkTheme) Icons.Filled.LightMode else Icons.Filled.DarkMode,
                        contentDescription = "Trocar Tema",
                        tint = colors.textPrimary,
                        modifier = Modifier.size(dimens.iconMd)
                    )
                }
            }

            // Bloco central (marca + formulário) agrupado para o SpaceBetween.
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

            Box(
                modifier = Modifier
                    .size(dimens.logoSize)
                    .shadow(
                        elevation = 20.dp,
                        shape = RoundedCornerShape(16.dp),
                        ambientColor = colors.primary.copy(alpha = 0.35f),
                        spotColor = colors.primary.copy(alpha = 0.35f)
                    )
                    .clip(RoundedCornerShape(16.dp))
                    .background(colors.primary.copy(alpha = 0.05f))
                    .border(1.dp, colors.primary.copy(alpha = 0.2f), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.FitnessCenter,
                    contentDescription = null,
                    tint = colors.primary,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.height(dimens.spaceXs))

            Text(
                text = buildAnnotatedString {
                    append("Spot")
                    withStyle(SpanStyle(color = colors.primary)) {
                        append("ter")
                    }
                },
                color = colors.textPrimary,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.75).sp
            )

            Text(
                text = "RECUPERAÇÃO DE SENHA",
                color = colors.textSecondary.copy(alpha = 0.8f),
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 0.35.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(dimens.spaceXl))

            Text(
                text = "Esqueceu a senha?",
                color = colors.textPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = (-0.6).sp,
                modifier = Modifier.padding(bottom = dimens.spaceSm)
            )

            Text(
                text = "Informe seu e-mail cadastrado para enviarmos um link de recuperação.",
                color = colors.textSecondary,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = dimens.spaceXl),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(dimens.spaceMd)
            ) {
                Column {
                    Text(
                        text = "E-MAIL",
                        color = colors.textSecondary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text("seu@email.com", color = colors.textSecondary) },
                        leadingIcon = {
                            Icon(
                                Icons.Filled.Email,
                                contentDescription = null,
                                tint = if (email.isNotEmpty()) colors.primary else colors.textSecondary,
                                modifier = Modifier.size(18.dp)
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = colors.surface,
                            focusedContainerColor = colors.surface,
                            unfocusedBorderColor = colors.inputBorder,
                            focusedBorderColor = colors.primary.copy(alpha = 0.5f),
                            focusedTextColor = colors.textPrimary,
                            unfocusedTextColor = colors.textPrimary,
                            cursorColor = colors.primary
                        )
                    )
                }

                if (!errorMessage.isNullOrBlank()) {
                    Text(
                        text = errorMessage,
                        color = colors.errorText,
                        fontSize = 13.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.height(dimens.spaceSm))

                Button(
                    onClick = { onSubmit(email.trim()) },
                    enabled = !isLoading && email.isNotBlank(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimens.buttonHeight)
                        .shadow(
                            elevation = 20.dp,
                            shape = RoundedCornerShape(dimens.cornerRadius),
                            ambientColor = colors.primary.copy(alpha = 0.35f),
                            spotColor = colors.primary.copy(alpha = 0.35f)
                        ),
                    shape = RoundedCornerShape(dimens.cornerRadius),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colors.primary,
                        contentColor = Color.Black
                    )
                ) {
                    if (isLoading) {
                        CircularProgressIndicatorComponent(color = Color.Black, size = 24.dp)
                    } else {
                        Text(
                            text = "ENVIAR LINK",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            } // fim do bloco central

            // Espaço inferior de respiro; com o SpaceBetween centraliza o bloco.
            Spacer(modifier = Modifier.height(dimens.spaceXl))
        }
        }
    }
}
