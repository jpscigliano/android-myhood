package co.test.myhood.framework.mappers

import co.test.myhood.dto.network.FlickrSearchedImageResponse
import javax.inject.Inject

class ImageMapper {

    class FlickrSearchedImageToURLMapper @Inject constructor() : Mapper<FlickrSearchedImageResponse, String>() {

        override fun map(input: FlickrSearchedImageResponse): String {
            val size = input.images?.photo?.size ?: 0

            return input.images?.photo?.get((0 until size).random())?.let {
                "https://farm${it.farm}.staticflickr.com/${it.server}/${it.id}_${it.secret}.jpg"
            } ?: run {
                ""
            }
        }
    }
}
