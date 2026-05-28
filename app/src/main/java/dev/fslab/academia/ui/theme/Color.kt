package dev.fslab.academia.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * PALETA DE CORES DA APLICAÇÃO - FÁBRICA 4
 *
 * Baseado no Design System oficial (TrainingApp no Figma).
 * O Modo Escuro é o padrão identitário.
 */

// ==========================================
// CORES PRINCIPAIS (DESIGN SYSTEM)
// ==========================================
val PrimaryNeon = Color(0xFF5DD62C)        // Verde Neon (Botões, ícones ativos)
val SecondaryDarkGreen = Color(0xFF16A34A) // Verde escuro (Para contraste no tema claro)
val PrimaryLightGreen = Color(0xFFF8FAFC)  // Fundo principal para tema claro (Slate 50)

// ==========================================
// SUPERFÍCIES E FUNDOS (DARK MODE FOCUSED)
// ==========================================
val DarkBg = Color(0xFF0F0F0F)             // Fundo principal do App (Preto profundo Figma)
val SurfaceDark = Color(0xFF1A1A1A)        // Superfície de Cards e Inputs (Figma)
val DarkInputBg = Color(0xFF1A1A1A)        // Fundo dos Inputs no Dark Mode
val DarkBgGradient = Color(0xFF0F0F0F)     // Base do background gradiente


// ==========================================
// TEXTOS E TIPOGRAFIA
// ==========================================
val TextWhite = Color(0xFFF1F5F9)          // Texto Principal no Dark (Slate 100)
val TextDarkOnPrimary = Color(0xFF0F0F0F)  // Texto Preto (Usado SOBRE o botão Verde Neon)
val TextGraySecondary = Color(0xFF94A3B8)  // Texto secundário (Slate 400 - placeholders)
val DarkTextTertiary = Color(0xFF64748B)   // Textos terciários e detalhes sutis


// ==========================================
// BORDAS E ÍCONES
// ==========================================
val DarkInputBorder = Color(0xFF334155)    // Borda dos inputs dark (Figma border)
val DarkIconColor = Color(0xFF5DD62C)      // Ícones de destaque
val IconMuted = Color(0xFF94A3B8)          // Ícones inativos ou secundários


// ==========================================
// CORES DE ESTADO (FEEDBACK)
// ==========================================
val ErrorBackground = Color(0xFF2E1212)    // Fundo para erro no dark
val ErrorText = Color(0xFFCF6679)          // Texto de erro / Ação Destrutiva (Excluir Conta)
val ErrorButton = Color(0xFFCF6679)        // Vermelho padrão de alerta
val SuccessBackground = Color(0xFF1E293B)  // Fundo de sucesso sutil
val SuccessText = Color(0xFF5DD62C)        // Texto de sucesso (verde neon)


// ==========================================
// TEMA CLARO (LIGHT MODE COMPLEMENTAR)
// ==========================================
val SurfaceWhite = Color(0xFFFFFFFF)       // Superfície dos Cards (Branco)
val SurfaceLight = Color(0xFFF1F5F9)       // Superfície secundária (Slate 100)
val TextPrimaryLight = Color(0xFF0F0F0F)   // Texto primário
val TextSecondaryLight = Color(0xFF475569) // Texto secundário (Slate 600)
val InputBorderLight = Color(0xFFCBD5E1)   // Bordas claras (Slate 300)
val LightGray = Color(0xFFE2E8F0)          // Fundo de inputs claros (Slate 200)

// ==========================================
// DASHBOARD ADMIN & FEATURES CARDS (Opcional)
// ==========================================
val CardBlue = Color(0xFF7C6AF6)
val CardOrange = Color(0xFFD97706)
val CardGreen = Color(0xFF16A34A)
val CardYellow = Color(0xFFFBBF24)
val CardPurple = Color(0xFFA855F7)
