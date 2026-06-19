package dev.fslab.academia.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Classe de tamanho de janela, baseada na largura disponível em dp.
 * COMPACT  — celulares pequenos / telas estreitas (< 360dp)
 * MEDIUM   — celulares comuns (360dp a 599dp)
 * EXPANDED — celulares grandes, foldables e tablets (>= 600dp)
 *
 * Segue a semântica das WindowSizeClass do Material 3, simplificada para o app.
 */
enum class WindowSize { COMPACT, MEDIUM, EXPANDED }

/**
 * Tokens de dimensão responsivos do app.
 *
 * Centraliza espaçamentos, alturas de toque, ícones e raios para que todas as telas
 * escalem de forma consistente entre tamanhos de tela — em vez de valores fixos
 * espalhados que quebram o layout em telas pequenas.
 *
 * Acesso nas telas via [LocalDimens] (mesmo padrão de LocalAcademiaColors).
 */
data class Dimens(
    val windowSize: WindowSize,
    // Espaçamentos progressivos (gaps verticais/horizontais)
    val spaceXxs: Dp,
    val spaceXs: Dp,
    val spaceSm: Dp,
    val spaceMd: Dp,
    val spaceLg: Dp,
    val spaceXl: Dp,
    val spaceXxl: Dp,
    // Padding padrão de tela
    val screenPaddingH: Dp,
    val screenPaddingV: Dp,
    // Alvos de toque
    val buttonHeight: Dp,
    val inputHeight: Dp,
    val minTouchTarget: Dp,
    // Ícones
    val iconSm: Dp,
    val iconMd: Dp,
    val iconLg: Dp,
    // Elementos compostos
    val logoSize: Dp,
    val avatarSize: Dp,
    val cornerRadius: Dp,
    val cardElevation: Dp,
    // Padding interno de cards/superfícies (medium = valor antigo, sem drift visual)
    val cardPadding: Dp,
    val cardPaddingSmall: Dp
)

private val CompactDimens = Dimens(
    windowSize = WindowSize.COMPACT,
    spaceXxs = 2.dp,
    spaceXs = 4.dp,
    spaceSm = 8.dp,
    spaceMd = 12.dp,
    spaceLg = 16.dp,
    spaceXl = 24.dp,
    spaceXxl = 32.dp,
    screenPaddingH = 16.dp,
    screenPaddingV = 12.dp,
    buttonHeight = 50.dp,
    inputHeight = 52.dp,
    minTouchTarget = 48.dp,
    iconSm = 16.dp,
    iconMd = 20.dp,
    iconLg = 28.dp,
    logoSize = 56.dp,
    avatarSize = 40.dp,
    cornerRadius = 12.dp,
    cardElevation = 2.dp,
    cardPadding = 12.dp,
    cardPaddingSmall = 8.dp
)

private val MediumDimens = Dimens(
    windowSize = WindowSize.MEDIUM,
    spaceXxs = 4.dp,
    spaceXs = 6.dp,
    spaceSm = 10.dp,
    spaceMd = 16.dp,
    spaceLg = 20.dp,
    spaceXl = 32.dp,
    spaceXxl = 48.dp,
    screenPaddingH = 24.dp,
    screenPaddingV = 16.dp,
    buttonHeight = 56.dp,
    inputHeight = 56.dp,
    minTouchTarget = 48.dp,
    iconSm = 18.dp,
    iconMd = 22.dp,
    iconLg = 30.dp,
    logoSize = 64.dp,
    avatarSize = 48.dp,
    cornerRadius = 14.dp,
    cardElevation = 3.dp,
    cardPadding = 16.dp,
    cardPaddingSmall = 12.dp
)

private val ExpandedDimens = Dimens(
    windowSize = WindowSize.EXPANDED,
    spaceXxs = 4.dp,
    spaceXs = 8.dp,
    spaceSm = 12.dp,
    spaceMd = 20.dp,
    spaceLg = 28.dp,
    spaceXl = 40.dp,
    spaceXxl = 56.dp,
    screenPaddingH = 32.dp,
    screenPaddingV = 24.dp,
    buttonHeight = 60.dp,
    inputHeight = 60.dp,
    minTouchTarget = 48.dp,
    iconSm = 20.dp,
    iconMd = 24.dp,
    iconLg = 34.dp,
    logoSize = 72.dp,
    avatarSize = 56.dp,
    cornerRadius = 16.dp,
    cardElevation = 4.dp,
    cardPadding = 20.dp,
    cardPaddingSmall = 16.dp
)

val LocalDimens = compositionLocalOf { MediumDimens }

/**
 * Calcula os tokens de dimensão a partir da largura atual da tela.
 * Usado pelo [dev.fslab.academia.ui.theme.AcademiaTheme] para prover [LocalDimens].
 */
@Composable
fun rememberDimens(): Dimens {
    val widthDp = LocalConfiguration.current.screenWidthDp
    return when {
        widthDp < 360 -> CompactDimens
        widthDp < 600 -> MediumDimens
        else -> ExpandedDimens
    }
}
