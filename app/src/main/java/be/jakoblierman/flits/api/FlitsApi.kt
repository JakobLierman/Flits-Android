package be.jakoblierman.flits.api

import be.jakoblierman.flits.model.AvgSpeedCheck
import be.jakoblierman.flits.model.PoliceCheck
import be.jakoblierman.flits.model.SpeedCamera
import be.jakoblierman.flits.model.User
import io.reactivex.Observable
import retrofit2.http.*

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
    @GET("avgSpeedChecks/{id}")
    fun getAvgSpeedCheckById(@Path("id") id: String): Observable<AvgSpeedCheck>

    /**
     * Get all PoliceChecks
     */
    @GET("policeChecks")
    fun getPoliceChecks(): Observable<List<PoliceCheck>>

    /**
     * Get PoliceCheck by id
     */
    @GET("policeChecks/{id}")
    fun getPoliceCheckById(@Path("id") id: String): Observable<PoliceCheck>

    /**
     * Post SpeedCamera
     */
    @POST("speedCameras")
    fun postSpeedCamera(@Body speedCamera: SpeedCamera): Observable<SpeedCamera>

    /**
     * Post AvgSpeedCheck
     */
    @POST("avgSpeedChecks")
    fun postAvgSpeedCheck(@Body avgSpeedCheck: AvgSpeedCheck): Observable<AvgSpeedCheck>

    /**
     * Post PoliceCheck
     */
    @POST("policeChecks")
    fun postPoliceCheck(@Body policeCheck: PoliceCheck): Observable<PoliceCheck>

    /**
     * Delete SpeedCamera
     */
    @DELETE("speedCameras/{id}")
    fun deleteSpeedCamera(@Path("id") id: String?): Observable<Boolean>

    /**
     * Delete AvgSpeedCheck
     */
    @DELETE("avgSpeedChecks/{id}")
    fun deleteAvgSpeedCheck(@Path("id") id: String?): Observable<Boolean>

    /**
     * Delete PoliceCheck
     */
    @DELETE("policeChecks/{id}")
    fun deletePoliceCheck(@Path("id") id: String?): Observable<Boolean>

    /**
     * Register
     */
    @FormUrlEncoded
    @POST("users/register")
    fun register(
        @Field("fullName") fullName: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<User>

    /**
     * Login
     */
    @FormUrlEncoded
    @POST("users/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Observable<User>

    /**
     * Check if email is unique
     */
    @FormUrlEncoded
    @POST("users/isValidEmail")
    fun isValidEmail(@Field("email") email: String): Observable<Boolean>

    /**
     * Get user by email
     */
    @GET("users/{email}")
    fun getUserByEmail(@Path("email") email: String): Observable<User>

    /**
     * Get user by id
     */
    @GET("users/id/{id}")
    fun getUserById(@Path("id") id: String): Observable<User>

}