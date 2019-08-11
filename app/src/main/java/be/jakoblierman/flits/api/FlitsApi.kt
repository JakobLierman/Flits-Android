package be.jakoblierman.flits.api

import be.jakoblierman.flits.model.SpeedCamera
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface FlitsApi {

    /**
     * Get all SpeedCameras
     */
    @GET("speedCameras")
    fun getSpeedCameras(): Observable<List<SpeedCamera>>

    /**
     * Get SpeedCamera by id
     */
    @GET("speedCameras/{id}")
    fun getSpeedCameraById(@Path("id") id: String): Observable<SpeedCamera>
}