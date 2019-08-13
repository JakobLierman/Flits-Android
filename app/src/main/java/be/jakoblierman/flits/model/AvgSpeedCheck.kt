package be.jakoblierman.flits.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class AvgSpeedCheck(
    @field:Json(name = "_id")
    val id: String? = null,
    @field:Json(name = "beginLocation")
    var beginLocation: String,
    @field:Json(name = "endLocation")
    var endLocation: String,
    @field:Json(name = "user")
    var user: User,
    @field:Json(name = "likes")
    var likes: MutableSet<User> = mutableSetOf(),
    @field:Json(name = "dislikes")
    var dislikes: MutableSet<User> = mutableSetOf(),
    @field:Json(name = "timeCreated")
    var timeCreated: Date? = null
) : Parcelable