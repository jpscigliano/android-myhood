package co.test.myhood.interactors

import co.test.myhood.data.Resource
import co.test.myhood.data.repository.HoodsRepository
import co.test.myhood.domain.Hood
import kotlinx.coroutines.flow.Flow

class GetHoods(private val hoodsRepository: HoodsRepository) {
    operator fun invoke(): Flow<Resource<List<Hood>>> {
        return hoodsRepository.hoods
    }
}