package dev.fslab.academia.ui.util

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale

/**
 * Specs de animação padronizados do app — usados para manter o motion consistente
 * entre telas (mesma curva e duração).
 */
object Motion {
    /** Troca de estado/conteúdo (Crossfade, AnimatedContent). */
    fun <T> contentSpec(): FiniteAnimationSpec<T> = tween(durationMillis = 220)

    /** Mudança de tamanho (animateContentSize). */
    fun <T> sizeSpec(): FiniteAnimationSpec<T> =
        spring(dampingRatio = Spring.DampingRatioLowBouncy, stiffness = Spring.StiffnessMediumLow)
}

/**
 * Micro-interação de toque: leve redução de escala enquanto pressionado.
 * Não consome o clique — combine com `clickable(interactionSource = ...)` usando
 * a mesma [interactionSource], ou use a sobrecarga [pressScale] sem origem própria.
 */
fun Modifier.pressScale(
    interactionSource: MutableInteractionSource,
    pressedScale: Float = 0.97f
): Modifier = composed {
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by androidx.compose.animation.core.animateFloatAsState(
        targetValue = if (pressed) pressedScale else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "pressScale"
    )
    this.scale(scale)
}

/**
 * Cria e lembra um [MutableInteractionSource] — açúcar para uso com [pressScale].
 */
@androidx.compose.runtime.Composable
fun rememberInteractionSource(): MutableInteractionSource =
    remember { MutableInteractionSource() }
