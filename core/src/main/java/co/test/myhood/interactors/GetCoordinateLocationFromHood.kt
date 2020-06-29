package co.test.myhood.interactors

import co.test.myhood.domain.Location

class GetCoordinateLocationFromHood() {
    operator fun invoke(name: String): Location {
        return Location("52.50820", "13.449358", name)
    }
}