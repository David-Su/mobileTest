package com.example.mobiletest.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mobiletest.model.DataManager
import com.example.mobiletest.model.entity.Booking
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author SuK
 * @time 2025/11/6 21:37
 * @desc main模块的ViewModel
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    //外部使用的LiveData，外部不允许使用MutableLiveData的setValue方法，所以声明为LiveData
    val bookingData get():LiveData<Booking?> = _bookingData //Booking数据
    val onError get():LiveData<Unit> = _onError //加载错误通知

    //内部使用的LiveData，需要用到MutableLiveData的setValue方法，所以声明为MutableLiveData
    private val _bookingData: MutableLiveData<Booking?> = MutableLiveData<Booking?>()
    private val _onError: MutableLiveData<Unit> = MutableLiveData<Unit>()

    private val dataManager = DataManager()

    /**
     * 初始化数据
     */
    fun initData() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            _onError.postValue(Unit)
        }) {
            val result = dataManager.getValidData() ?: return@launch
            withContext(Dispatchers.Main) {
                _bookingData.value = result
            }
        }
    }

    /**
     * 刷新数据
     */
    fun refresh() {
        viewModelScope.launch(Dispatchers.IO + CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            _onError.postValue(Unit)
        }) {
            val result = dataManager.fetchDataAndCache() ?: return@launch
            withContext(Dispatchers.Main) {
                _bookingData.value = result
            }
        }
    }
}