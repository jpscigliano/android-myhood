package co.test.myhood.interactors

import co.test.myhood.data.repository.HoodsRepository
import co.test.myhood.domain.Hood

class  GetHoods (private val hoodsRepository: HoodsRepository) {
    operator fun invoke(): List<Hood> {
        return hoodsRepository.getHoodList()
    }
}