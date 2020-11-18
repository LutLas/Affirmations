package io.erehsawsaltul.affirmations.data

import io.erehsawsaltul.affirmations.model.Hymns
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


private const val privateToken = "71a1c5c8-6e74-4a29-bb0f-e8f1011013bd"

interface MyAPI {
    @Headers("Accept: application/json")
    @GET("hymnsListOnly.json?alt=media&token=$privateToken")
    fun getHymns(): Call<List<Hymns>>//Call<Map<String,Any>>
}
