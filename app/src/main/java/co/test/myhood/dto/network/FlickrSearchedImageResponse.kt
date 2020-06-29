package co.test.myhood.dto.network

import com.squareup.moshi.Json

class FlickrSearchedImageResponse(
    @Json(name = "photos")
    val images: Result?
) {

    class Result(
        @Json(name = "page") val page: Int,
        @Json(name = "pages") val pages: Int,
        @Json(name = "perpage") val perPage: Int,
        @Json(name = "total") val total: String,
        @Json(name = "photo") val photo: List<Photo>?
    ) {

        class Photo(
            @Json(name = "id") val id: String,
            @Json(name = "secret") val secret: String,
            @Json(name = "server") val server: String,
            @Json(name = "farm") val farm: Int
        )
    }
}