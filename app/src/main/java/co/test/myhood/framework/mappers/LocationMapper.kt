package co.test.myhood.framework.mappers

import co.test.myhood.domain.Location
import co.test.myhood.dto.local.AddressLocationDTO
import javax.inject.Inject

class LocationMapper {

    class AddressToLocationMapper @Inject constructor() : Mapper<AddressLocationDTO, Location>() {
        override fun map(input: AddressLocationDTO): Location {
            return Location(input.address.latitude.toString(), input.address.longitude.toString(), input.name)
        }
    }
}