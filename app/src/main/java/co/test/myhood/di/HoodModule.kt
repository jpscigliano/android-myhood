package co.test.myhood.di

import co.test.myhood.data.dataSource.LocalHoodsDataSource
import co.test.myhood.data.dataSource.RemoteHoodsDataSource
import co.test.myhood.data.repository.HoodsRepository
import co.test.myhood.di.HoodModule.Declarations
import co.test.myhood.framework.dataSource.local.DatabaseHoodsDataSourceImp
import co.test.myhood.framework.dataSource.remote.NetworkHoodsDataSourceImp

import co.test.myhood.interactors.ForceUpdateHoods
import co.test.myhood.interactors.GetHoods

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module(includes = [Declarations::class])
@InstallIn(ApplicationComponent::class)
class HoodModule {


    @Provides
    fun provideHoodInteractor(hoodsRepository: HoodsRepository) = GetHoods(hoodsRepository)

    @Provides
    fun provideSimpleHoodInteractor(hoodsRepository: HoodsRepository) = ForceUpdateHoods(hoodsRepository)


    @Singleton
    @Provides
    fun provideHoodRepository(
        networkHoodDataSource: RemoteHoodsDataSource,
        localHoodDataSource: LocalHoodsDataSource
    ) =
        HoodsRepository(networkHoodDataSource, localHoodDataSource)

    @Module
    @InstallIn(ActivityComponent::class)
    interface Declarations {

        @Binds
        fun provideRemoteHoodDataSource(hoodsDataSource: NetworkHoodsDataSourceImp): RemoteHoodsDataSource

        @Binds
        fun provideLocalHoodDataSource(hoodsDataSource: DatabaseHoodsDataSourceImp): LocalHoodsDataSource
    }
}

