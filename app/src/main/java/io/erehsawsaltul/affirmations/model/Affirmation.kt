package io.erehsawsaltul.affirmations.model

import androidx.annotation.*


data class Affirmation(
        @StringRes val stringResourceId: Int,
        @DrawableRes val imageResourceId: Int
)