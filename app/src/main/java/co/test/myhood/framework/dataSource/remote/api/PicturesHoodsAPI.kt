package co.test.myhood.framework.dataSource.remote.api

import co.test.myhood.dto.network.FlickrSearchedImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PicturesHoodsAPI {

    @GET("services/rest")
    suspend fun fetchPictureHood(
        @Query("method") method: String = "flickr.photos.getInfo",
        @Query("photo_id") id: String
    ): String

    @GET("services/rest")
    suspend fun searchPictureHood2(
        @Query("method") method: String = "flickr.photos.search",
        @Query("per_page") perPage: Int = 100,
        @Query("lat") lat: String,
        @Query("lon") long: String
    ): FlickrSearchedImageResponse
}