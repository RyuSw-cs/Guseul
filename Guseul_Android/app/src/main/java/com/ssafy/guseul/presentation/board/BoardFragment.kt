package com.ssafy.guseul.presentation.board

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.FragmentBoardBinding
import com.ssafy.guseul.domain.entity.user.BoardEntity
import com.ssafy.guseul.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import java.lang.Math.ceil

@AndroidEntryPoint
class BoardFragment : BaseFragment<FragmentBoardBinding>(R.layout.fragment_board) {

    private var bannerPosition = 0
    lateinit var job : Job

    override fun initView() {
        initBanner()
        initBoard()
    }

    fun initBanner() {
        val list: ArrayList<BannerData> = ArrayList<BannerData>().let {
            it.apply {
                add(BannerData(R.drawable.bg_home_banner_img_1, "구미시 맛집", "https://m.blog.naver.com/sukzintro/221330856422"))
                add(BannerData(R.drawable.bg_home_banner_img_1, "구미시 맛집", "https://m.blog.naver.com/sukzintro/221330856422"))
                add(BannerData(R.drawable.bg_home_banner_img_1, "구미시 맛집", "https://m.blog.naver.com/sukzintro/221330856422"))
                add(BannerData(R.drawable.bg_home_banner_img_1, "구미시 맛집", "https://m.blog.naver.com/sukzintro/221330856422"))
                add(BannerData(R.drawable.bg_home_banner_img_1, "구미시 맛집", "https://m.blog.naver.com/sukzintro/221330856422"))
            }
        }

        binding.vpBoardBanner.adapter = ViewPagerAdapter(list)
        binding.vpBoardBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.tvBoardBannerNumber.text = getString(R.string.content_board_banner_page, 1, list.size)
        binding.vpBoardBanner.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bannerPosition = position
                binding.tvBoardBannerNumber.text = getString(R.string.content_board_banner_page, (bannerPosition % list.size) + 1, list.size)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when (state) {
                    ViewPager2.SCROLL_STATE_IDLE ->{
                        if (!job.isActive) scrollJobCreate()
                    }

                    ViewPager2.SCROLL_STATE_DRAGGING -> job.cancel()

                    ViewPager2.SCROLL_STATE_SETTLING -> {}
                }
            }
        })

        bannerPosition = Int.MAX_VALUE / 2 - ceil(list.size.toDouble() / 2).toInt()

        binding.vpBoardBanner.setCurrentItem(bannerPosition, false)
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
        binding.rvBoard.adapter = BoardAdapter(list)
        binding.rvBoard.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()
        scrollJobCreate()
    }

    override fun onPause() {
        super.onPause()
        job.cancel()
    }

    fun scrollJobCreate() {
        job = lifecycleScope.launchWhenResumed {
            delay(1500)
            binding.vpBoardBanner.setCurrentItem(++bannerPosition, true)
        }
    }
}