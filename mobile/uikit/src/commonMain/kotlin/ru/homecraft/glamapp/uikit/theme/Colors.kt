package ru.homecraft.glamapp.uikit.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Light Color Scheme
val LightColorScheme = lightColorScheme(
    primary = Color(0xFF8B5FBF),      // Rich purple - represents creativity
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFE9DCFA),
    onPrimaryContainer = Color(0xFF2A0054),

    secondary = Color(0xFFF48FB1),    // Soft pink - feminine and warm
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFFFD9E3),
    onSecondaryContainer = Color(0xFF3B0027),

    tertiary = Color(0xFF4DB6AC),     // Teal - fresh and clean
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFFB2DFDB),
    onTertiaryContainer = Color(0xFF00201D),

    background = Color(0xFFFFFBFE),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFFFFBFE),
    onSurface = Color(0xFF1C1B1F),

    surfaceVariant = Color(0xFFE7E0EC),
    onSurfaceVariant = Color(0xFF49454F),

    outline = Color(0xFF79747E),
    outlineVariant = Color(0xFFCAC4D0),

    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002)
)

// Dark Color Scheme
val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFD0BCFF),
    onPrimary = Color(0xFF381E72),
    primaryContainer = Color(0xFF4F378B),
    onPrimaryContainer = Color(0xFFEADDFF),

    secondary = Color(0xFFF8BBD9),
    onSecondary = Color(0xFF4A2532),
    secondaryContainer = Color(0xFF633B48),
    onSecondaryContainer = Color(0xFFFFD8E4),

    tertiary = Color(0xFF80CBC4),
    onTertiary = Color(0xFF003734),
    tertiaryContainer = Color(0xFF00504B),
    onTertiaryContainer = Color(0xFF99F0E8),

    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF1C1B1F),
    onSurface = Color(0xFFE6E1E5),

    surfaceVariant = Color(0xFF49454F),
    onSurfaceVariant = Color(0xFFCAC4D0),

    outline = Color(0xFF938F99),
    outlineVariant = Color(0xFF49454F),

    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6)
)