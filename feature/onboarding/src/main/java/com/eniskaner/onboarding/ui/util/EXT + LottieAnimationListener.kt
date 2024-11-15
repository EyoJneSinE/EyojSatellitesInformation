package com.eniskaner.onboarding.ui.util

import android.animation.Animator
import android.util.Log
import com.airbnb.lottie.LottieAnimationView

fun LottieAnimationView.addAnimatorEndListener(onEnd: () -> Unit) {

    addAnimatorListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator) {}
        override fun onAnimationEnd(animation: Animator) {
            Log.d("AnimationEnd", "Animation has ended")
            onEnd()
        }
        override fun onAnimationCancel(animation: Animator) {}
        override fun onAnimationRepeat(animation: Animator) {}
    })
}