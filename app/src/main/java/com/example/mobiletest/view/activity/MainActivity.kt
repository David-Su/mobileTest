package com.example.mobiletest.view.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobiletest.view.adapter.BookingSegmentsAdapter
import com.example.mobiletest.databinding.ActivityMainBinding
import com.example.mobiletest.viewmodel.MainViewModel
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    private val bookingSegmentsAdapter = BookingSegmentsAdapter()
    private val mainViewModel by viewModels<MainViewModel>()
    private var isFirstResume = true //是否为第一次调用onResume，默认为true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //全面屏适配
        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //界面列表绑定适配器
        binding.rv.adapter = bookingSegmentsAdapter
        binding.btn.setOnClickListener { mainViewModel.refresh() }
        //加载初始化数据
        mainViewModel.initData()
        //监听Booking数据变化
        mainViewModel.bookingData.observe(this) { booking ->
            booking ?: return@observe
            Toast.makeText(this,"数据加载成功", Toast.LENGTH_SHORT).show()
            //控制台打印数据
            val printData = Json.encodeToString(booking)
            print(printData)
            Log.d("mobileTest", printData)
            //给控件设置数据
            binding.tv.text = StringBuilder().apply {
                append("shipReference:${booking.shipReference}")
                appendLine()
                append("shipToken:${booking.shipToken}")
                appendLine()
                append("canIssueTicketChecking:${booking.canIssueTicketChecking}")
                appendLine()
                append("expiryTime:${booking.expiryTime}(${SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(booking.expiryTime?.toLong())})")
                appendLine()
                append("duration:${booking.duration}")
            }
            bookingSegmentsAdapter.setData(booking.segments)
        }
        //监听错误状态，错误处理
        mainViewModel.onError.observe(this) {
            Toast.makeText(this,"加载错误，请刷新重试", Toast.LENGTH_LONG).show()
        }
    }

    override fun onResume() {
        super.onResume()
        //如果第一次onResume不用处理，因为onCreate会初始化数据。后续的onResume需要刷新数据
        if (isFirstResume) {
            isFirstResume = false
        } else {
            mainViewModel.refresh()
        }
    }
}