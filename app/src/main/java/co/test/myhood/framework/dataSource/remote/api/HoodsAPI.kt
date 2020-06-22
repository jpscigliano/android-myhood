package co.test.myhood.framework.dataSource.remote.api

import retrofit2.http.GET

interface HoodsAPI {

    @GET("api/districts")
    suspend fun fetchHoods(): List<String>
}