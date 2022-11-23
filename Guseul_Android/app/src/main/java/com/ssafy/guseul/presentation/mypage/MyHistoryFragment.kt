package com.ssafy.guseul.presentation.mypage

import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.FragmentMyHistoryBinding
import com.ssafy.guseul.domain.entity.user.BoardEntity
import com.ssafy.guseul.presentation.base.BaseFragment
import com.ssafy.guseul.presentation.base.ViewState
import com.ssafy.guseul.presentation.board.BoardAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyHistoryFragment : BaseFragment<FragmentMyHistoryBinding>(R.layout.fragment_my_history) {

    private val args by navArgs<MyHistoryFragmentArgs>()
    private val historyViewModel by viewModels<HistoryViewModel>()

    override fun initView() {
        initBoard()
    }

    fun initBoard() {
        historyViewModel.boardEntity.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    // TODO : Adapter.setBoard
                    // args.userId
                }
                else -> {
                    // Do Nothing
                }
            }
        }
        historyViewModel.getUserHistory(args.userId)
        //binding.rvHistory.adapter = BoardAdapter(list)
        binding.rvHistory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}