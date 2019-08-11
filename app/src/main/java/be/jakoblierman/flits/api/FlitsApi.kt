package be.jakoblierman.flits.api

import be.jakoblierman.flits.model.AvgSpeedCheck
import be.jakoblierman.flits.model.PoliceCheck
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

    /**
     * Get all AvgSpeedChecks
     */
    @GET("avgSpeedChecks")
    fun getAvgSpeedChecks(): Observable<List<AvgSpeedCheck>>

    /**
     * Get AvgSpeedCheck by id
     */
    @GET("avgSpeedCheck/{id}")
    fun getAvgSpeedCheckById(@Path("id") id: String): Observable<AvgSpeedCheck>

    /**
     * Get all PoliceChecks
     */
    @GET("policeChecks")
    fun getPoliceChecks(): Observable<List<PoliceCheck>>

    /**
     * Get PoliceCheck by id
     */
    @GET("policeCheck/{id}")
    fun getPoliceCheckById(@Path("id") id: String): Observable<PoliceCheck>
    
}