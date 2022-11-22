package com.ssafy.guseul.presentation.mypage

import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.FragmentMyHistoryBinding
import com.ssafy.guseul.domain.entity.board.BoardEntity
import com.ssafy.guseul.presentation.base.BaseFragment
import com.ssafy.guseul.presentation.board.BoardAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyHistoryFragment : BaseFragment<FragmentMyHistoryBinding>(R.layout.fragment_my_history) {

    override fun initView() {
        initBoard()
    }

    fun initBoard() {
        val list: ArrayList<BoardEntity> = ArrayList<BoardEntity>().let {
            it.apply {
                add(BoardEntity(1, "탕자감 맛있겠당", "탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱", 1, location = "구미시 진평동"))
                add(BoardEntity(1, "탕자감 맛있겠당", "탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱", 1, location = "구미시 진평동"))
                add(BoardEntity(1, "탕자감 맛있겠당", "탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱", 1, location = "구미시 진평동"))
                add(BoardEntity(1, "탕자감 맛있겠당", "탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱", 1, location = "구미시 진평동"))
                add(BoardEntity(1, "탕자감 맛있겠당", "탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱", 1, location = "구미시 진평동"))
                add(BoardEntity(1, "탕자감 맛있겠당", "탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱", 1, location = "구미시 진평동"))
                add(BoardEntity(1, "탕자감 맛있겠당", "탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱 탕자감 짱짱", 1, location = "구미시 진평동"))
            }
        }
        binding.rvHistory.adapter = BoardAdapter(list)
        binding.rvHistory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }
}