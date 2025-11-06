package com.example.mobiletest.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobiletest.model.entity.Booking

/**
 * @author SuK
 * @time 2025/11/6 15:01
 * @desc Booking的数据库操作封装，插入删除只针对单行操作
 */
@Dao
interface BookingDao {

    @Query("SELECT * FROM Booking")
    fun getBookings(): List<Booking>?

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    fun insertBook(booking: Booking): Long

    @Query("DELETE FROM Booking")
    fun deleteAll()
}