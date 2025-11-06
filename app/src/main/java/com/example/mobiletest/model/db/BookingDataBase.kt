package com.example.mobiletest.model.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobiletest.model.dao.BookingDao
import com.example.mobiletest.MobileTestApp
import com.example.mobiletest.model.entity.Booking

/**
 * @author SuK
 * @time 2025/11/6 11:44
 * @desc
 */
@Database(
    version = 1,
    entities = [Booking::class],
    exportSchema = false
)
abstract class BookingDataBase : RoomDatabase() {
    companion object {
        val INSTANCE = Room
            .databaseBuilder(
                MobileTestApp.Companion.INSTANCE,
                BookingDataBase::class.java,
                "booking.db"
            )
            .fallbackToDestructiveMigration(true)
            .build()
    }

    abstract fun bookingDao(): BookingDao
}