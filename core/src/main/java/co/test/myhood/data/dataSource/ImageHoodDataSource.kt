package co.test.myhood.data.dataSource

import co.test.myhood.domain.Location

interface ImageHoodDataSource {
    suspend fun getImageFromHoodLocation(location: Location): String
}