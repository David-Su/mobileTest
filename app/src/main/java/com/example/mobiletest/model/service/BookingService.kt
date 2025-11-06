package com.example.mobiletest.model.service

import com.example.mobiletest.model.entity.Booking
import kotlinx.coroutines.delay

/**
 * @author SuK
 * @time 2025/11/6 15:57
 * @desc 模拟获取Booking的网路请求的Service层
 */
class BookingService {

    /**
     * 模拟从网路获取Booking数据
     */
    suspend fun getBooking(): Booking? {
        //模拟网络请求等待
        delay(500)
        return getMockData()
    }

    /**
     * 获取模拟的Booking数据,每次生成都会以生成时刻的30秒之后设为过期时间
     */
    private fun getMockData(): Booking {
        val expiryTimeInMill = System.currentTimeMillis() + 30 * 1000
        return Booking(
            "ABCDEF",
            "shipToken",
            false,
            expiryTimeInMill.toString(),
            2430,
            listOf(
                Booking.Segment(
                    1,
                    Booking.Segment.OriginAndDestinationPair(
                        Booking.Segment.OriginAndDestinationPair.Location(
                            "BBB",
                            "BBB DisplayName",
                            "www.ship.com",
                        ),
                        "BBB City",
                        Booking.Segment.OriginAndDestinationPair.Location(
                            "AAA",
                            "AAA DisplayName",
                            "www.ship.com"
                        ),
                        "AAA City"
                    )
                ),
                Booking.Segment(
                    2,
                    Booking.Segment.OriginAndDestinationPair(
                        Booking.Segment.OriginAndDestinationPair.Location(
                            "CCC",
                            "CCC DisplayName",
                            "www.ship.com",
                        ),
                        "CCC City",
                        Booking.Segment.OriginAndDestinationPair.Location(
                            "BBB",
                            "BBB DisplayName",
                            "www.ship.com"
                        ),
                        "BBB City"
                    )
                )
            )
        )
    }

}