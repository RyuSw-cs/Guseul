package com.ssafy.guseul.presentation.board

import android.text.Editable
import android.text.TextWatcher
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.showAlignTop
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.FragmentBoardBinding
import com.ssafy.guseul.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import java.lang.Math.ceil

@AndroidEntryPoint
class BoardFragment : BaseFragment<FragmentBoardBinding>(R.layout.fragment_board) {

    private val categorySet = mutableSetOf<Int>(1, 2, 3, 4)
    private val viewModel by activityViewModels<BoardViewModel>()
    private val boardAdapter by lazy {
        BoardAdapter(this::getPost)
    }
    private var bannerPosition = 0
    private var keyword = ""
    lateinit var job: Job

    override fun initView() {
        initBanner()
        initBoard()
        initListener()
        addCategoryButtonEvent()
    }

    private fun addCategoryButtonEvent() {
        binding.run {
            btnBoardTaxi.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) categorySet.add(1)
                else categorySet.remove(1)
                viewModel.searchPost(keyword, categorySet)
            }
            btnBoardEat.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) categorySet.add(2)
                else categorySet.remove(2)
                viewModel.searchPost(keyword, categorySet)
            }
            btnBoardBuy.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) categorySet.add(3)
                else categorySet.remove(3)
                viewModel.searchPost(keyword, categorySet)
            }
            btnBoardTalk.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) categorySet.add(4)
                else categorySet.remove(4)
                viewModel.searchPost(keyword, categorySet)
            }
        }
    }

    fun initListener() {
        binding.btnGoToAdd.setOnClickListener {
            navigate(BoardFragmentDirections.actionBoardFragmentToAddPostFragment())
        }
        binding.etSearch.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                s?.let {
                    keyword = it.toString()
                    viewModel.searchPost(it.toString(), categorySet)
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }

    fun initBanner() {
        val list: ArrayList<BannerData> = ArrayList<BannerData>().let {
            it.apply {
                add(
                    BannerData(
                        R.drawable.bg_home_banner_img_1,
                        "구미시 맛집",
                        "https://m.blog.naver.com/sukzintro/221330856422"
                    )
                )
                add(
                    BannerData(
                        R.drawable.bg_home_banner_img_1,
                        "구미시 맛집",
                        "https://m.blog.naver.com/sukzintro/221330856422"
                    )
                )
                add(
                    BannerData(
                        R.drawable.bg_home_banner_img_1,
                        "구미시 맛집",
                        "https://m.blog.naver.com/sukzintro/221330856422"
                    )
                )
                add(
                    BannerData(
                        R.drawable.bg_home_banner_img_1,
                        "구미시 맛집",
                        "https://m.blog.naver.com/sukzintro/221330856422"
                    )
                )
                add(
                    BannerData(
                        R.drawable.bg_home_banner_img_1,
                        "구미시 맛집",
                        "https://m.blog.naver.com/sukzintro/221330856422"
                    )
                )
            }
        }

        binding.vpBoardBanner.adapter = ViewPagerAdapter(list)
        binding.vpBoardBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        binding.tvBoardBannerNumber.text =
            getString(R.string.content_board_banner_page, 1, list.size)
        binding.vpBoardBanner.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bannerPosition = position
                binding.tvBoardBannerNumber.text = getString(
                    R.string.content_board_banner_page,
                    (bannerPosition % list.size) + 1,
                    list.size
                )
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                when (state) {
                    ViewPager2.SCROLL_STATE_IDLE -> {
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
        binding.rvBoard.adapter = boardAdapter
        binding.rvBoard.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.filteredList.observe(viewLifecycleOwner) { response ->
            val result = response
            result?.let { boardAdapter.setBoard(it) }
        }
        viewModel.getPosts()

        binding.btnGoToAdd.showAlignTop(makeBalloon())
    }


    private fun makeBalloon(): Balloon {
        val popUpMessage = Balloon.Builder(requireContext())
            .setWidth(BalloonSizeSpec.WRAP)
            .setHeight(BalloonSizeSpec.WRAP)
            .setText(resources.getString(R.string.content_board_create_message))
            .setTextColorResource(R.color.white)
            .setTextTypeface(ResourcesCompat.getFont(requireContext(), R.font.roboto_medium)!!)
            .setTextSize(13f)
            .setIconHeight(20)
            .setMarginBottom(6)
            .setIconWidth(20)
            .setArrowSize(12)
            .setArrowPosition(0.5f)
            .setPaddingTop(8)
            .setPaddingLeft(13)
            .setPaddingRight(13)
            .setPaddingBottom(8)
            .setCornerRadius(10f)
            .setBackgroundColorResource(R.color.gainsboro)

        return popUpMessage.build()
    }

    override fun onResume() {
        super.onResume()
        scrollJobCreate()
        viewModel.getPosts()
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

    private fun getPost(postId: Int) {
        // viewModel.getPost(postId)
        navigate(BoardFragmentDirections.actionBoardFragmentToBoardDetailFragment(postId))
    }
}