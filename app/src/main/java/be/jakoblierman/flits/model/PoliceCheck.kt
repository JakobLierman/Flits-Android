package be.jakoblierman.flits.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class PoliceCheck(
    @field:Json(name = "id")
    val id: String? = null,
    @field:Json(name = "Location")
    var location: String,
    @field:Json(name = "description")
    var description: String = "",
    @field:Json(name = "imagePath")
    var imagePath: String = "",
    @field:Json(name = "user")
    var user: User,
    @field:Json(name = "likes")
    var likes: MutableSet<User> = mutableSetOf(),
    @field:Json(name = "dislikes")
    var dislikes: MutableSet<User> = mutableSetOf(),
    @field:Json(name = "timeCreated")
    var timeCreated: Date? = null,
    @field:Json(name = "expireDate")
    var expireDate: Date? = null
) : Parcelable