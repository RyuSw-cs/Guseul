package com.ssafy.guseul.presentation.board.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ssafy.guseul.R
import com.ssafy.guseul.databinding.DialogAddBoardTimeBinding
import java.util.*
import kotlin.math.roundToInt

class AddPostTimeDialog(
    private val time: String,
    private val makeTime: (String) -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var binding: DialogAddBoardTimeBinding

    private val dayTimeList by lazy { resources.getStringArray(R.array.category_number_picker_day) }
    private val hourList by lazy { resources.getStringArray(R.array.category_number_picker_zero_to_twelve) }
    private val minuteList by lazy { resources.getStringArray(R.array.category_number_zero_to_fifty_10) }

    private var getDayTimeIdx = 0
    private var getHourIdx = 0
    private var getMinuteIdx = 0

    private val calendar = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogAddBoardTimeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPreviousTime()
        initRegistTimeButtonEvent()
    }

    private fun checkPreviousTime() {
        //시간이면 현재 시간 받아와서 적용
        if (time == "시간") {
            getCurrentDateIndex()
        } else {
            val customTime = time.split(" ")

            getDayTimeIdx = dayTimeList.indexOf(customTime[0])
            getHourIdx = hourList.indexOf(customTime[1])
            getMinuteIdx = minuteList.indexOf(customTime[2])
        }
        initCustomPicker()
    }

    private fun initCustomPicker() {
        initDatePicker()
        initHourPicker()
        initMinutePicker()
    }

    private fun getCurrentDateIndex() {
        getDayTimeIdx = if (calendar.get(Calendar.HOUR_OF_DAY) > 12) {
            1
        } else {
            0
        }
        val currentHour = if (calendar.get(Calendar.HOUR) > 9) {
            calendar.get(Calendar.HOUR).toString()
        } else {
            "0" + calendar.get(Calendar.HOUR)
        }

        val currentMinute = if (calendar.get(Calendar.MINUTE) > 9) {
            val minute = calendar.get(Calendar.MINUTE).toDouble() / 10
            minute.roundToInt().toString() + "0"
        } else {
            "00"
        }
        getHourIdx = hourList.indexOf(currentHour)
        getMinuteIdx = minuteList.indexOf(currentMinute)
    }

    private fun initHourPicker() {
        binding.npHour.apply {
            minValue = 0
            maxValue = hourList.size - 1
            value = getHourIdx
            wrapSelectorWheel = false
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            setSelectedTypeface(
                ResourcesCompat.getFont(
                    requireContext(),
                    R.font.roboto_medium
                )
            )
            setFormatter { value -> hourList[value] }
        }
    }

    private fun initDatePicker() {
        binding.npAmPm.apply {
            minValue = 0
            maxValue = dayTimeList.size - 1
            value = getDayTimeIdx
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            wrapSelectorWheel = false
            setSelectedTypeface(
                ResourcesCompat.getFont(
                    requireContext(),
                    R.font.roboto_medium
                )
            )
            setFormatter { value -> dayTimeList[value] }
        }
    }

    private fun initMinutePicker() {
        binding.npMinute.apply {
            minValue = 0
            maxValue = minuteList.size - 1
            value = getMinuteIdx
            wrapSelectorWheel = false
            descendantFocusability = NumberPicker.FOCUS_BLOCK_DESCENDANTS
            setSelectedTypeface(
                ResourcesCompat.getFont(
                    requireContext(),
                    R.font.roboto_medium
                )
            )
            setFormatter { value -> minuteList[value] }
        }
    }

    private fun initRegistTimeButtonEvent() {
        binding.btnOkay.setOnClickListener {
            val createTimeData =
                "${dayTimeList[binding.npAmPm.value]} ${hourList[binding.npHour.value]}시 ${minuteList[binding.npMinute.value]}분"
            makeTime(createTimeData)
            dismiss()
        }
    }
}