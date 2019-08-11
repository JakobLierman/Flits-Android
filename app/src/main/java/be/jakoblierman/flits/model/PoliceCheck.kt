package be.jakoblierman.flits.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class PoliceCheck(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "Location")
    var location: String,
    @field:Json(name = "description")
    var description: String,
    @field:Json(name = "imagePath")
    var imagePath: String,
    @field:Json(name = "user")
    var userID: User,
    @field:Json(name = "likes")
    var likes: MutableSet<User>,
    @field:Json(name = "dislikes")
    var dislikes: MutableSet<User>,
    @field:Json(name = "timeCreated")
    var timeCreated: Date,
    @field:Json(name = "expireDate")
    var expireDate: Date
) : Parcelable