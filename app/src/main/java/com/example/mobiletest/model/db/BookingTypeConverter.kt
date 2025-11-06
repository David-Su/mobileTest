package com.example.mobiletest.model.db

import androidx.room.TypeConverter
import com.example.mobiletest.model.entity.Booking
import kotlinx.serialization.json.Json

/**
 * @author SuK
 * @time 2025/11/6 16:23
 * @desc
 */
class BookingTypeConverter {

    @TypeConverter
    fun segmentsToString(list: List<Booking.Segment>?): String? {
        return list?.let { Json.Default.encodeToString(it) }
    }

    @TypeConverter
    fun stringToSegments(str: String?): List<Booking.Segment>? {
        return str?.let { Json.Default.decodeFromString<List<Booking.Segment>?>(it) }
    }
}