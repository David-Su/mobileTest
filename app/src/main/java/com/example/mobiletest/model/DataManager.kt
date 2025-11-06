package com.example.mobiletest.model

import com.example.mobiletest.model.dao.BookingDao
import com.example.mobiletest.model.db.BookingDataBase
import com.example.mobiletest.model.entity.Booking
import com.example.mobiletest.model.service.BookingService
import kotlin.collections.firstOrNull

/**
 * @author SuK
 * @time 2025/11/6 20:34
 * @desc Booking的数据提供者
 */
class DataManager {

    private val dao: BookingDao = BookingDataBase.Companion.INSTANCE.bookingDao()
    private val service: BookingService = BookingService()

    /**
     * 获取有效的Booking数据(即非过期数据)，优先使用缓存
     */
    suspend fun getValidData(): Booking? {
        val cache = dao.getBookings()?.firstOrNull()
        if (cache == null) { //缓存为空，获取网络数据
            return fetchDataAndCache()
        }
        val cacheExpiryTime = cache.expiryTime
        //如果缓存超时了，获取网络数据，如果没超时，使用缓存
        return if (cacheExpiryTime == null || System.currentTimeMillis() > cacheExpiryTime.toLong()) {
            fetchDataAndCache()
        } else {
            cache
        }
    }

    /**
     * 获取网络数据，同时缓存到本地
     */
    suspend fun fetchDataAndCache(): Booking? {
        val result = service.getBooking()
        //网络数据不为空，缓存到本地
        if (result != null) {
            dao.deleteAll()
            dao.insertBook(result)
        }
        return result
    }
}