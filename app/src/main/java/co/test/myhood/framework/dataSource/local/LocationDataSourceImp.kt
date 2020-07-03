package co.test.myhood.framework.dataSource.local

import android.content.Context
import android.location.Address
import android.location.Geocoder
import co.test.myhood.data.dataSource.LocationDataSource
import co.test.myhood.domain.Location
import co.test.myhood.dto.local.AddressLocationDTO
import co.test.myhood.framework.mappers.Mapper
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocationDataSourceImp @Inject constructor(
    @ApplicationContext val context: Context,
    private val addressToLocationMapper: Mapper<AddressLocationDTO, Location>
) : LocationDataSource {


    override suspend fun getLocationFromHoodName(name: String): Location {
        try {
            return coroutineScope {
                return@coroutineScope withContext(Dispatchers.IO) {
                    val address: Address = Geocoder(context).getFromLocationName(name, 1)[0]
                    addressToLocationMapper.map(AddressLocationDTO(address, name))
                }
            }
        } catch (e: Exception) {
            //Todo how to set default lococation
            return Location("52.50820", "13.449358", name)
        }
    }
}