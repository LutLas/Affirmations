package io.erehsawsaltul.affirmations.model

data class Hymns(
    val author: Map<String,String>,
    val majorGroup: String,
    val no: String,
    val p1: String,
    val subGroup: String,
    val title: Map<String,String>
)