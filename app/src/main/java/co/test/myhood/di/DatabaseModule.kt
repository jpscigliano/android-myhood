package co.test.myhood.di

import android.app.Application
import androidx.room.Room
import co.test.myhood.framework.dataSource.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) =
        Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java, "hood-database"
        ).build()

    @Provides
    fun provideHoodDao(appDatabase: AppDatabase) = appDatabase.hoodsDao()
}