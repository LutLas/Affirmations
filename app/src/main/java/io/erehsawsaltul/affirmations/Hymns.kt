package io.erehsawsaltul.affirmations

data class Hymns(
    val author: Map<String,String>,
    val majorGroup: String,
    val no: String,
    val p1: Map<String,Any>,
    val subGroup: String,
    val title: Map<String,String>
)