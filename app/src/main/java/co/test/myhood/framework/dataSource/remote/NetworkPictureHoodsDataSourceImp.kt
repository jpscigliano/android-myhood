package co.test.myhood.framework.dataSource.remote

import co.test.myhood.data.dataSource.ImageHoodDataSource
import co.test.myhood.domain.Location
import co.test.myhood.dto.network.FlickrSearchedImageResponse
import co.test.myhood.framework.dataSource.remote.api.PicturesHoodsAPI
import co.test.myhood.framework.mappers.Mapper
import javax.inject.Inject

class NetworkPictureHoodsDataSourceImp @Inject constructor(
    private val hoodsAPI: PicturesHoodsAPI,
    private val locationToHoodImageMapper: Mapper<FlickrSearchedImageResponse, String>
) : ImageHoodDataSource {

    override suspend fun getImageFromHoodLocation(location: Location): String {
        return locationToHoodImageMapper.map(
            hoodsAPI.searchPictureHood2(
                lat = location.lat,
                long = location.lon
            )
        )
    }
}