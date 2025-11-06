package com.example.mobiletest.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiletest.databinding.ItemBookingSegmentBinding
import com.example.mobiletest.model.entity.Booking

/**
 * @author SuK
 * @time 2025/11/6 21:02
 * @desc 首页展示列表适配器
 */
class BookingSegmentsAdapter : RecyclerView.Adapter<BookingSegmentsAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemBookingSegmentBinding) : RecyclerView.ViewHolder(binding.root)

    private var items: List<Booking.Segment> = listOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemBookingSegmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /**
     * 每一个item分为左右两部分，分别展示origin相关的数据和destination相关的数据
     */
    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = items[position]
        val pair = item.originAndDestinationPair
        val binding = holder.binding
        //填写origin数据
        binding.tvOriginCity.text = "originCity:${pair?.originCity}"
        binding.tvOriginCode.text = "code:${pair?.origin?.code}"
        binding.tvOriginDisplayName.text =
            "displayName:${pair?.origin?.displayName}"
        binding.tvOriginUrl.text = "url:${pair?.origin?.url}"
        //填写destination数据
        binding.tvDesCity.text = "destinationCity:${pair?.destinationCity}"
        binding.tvDesCode.text = "code:${pair?.destination?.code}"
        binding.tvDesDisplayName.text =
            "displayName:${pair?.destination?.displayName}"
        binding.tvDesUrl.text = "url:${pair?.destination?.url}"
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setData(segments: List<Booking.Segment>?) {
        items = segments ?: listOf()
        notifyDataSetChanged()
    }
}