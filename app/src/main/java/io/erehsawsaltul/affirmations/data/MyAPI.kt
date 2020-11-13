package io.erehsawsaltul.affirmations.data

import io.erehsawsaltul.affirmations.model.Hymns
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


private const val privateToken = "5904d9b6-a1eb-41ff-9eb9-6df50c8c8281"

interface MyAPI {
    @Headers("Accept: application/json")
    @GET("hymnsListOnly.json?alt=media&token=$privateToken")
    fun getHymns(): Call<List<Hymns>>//Call<Map<String,Any>>
}
