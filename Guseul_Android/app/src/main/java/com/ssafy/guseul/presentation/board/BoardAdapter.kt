package com.ssafy.guseul.presentation.board

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.ItemBoardRecyclerViewBinding
import com.ssafy.guseul.domain.entity.board.BoardEntity

class BoardAdapter(
    private val onPostClicked: (postId: Int) -> Unit
) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    private var posts: List<BoardEntity> = listOf()
    lateinit var binding: ItemBoardRecyclerViewBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_board_recycler_view, parent, false)
        return ViewHolder(binding, onPostClicked)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    inner class ViewHolder(
        private val binding: ItemBoardRecyclerViewBinding,
        private val onPostClicked: (postId: Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BoardEntity) {
            binding.board = data
            binding.root.setOnClickListener {
                onPostClicked(data.postId)
            }
        }
    }

    fun setBoard(boardEntity: List<BoardEntity>) {
        this.posts = boardEntity
        notifyDataSetChanged()
    }
}