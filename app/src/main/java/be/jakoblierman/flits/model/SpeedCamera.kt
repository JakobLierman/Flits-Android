package be.jakoblierman.flits.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class SpeedCamera(
    @field:Json(name = "_id")
    val id: String,
    @field:Json(name = "location")
    var location: String,
    @field:Json(name = "kind")
    var kind: String,
    @field:Json(name = "description")
    var description: String,
    @field:Json(name = "imagePath")
    var imagePath: String,
    @field:Json(name = "user")
    var user: User,
    @field:Json(name = "likes")
    var likes: MutableSet<User>,
    @field:Json(name = "dislikes")
    var dislikes: MutableSet<User>,
    @field:Json(name = "timeCreated")
    var timeCreated: Date,
    @field:Json(name = "expireDate")
    var expireDate: Date
) : Parcelable