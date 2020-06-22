package co.test.myhood.di

import co.test.myhood.framework.dataSource.remote.api.HoodsAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.MINUTES
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Qualifier

@Module
@InstallIn(ActivityComponent::class)
class ConnectivityModule {

    @Provides
    fun provideOkHttpClient() = OkHttpClient().newBuilder().callTimeout(10,MINUTES).build()

    @DigginAPI
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Builder()
            .baseUrl("http://www.digginberlin.com")
            .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
            .client(client)
            .build()
    }

    private fun getMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    fun provideHoodAPI(@DigginAPI retrofit: Retrofit) = retrofit.create(HoodsAPI::class.java)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DigginAPI
}
