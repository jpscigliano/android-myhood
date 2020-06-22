package co.test.myhood.data.repository

import co.test.myhood.data.dataSource.LocalHoodsDataSource
import co.test.myhood.data.dataSource.RemoteHoodsDataSource

import co.test.myhood.domain.Hood
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class HoodsRepository constructor(
    private val remoteHoodsDataSource: RemoteHoodsDataSource,
    private val localHoodsDataSource: LocalHoodsDataSource
) {

    fun getHoods() = networkBoundResource(
        query = { localHoodsDataSource.getHoods() },
        fetch = { remoteHoodsDataSource.getHoods() },
        saveFetchResult = { items -> localHoodsDataSource.saveHoods(items) }
    )

    suspend fun forceHoodUpdate() {
        localHoodsDataSource.saveHoods(mutableListOf(Hood("Neuquen", "Neuquen")))
    }

    inline fun <ResultType, RequestType> networkBoundResource(
        crossinline query: () -> Flow<ResultType>,
        crossinline fetch: suspend () -> RequestType,
        crossinline saveFetchResult: suspend (RequestType) -> Unit,
        crossinline onFetchFailed: (Throwable) -> Unit = { Unit },
        crossinline shouldFetch: (ResultType) -> Boolean = { true }
    ) = flow<ResultType> {
        // emit(Resource.Loading(null))
        val data = query().first()

        val flow = if (shouldFetch(data)) {
            emit(data)
            try {
                saveFetchResult(fetch())
                query().map { it }
            } catch (throwable: Throwable) {
                onFetchFailed(throwable)
                query().map { it }
            }
        } else {
            query().map { it }
        }
        emitAll(flow)
    }
}