package co.test.myhood.framework.dataSource.local

import android.content.Context
import android.location.Address
import android.location.Geocoder
import co.test.myhood.data.dataSource.LocationDataSource
import co.test.myhood.domain.Location
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocationDataSourceImp @Inject constructor(
    @ApplicationContext val context: Context
) : LocationDataSource {


    override suspend fun getLocationFromHoodName(name: String): Location {
        try {
            var address: Address = Geocoder(context).getFromLocationName(name, 1)[0]

            return if (address != null) {
                Location(address.latitude.toString(), address.longitude.toString(), name)
            } else {
                address = Geocoder(context).getFromLocationName("Berlin", 1)[0]
                if (address != null) {
                    Location(address.latitude.toString(), address.longitude.toString(), name)
                } else {
                    Location("52.50820", "13.449358", name)
                }
            }
        } catch (e: Exception) {
            return Location("52.50820", "13.449358", name)
        }
    }
}