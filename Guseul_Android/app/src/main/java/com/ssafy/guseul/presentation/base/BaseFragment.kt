package com.ssafy.guseul.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.ssafy.guseul.R

abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes val layoutRes: Int) : Fragment() {
    private lateinit var _binding: T
    val binding: T get() = _binding

    private lateinit var _navController: NavController
    val navController: NavController get() = _navController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this@BaseFragment
        _navController = Navigation.findNavController(requireActivity(), R.id.nav_host)
        initView()
    }

    //navigate로 프래그먼트 변경
    fun navigate(direction: NavDirections) {
        _navController.navigate(direction)
    }

    //view 초기화
    abstract fun initView()

    override fun onDestroyView() {
        super.onDestroyView()
    }
}