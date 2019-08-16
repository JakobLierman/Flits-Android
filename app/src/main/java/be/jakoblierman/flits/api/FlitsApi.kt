package be.jakoblierman.flits.api

import be.jakoblierman.flits.model.AvgSpeedCheck
import be.jakoblierman.flits.model.PoliceCheck
import be.jakoblierman.flits.model.SpeedCamera
import be.jakoblierman.flits.model.User
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface FlitsApi {

    /**
     * Get all SpeedCameras
     *
     * @return Observable List of SpeedCamera
     */
    @GET("speedCameras")
    fun getSpeedCameras(): Observable<List<SpeedCamera>>

    /**
     * Gets SpeedCamera by id
     *
     * @param id
     * @return Observable of SpeedCamera
     */
    @GET("speedCameras/{id}")
    fun getSpeedCameraById(@Path("id") id: String): Observable<SpeedCamera>

    /**
     * Gets all AvgSpeedChecks
     *
     * @return Observable List of AvgSpeedChecks
     */
    @GET("avgSpeedChecks")
    fun getAvgSpeedChecks(): Observable<List<AvgSpeedCheck>>

    /**
     * Gets AvgSpeedCheck by id
     *
     * @param id
     * @return Observable of AvgSpeedCheck
     */
    @GET("avgSpeedChecks/{id}")
    fun getAvgSpeedCheckById(@Path("id") id: String): Observable<AvgSpeedCheck>

    /**
     * Gets all PoliceChecks
     *
     * @return Observable List of PoliceCheck
     */
    @GET("policeChecks")
    fun getPoliceChecks(): Observable<List<PoliceCheck>>

    /**
     * Gets PoliceCheck by id
     *
     * @param id
     * @return Observable of PoliceCheck
     */
    @GET("policeChecks/{id}")
    fun getPoliceCheckById(@Path("id") id: String): Observable<PoliceCheck>

    /**
     * Posts a new SpeedCheck to backend
     *
     * @param authToken
     * @param speedCamera
     * @return SpeedCheck Observable
     */
    @POST("speedCameras")
    fun postSpeedCamera(@Header("Authorization") authToken: String, @Body speedCamera: SpeedCamera): Observable<SpeedCamera>

    /**
     * Posts a new AvgSpeedCheck to backend
     *
     * @param authToken
     * @param avgSpeedCheck
     * @return AvgSpeedCheck Observable
     */
    @POST("avgSpeedChecks")
    fun postAvgSpeedCheck(@Header("Authorization") authToken: String, @Body avgSpeedCheck: AvgSpeedCheck): Observable<AvgSpeedCheck>

    /**
     * Posts a new PoliceCheck to backend
     *
     * @param authToken
     * @param policeCheck
     * @return PoliceCheck Observable
     */
    @POST("policeChecks")
    fun postPoliceCheck(@Header("Authorization") authToken: String, @Body policeCheck: PoliceCheck): Observable<PoliceCheck>

    /**
     * Deletes SpeedCamera from database
     *
     * @param authToken
     * @param id
     * @return true if deleted
     */
    @DELETE("speedCameras/{id}")
    fun deleteSpeedCamera(@Header("Authorization") authToken: String, @Path("id") id: String?): Observable<Boolean>

    /**
     * Deletes AvgSpeedCheck from database
     *
     * @param authToken
     * @param id
     * @return true if deleted
     */
    @DELETE("avgSpeedChecks/{id}")
    fun deleteAvgSpeedCheck(@Header("Authorization") authToken: String, @Path("id") id: String?): Observable<Boolean>

    /**
     * Deletes PoliceCheck from database
     *
     * @param authToken
     * @param id
     * @return true if deleted
     */
    @DELETE("policeChecks/{id}")
    fun deletePoliceCheck(@Header("Authorization") authToken: String, @Path("id") id: String?): Observable<Boolean>

    /**
     * Registers a new user
     *
     * @param fullName
     * @param email
     * @param password
     * @return User with token
     */
    @FormUrlEncoded
    @POST("users/register")
    fun register(
        @Field("fullName") fullName: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Single<User>

    /**
     * Signs in existing users
     *
     * @param email
     * @param password
     * @return User with token
     */
    @FormUrlEncoded
    @POST("users/login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Single<User>

    /**
     * Checks is email is valid and unique
     *
     * @param email
     * @return true if valid
     */
    @FormUrlEncoded
    @POST("users/isValidEmail")
    fun isValidEmail(@Field("email") email: String): Single<Boolean>

    /**
     * Gets user by email
     *
     * @param email
     * @return user
     */
    @GET("users/{email}")
    fun getUserByEmail(@Path("email") email: String): Observable<User>

    /**
     * Gets user by id
     *
     * @param id
     * @return
     */
    @GET("users/id/{id}")
    fun getUserById(@Path("id") id: String): Observable<User>

}