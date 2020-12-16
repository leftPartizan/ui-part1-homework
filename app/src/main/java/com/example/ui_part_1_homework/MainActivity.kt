package com.example.ui_part_1_homework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.example.ui_part_1_homework.databinding.MainActivityBinding
import com.example.ui_part_1_homework.utill.OrdinatorBehavior


class MainActivity : AppCompatActivity() {


    companion object {
        private val CAPITAL = "CAPITAL"
        private val FELLING = "FELLING"
        private val IMAGE_ORD = "IMAGE_ORD"
    }

    private val ord : OrdinatorBehavior by lazy { OrdinatorBehavior() }

    private val binding : MainActivityBinding by lazy {
        val tempBnd = MainActivityBinding.inflate(layoutInflater)
        setContentView(tempBnd.root)
        tempBnd
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fields = binding.textContent
        if (savedInstanceState == null) {
            fields.capitalOrdinator.text = ord.getCapitalTextStart()
            fields.fellingOrdinator.text = ord.getFellingTextStart()
        }
        else
            if (savedInstanceState.getBoolean(IMAGE_ORD)) binding.image.ord2.visibility = View.VISIBLE

        fields.capitalOrdinator.text = savedInstanceState?.getString(CAPITAL) ?: ord.getCapitalTextStart()
        fields.fellingOrdinator.text = savedInstanceState?.getString(FELLING) ?: ord.getFellingTextStart()

        fields.button.setOnClickListener {
            val takingMoney = ord.getStateIntFromView(fields.capitalOrdinator.text.toString())
            val sum = takingMoney + fields.editText.text.toString().toInt()
            fields.capitalOrdinator.text = ord.getCapitalText(sum)

            val fellingText = ord.getFellingText(fields.editText.text.toString().toInt(),
                ord.getStateIntFromView(fields.fellingOrdinator.text.toString()))
            fields.fellingOrdinator.text = fellingText

            if (ord.getStateIntFromView(fellingText) == 0) {
                binding.image.ord2.visibility = View.VISIBLE

            }
            else
                binding.image.ord2.visibility = View.INVISIBLE
        }


    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean(IMAGE_ORD, binding.image.ord2.isVisible)
        outState.putString(CAPITAL, binding.textContent.capitalOrdinator.text.toString())
        outState.putString(FELLING, binding.textContent.fellingOrdinator.text.toString())
        super.onSaveInstanceState(outState)
    }

}