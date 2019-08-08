package be.jakoblierman.flits.networking

import be.jakoblierman.flits.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceGenerator {

    private val retrofitBuilder = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())

    private val retrofit = retrofitBuilder.build()

    val flitsApi: FlitsApi = retrofit.create(FlitsApi::class.java)
}