package co.test.myhood.interactors

import co.test.myhood.data.repository.HoodsRepository

class ForceUpdateHoods(private val hoodsRepository: HoodsRepository) {
    suspend operator fun invoke() {
        return hoodsRepository.forceHoodUpdate()
    }
}