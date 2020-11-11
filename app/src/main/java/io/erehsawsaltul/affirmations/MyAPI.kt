package io.erehsawsaltul.affirmations

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


private const val privateToken = "d865fda4-ff64-424d-b1f4-d2abc1b36e51"

interface MyAPI {
    @Headers("Accept: application/json")
    @GET("hymnsListOnly.json?alt=media&token=$privateToken")
    fun getHymns(): Call<Map<String,Any>>
}
