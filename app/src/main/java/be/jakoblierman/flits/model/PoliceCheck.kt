package be.jakoblierman.flits.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
import java.util.*

/**
 * Police Check entity
 *
 * @property id
 * @property location
 * @property description
 * @property imagePath
 * @property user
 * @property likes
 * @property dislikes
 * @property timeCreated
 * @property expireDate
 */
@Parcelize
data class PoliceCheck(
    @field:Json(name = "_id")
    val id: String? = null,
    @field:Json(name = "location")
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