package com.example.numbergame

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nambergame.R
import com.example.nambergame.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.text.NumberFormat



class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    var TAG = MainActivity::class.java.toString()
    private var mOnClickNumberListener: OnClickNumberListener? = null
    private var selectedNumber: Int = 0
    private var number1: Int = 0
    private var number2: Int = 0
    private var number3: Int = 0
    private var operatorSign: String? = null
    private var score: Int = 0

    private var tvOnetrue: Boolean = false
    private var tvTwotrue: Boolean = false
    private var tvThreetrue: Boolean = false

    var randomNumList = ArrayList<String>()
    private lateinit var countTimer: CountDownTimer

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOne.setOnClickListener(this)
        binding.btnTwo.setOnClickListener(this)
        binding.btnTwo.setOnClickListener(this)

        binding.btnThree.setOnClickListener(this)
        binding.btnFour.setOnClickListener(this)
        binding.btnFive.setOnClickListener(this)

        binding.btnSix.setOnClickListener(this)
        binding.btnSeven.setOnClickListener(this)
        binding.btnEight.setOnClickListener(this)

        binding.btnNine.setOnClickListener(this)
        binding.btnTen.setOnClickListener(this)
        binding.btnTryNext.setOnClickListener(this)

        binding.btnPlus.setOnClickListener(this)
        binding.btnMinus.setOnClickListener(this)
        binding.btnMulti.setOnClickListener(this)
        binding.btnEquell.setOnClickListener(this)
        binding.tvScore.text = getString(R.string.score) + score.toString()

        setRandomNumbers()
        setTimer()

        mOnClickNumberListener = object : OnClickNumberListener {
            override fun getClickednumber(number: Int) {
                selectedNumber = number
            }
        }

    }

    fun setRandomNumbers() {
        randomNumList = randomNumberList()

        if (randomNumList.size > 0) {
            binding.tvOne.text = randomNumList.get(0)
            binding.tvTwo.text = randomNumList.get(1)
            binding.tvThree.text = randomNumList.get(2)
        }
    }

    fun randomNumberList(): ArrayList<String> {
        val randomNumList = ArrayList<String>()
        val randomNum1 = ((1..(50) / 2).random() * 2)

        var randomNumber2 = (Math.random() * 50).toInt()
        randomNumber2 += if (randomNumber2 % 2 == 0) 1 else 0

        var randomNumber3 = (Math.random() * 50).toInt()
        randomNumber3 = randomNumber3 * 2 + 1

        randomNumList.add(randomNum1.toString())
        randomNumList.add(randomNumber2.toString())
        randomNumList.add(randomNumber3.toString())

        return randomNumList
    }


    fun setTimer() {
        countTimer = object : CountDownTimer(45000, 1000) {

            @SuppressLint("NewApi")
            override fun onTick(millisUntilFinished: Long) {
                var f: NumberFormat = DecimalFormat("00")
                var hour: Long = millisUntilFinished / 3600000 % 24
                var min: Long = millisUntilFinished / 60000 % 60
                var sec: Long = millisUntilFinished / 1000 % 60
                binding.tvTimer.setTextColor(getColor(R.color.time_remian_color))
                binding.tvTimer.text =
                    getString(R.string.time_in) + f.format(hour) + ":" + f.format(min) + ":" + f.format(
                        sec
                    )
            }

            @SuppressLint("NewApi")
            override fun onFinish() {
                binding.tvTimer.setTextColor(getColor(R.color.time_out_color))
                binding.tvTimer.text = getString(R.string.time_out)
                binding.tvOne.text = getString(R.string.empty_tv)
                binding.tvTwo.text = getString(R.string.empty_tv)
                binding.tvThree.text = getString(R.string.empty_tv)
                binding.btnTryNext.visibility = View.VISIBLE
                binding.tvOne.setBackgroundDrawable(getDrawable(R.drawable.textview_suare_shape))
                binding.tvTwo.setBackgroundDrawable(getDrawable(R.drawable.textview_suare_shape))
                binding.tvThree.setBackgroundDrawable(getDrawable(R.drawable.textview_suare_shape))

            }
        }
        countTimer.start()
    }


    @SuppressLint("NewApi")
    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.btn_one -> {
                mOnClickNumberListener?.getClickednumber(1)
            }
            R.id.btn_two -> {
                mOnClickNumberListener?.getClickednumber(2)
            }
            R.id.btn_three -> {
                mOnClickNumberListener?.getClickednumber(3)
            }
            R.id.btn_four -> {
                mOnClickNumberListener?.getClickednumber(4)
            }
            R.id.btn_five -> {
                mOnClickNumberListener?.getClickednumber(5)
            }
            R.id.btn_six -> {
                mOnClickNumberListener?.getClickednumber(6)
            }
            R.id.btn_seven -> {
                mOnClickNumberListener?.getClickednumber(7)
            }
            R.id.btn_eight -> {
                mOnClickNumberListener?.getClickednumber(8)
            }
            R.id.btn_nine -> {
                mOnClickNumberListener?.getClickednumber(9)
            }
            R.id.btn_ten -> {
                mOnClickNumberListener?.getClickednumber(10)
            }
            R.id.btn_try_next -> {
                setTvvaluesFalse(false, false, false)
                setRandomNumbers()
                setTimer()
                binding.btnTryNext.visibility = View.GONE
                binding.tvTimer.setTextColor(getColor(R.color.time_remian_color))
                binding.tvOne.setTextColor(Color.BLACK)
            }
            R.id.btn_plus -> {
                operatorSign = getString(R.string.plus)
                if (selectedNumber > 0) {
                    if (number1 == 0) {
                        number1 = selectedNumber
                    } else if (number2 == 0) {
                        number2 = selectedNumber
                    } else {
                        number3 = selectedNumber
                    }
                }
            }
            R.id.btn_minus -> {
                operatorSign = getString(R.string.minus)
                if (selectedNumber > 0) {
                    if (number1 == 0) {
                        number1 = selectedNumber
                    } else if (number2 == 0) {
                        number2 = selectedNumber
                    } else {
                        number3 = selectedNumber
                    }
                }
            }
            R.id.btn_multi -> {
                operatorSign = getString(R.string.multiple)
                if (selectedNumber > 0) {
                    if (number1 == 0) {
                        number1 = selectedNumber
                    } else if (number2 == 0) {
                        number2 = selectedNumber
                    } else {
                        number3 = selectedNumber
                    }
                }
            }
            R.id.btn_equell -> {
                if (selectedNumber > 0) {
                    if (number1 == 0) {
                        number1 = selectedNumber
                    } else if (number2 == 0) {
                        number2 = selectedNumber
                    } else {
                        number3 = selectedNumber
                    }
                    checkNumberToMatch(number1, number2, number3)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Please click a valid NUMBER",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            else -> {
                Log.d(TAG, "No id")
            }
        }
    }


    fun checkNumberToMatch(num1: Int, num2: Int, num3: Int) {

        var finalValue: Int = 0
        if (num3 > 0) {
            finalValue = (num1 * num2) + num3
            setNumberToZero(0, 0, 0)
        } else {
            if (operatorSign.equals(getString(R.string.plus))) {
                finalValue = num1 + num2
                setNumberToZero(0, 0, 0)
            } else if (operatorSign.equals(getString(R.string.minus))) {
                finalValue = num1 - num2
                setNumberToZero(0, 0, 0)
            } else if (operatorSign.equals(getString(R.string.multiple))) {
                finalValue = num1 * num2
                setNumberToZero(0, 0, 0)
            } else {
                isNumberMatch(selectedNumber)
            }
        }
        isNumberMatch(finalValue)

        if (tvOnetrue && tvTwotrue && tvThreetrue) {

            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.custom_dialog_layout)
            val yesBtn = dialog.findViewById(R.id.btn_yes) as Button
            val noBtn = dialog.findViewById(R.id.btn_no) as TextView
            yesBtn.setOnClickListener {
                setTvvaluesFalse(false, false, false)
                countTimer.cancel()
                setRandomNumbers()
                setTimer()
                binding.tvOne.setBackgroundDrawable(getDrawable(R.drawable.textview_suare_shape))
                binding.tvTwo.setBackgroundDrawable(getDrawable(R.drawable.textview_suare_shape))
                binding.tvThree.setBackgroundDrawable(getDrawable(R.drawable.textview_suare_shape))
                score += 5
                binding.tvScore.text = getString(R.string.score) + score.toString()
                dialog.dismiss()
            }
            noBtn.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()

        }

    }

    fun isNumberMatch(number: Int) {

        var tvOneValue: String = binding.tvOne.text.toString()
        var tvTwoValue: String = binding.tvTwo.text.toString()
        var tvThreeValue: String = binding.tvThree.text.toString()

        if (number == tvOneValue.toInt()) {
            binding.tvOne.setBackgroundResource(R.color.tv_bgc)
            tvOnetrue = true
        } else if (number == tvTwoValue.toInt()) {
            binding.tvTwo.setBackgroundResource(R.color.tv_bgc)
            tvTwotrue = true
        } else if (number == tvThreeValue.toInt()) {
            binding.tvThree.setBackgroundResource(R.color.tv_bgc)
            tvThreetrue = true
        }

    }

    fun setNumberToZero(num1: Int, num2: Int, num3: Int) {
        number1 = num1
        number2 = num2
        number3 = num3
    }

    fun setTvvaluesFalse(tvOne: Boolean, tvTwo: Boolean, tvThree: Boolean) {
        tvOnetrue = tvOne
        tvTwotrue = tvTwo
        tvThreetrue = tvThree
    }


}