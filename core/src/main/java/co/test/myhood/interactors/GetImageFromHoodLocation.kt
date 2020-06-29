package co.test.myhood.interactors

import co.test.myhood.domain.Location

class GetImageFromHoodLocation {
    operator fun invoke(location: Location): String {
        return "URL"
    }
}