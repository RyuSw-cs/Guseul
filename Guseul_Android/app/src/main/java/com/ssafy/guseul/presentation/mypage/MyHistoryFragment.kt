package com.ssafy.guseul.presentation.mypage

import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssafy.guseul.R
import com.ssafy.guseul.common.util.setLoadingDialog
import com.ssafy.guseul.databinding.FragmentMyHistoryBinding
import com.ssafy.guseul.presentation.base.BaseFragment
import com.ssafy.guseul.presentation.base.ViewState
import com.ssafy.guseul.presentation.board.BoardAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyHistoryFragment : BaseFragment<FragmentMyHistoryBinding>(R.layout.fragment_my_history) {

    private val args by navArgs<MyHistoryFragmentArgs>()
    private val historyViewModel by viewModels<HistoryViewModel>()
    private val boardAdapter by lazy {
        BoardAdapter()
    }

    override fun initView() {
        initListener()
        initBoard()
    }

    fun initListener() {
        binding.btnArrowLeft.setOnClickListener { popBackStack() }
    }

    fun initBoard() {
        binding.rvHistory.adapter = boardAdapter
        binding.rvHistory.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        historyViewModel.boardEntity.observe(viewLifecycleOwner) {
            when (it) {
                is ViewState.Success -> {
                    // TODO : Adapter.setBoard
                    // args.userId
                    requireContext().setLoadingDialog(false)
                    val result = it.value
                    result?.let { boardAdapter.setBoard(it) }
                }
                else -> {
                    // Do Nothing
                }
            }
        }
        historyViewModel.getUserHistory(args.userId)
    }

}