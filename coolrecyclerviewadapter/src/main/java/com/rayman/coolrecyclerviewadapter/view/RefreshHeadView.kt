package com.rayman.coolrecyclerviewadapter.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import android.widget.TextView
import com.rayman.coolrecyclerviewadapter.R

/**
 * @author 吕少锐 (lvshaorui@parkingwang.com)
 * @version 2019/1/21
 */
class RefreshHeadView : LinearLayout {
    var maxHeight = 100f
    lateinit var contentLayout: LinearLayout
    private lateinit var mTv: TextView
    private lateinit var mRotateUpAnim: RotateAnimation
    private lateinit var mRotateDownAnim: RotateAnimation

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(context, attributeSet, defStyle) {
        init(context)
    }

    private fun init(context: Context) {
        val layoutParams =
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(0, 0, 0, 0)
        setLayoutParams(layoutParams)
        setPadding(0, 0, 0, 0)
        contentLayout = LayoutInflater.from(context).inflate(R.layout.view_head, null) as LinearLayout
        addView(contentLayout, LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
        mTv = findViewById(R.id.tv_head)
        contentLayout.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                maxHeight = contentLayout.height.toFloat()
                contentLayout.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        })

        mRotateUpAnim = RotateAnimation(0f, -180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mRotateUpAnim.duration = 200
        mRotateUpAnim.fillAfter = true
        mRotateDownAnim = RotateAnimation(-180f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mRotateDownAnim.duration = 200
        mRotateDownAnim.fillAfter = true

        measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

}