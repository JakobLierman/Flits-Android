package be.jakoblierman.flits.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    @field:Json(name = "fullName")
    val fullName: String,
    @field:Json(name = "email")
    val email: String
) : Parcelable