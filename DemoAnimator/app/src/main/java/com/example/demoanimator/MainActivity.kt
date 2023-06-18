package com.example.demoanimator

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Property
import android.view.View
import android.view.View.OnClickListener
import android.view.animation.AccelerateInterpolator
import android.view.animation.AnimationUtils
import android.view.animation.PathInterpolator
import com.example.demoanimator.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity(), OnClickListener {
    lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        
        mBinding.btnFadeIn.setOnClickListener(this)
        mBinding.btnFadeIn.setOnClickListener(this)
        mBinding.btnFadeIn.setOnClickListener(this)
        mBinding.btnFadeIn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var anim = 0
        when(v!!.id) {
            R.id.btn_fade_in -> anim = R.anim.fade_in
            // ...
            else -> Log.d("PhucDVb", ": ")
        }

        val animation = AnimationUtils.loadAnimation(this, anim)
        mBinding.imgDemo.startAnimation(animation)
    }
}