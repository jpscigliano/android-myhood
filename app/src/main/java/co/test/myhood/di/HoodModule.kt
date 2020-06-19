package co.test.myhood.di

import co.test.myhood.data.dataSource.HoodsDataSource
import co.test.myhood.data.repository.HoodsRepository
import co.test.myhood.di.HoodModule.Declarations
import co.test.myhood.framework.dataSource.local.DatabaseHoodsDataSourceImp
import co.test.myhood.framework.dataSource.remote.NetworkHoodsDataSourceImp
import co.test.myhood.interactors.GetHoods
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import javax.inject.Qualifier

@Module(includes = [Declarations::class])
@InstallIn(ActivityComponent::class, FragmentComponent::class)
class HoodModule {

    @Provides
    fun provideHoodInteractor(hoodsRepository: HoodsRepository) = GetHoods(hoodsRepository)

    @Provides
    fun provideHoodRepository(
        @RemoteHoodDataSource networkHoodDataSource: HoodsDataSource,
        @LocalHoodDataSource localHoodDataSource: HoodsDataSource
    ) =
        HoodsRepository(networkHoodDataSource, localHoodDataSource)

    @Module
    @InstallIn(ActivityComponent::class, FragmentComponent::class)
    interface Declarations {

        @RemoteHoodDataSource
        @Binds
        fun provideRemoteHoodDataSource(hoodsDataSource: NetworkHoodsDataSourceImp): HoodsDataSource

        @LocalHoodDataSource
        @Binds
        fun provideLocalHoodDataSource(hoodsDataSource: DatabaseHoodsDataSourceImp): HoodsDataSource
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class RemoteHoodDataSource

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LocalHoodDataSource
}

