package co.test.myhood.framework.dataSource.remote

import co.test.myhood.data.dataSource.ImageHoodDataSource
import co.test.myhood.domain.Location
import co.test.myhood.framework.dataSource.remote.api.PicturesHoodsAPI
import javax.inject.Inject

class NetworkPictureHoodsDataSourceImp @Inject constructor(
    private val hoodsAPI: PicturesHoodsAPI
) : ImageHoodDataSource {

    override suspend fun getImageFromHoodLocation(location: Location): String {

        val searched = hoodsAPI.searchPictureHood2(
            lat = location.lat,
            long = location.lon
        )
        return searched.images?.photo?.get(0)?.let {
            "https://farm${it.farm}.staticflickr.com/${it.server}/${it.id}_${it.secret}.jpg"
        } ?: run {
            ""
        }
    }
}