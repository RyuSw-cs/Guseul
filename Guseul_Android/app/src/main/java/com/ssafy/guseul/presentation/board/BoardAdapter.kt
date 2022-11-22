package com.ssafy.guseul.presentation.board

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.ItemBoardRecyclerViewBinding
import com.ssafy.guseul.domain.entity.board.BoardEntity

class BoardAdapter(private val datas: ArrayList<BoardEntity>) : RecyclerView.Adapter<viewHolder>() {

    lateinit var binding: ItemBoardRecyclerViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_board_recycler_view, parent, false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val viewHolder: viewHolder = holder
        viewHolder.onBind(datas[position])
    }

    override fun getItemCount(): Int = datas.size
}

class viewHolder(val binding: ItemBoardRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(datas: BoardEntity) {
        binding.tvBoardRecyclerTitle.text = datas.title
        binding.tvBoardRecyclerContent.text = datas.content
        binding.tvBoardReyclerState.text = "모집중"
        binding.tvBoardRecyclerLocation.text = datas.location
    }
}