package com.ssafy.guseul.presentation.board

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.ItemBoardBannerBinding

class ViewPagerAdapter(private val datas: ArrayList<BannerData>) : RecyclerView.Adapter<ViewHolderPage>() {

    lateinit var binding: ItemBoardBannerBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPage {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_board_banner, parent, false)
        return ViewHolderPage(binding)
    }

    override fun onBindViewHolder(holder: ViewHolderPage, position: Int) {
        val viewHolder: ViewHolderPage = holder
        viewHolder.onBind(datas[position % datas.size])
    }

    override fun getItemCount(): Int = Int.MAX_VALUE

}

class ViewHolderPage(val binding: ItemBoardBannerBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(datas: BannerData) {
        binding.ivHomeBannerImg.setImageResource(datas.image)
    }
}