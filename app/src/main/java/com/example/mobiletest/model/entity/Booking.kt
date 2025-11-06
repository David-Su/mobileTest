package com.example.mobiletest.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.serialization.Serializable
import com.example.mobiletest.model.db.BookingTypeConverter

/**
 * @author SuK
 * @time 2025/11/6 12:01
 * @desc 数据类，用于网络请求与本地数据库缓存
 */
@Entity
@Serializable
@TypeConverters(BookingTypeConverter::class)
data class Booking(
    val shipReference: String?,
    val shipToken: String?,
    val canIssueTicketChecking: Boolean?,
    val expiryTime: String?,
    val duration: Int?,
    val segments: List<Segment>?,
) {

    @PrimaryKey()
    var key: Long? = null

    @Serializable
    data class Segment(
        val id: Int?,
        val originAndDestinationPair: OriginAndDestinationPair?
    ) {
        @Serializable
        data class OriginAndDestinationPair(
            val destination: Location?,
            val destinationCity: String?,
            val origin: Location?,
            val originCity: String?
        ) {
            @Serializable
            data class Location(
                val code: String?,
                val displayName: String?,
                val url: String?
            )
        }
    }
}