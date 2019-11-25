package ns.example.mvc_sample.data.entity

import com.google.gson.annotations.SerializedName

data class CatFact(
    @SerializedName("all") private val _facts: List<Fact>? = listOf()
) {
    val facts: List<Fact> get() = _facts ?: listOf()
}

data class Fact(
    @SerializedName("text") val text: String = ""
)