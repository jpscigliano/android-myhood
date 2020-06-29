package co.test.myhood.di

import co.test.myhood.framework.dataSource.remote.api.HoodsAPI
import co.test.myhood.framework.dataSource.remote.api.PicturesHoodsAPI
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit.MINUTES
import javax.inject.Qualifier

@Module
@InstallIn(ApplicationComponent::class)
class ConnectivityModule {

    @DigginOkHttpClient
    @Provides
    fun provideDigginOkHttpClient() = OkHttpClient().newBuilder().callTimeout(10, MINUTES).build()

    @FlickrOkHttpClient
    @Provides
    fun provideFlickrOkHttpClient() = OkHttpClient().newBuilder().addInterceptor { chain ->
        val url = chain.request().url.newBuilder()
            .addQueryParameter("format", "json")
            .addQueryParameter("api_key", "ec59954245cdbf312dbf44ef854272db")
            .addQueryParameter("nojsoncallback", "1")
            .build()
        chain.proceed(chain.request().newBuilder().url(url).build())

    }.callTimeout(10, MINUTES).build()

    @DigginAPI
    @Provides
    fun provideDigginRetrofit(@DigginOkHttpClient client: OkHttpClient): Retrofit {
        return Builder()
            .baseUrl("http://www.digginberlin.com")
            .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
            .client(client)
            .build()
    }

    @FlickrAPI
    @Provides
    fun provideFlickrRetrofit(@FlickrOkHttpClient client: OkHttpClient): Retrofit {
        return Builder()
            .baseUrl("https://api.flickr.com")
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

    @Provides
    fun provideFlickrAPI(@FlickrAPI retrofit: Retrofit) = retrofit.create(PicturesHoodsAPI::class.java)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DigginAPI

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class FlickrAPI

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class FlickrOkHttpClient

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class DigginOkHttpClient
}
