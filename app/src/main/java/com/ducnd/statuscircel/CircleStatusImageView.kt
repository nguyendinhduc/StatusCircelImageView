package com.ducnd.statuscircel

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.support.v4.content.ContextCompat
import android.util.AttributeSet

import de.hdodenhof.circleimageview.CircleImageView

class CircleStatusImageView : CircleImageView {
    private var ratioActive = 0
    private var ratioInActive = 0
    private var sizeActiveBorder = 0
    private var sizeInActiveBorder = 0
    private var colorActive = 0
    private var colorInActive = 0
    private var colorActiveBorder = 0
    private var colorInActiveBorder = 0
    private var rotate = 0
    var isActive = true

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        inits(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        inits(attrs, defStyle)
    }

    private fun inits(attrs: AttributeSet?, defStyle: Int) {
        if (attrs == null) {
            ratioActive = context.resources.getDimensionPixelOffset(R.dimen.radioActiveDefault)
            ratioInActive = context.resources.getDimensionPixelOffset(R.dimen.radioInActiveDefault)
            sizeActiveBorder = context.resources.getDimensionPixelOffset(R.dimen.sizeActiveBorderDefault)
            sizeInActiveBorder = context.resources.getDimensionPixelOffset(R.dimen.sizeInActiveBorderDefault)
            colorActive = ContextCompat.getColor(context, R.color.colorActiveDefault)
            colorActiveBorder = ContextCompat.getColor(context, R.color.colorActiveBorderDefault)
            colorInActive = ContextCompat.getColor(context, R.color.colorInActiveDefault)
            colorInActiveBorder = ContextCompat.getColor(context, R.color.colorInActiveBorderDefault)
            rotate = context.resources.getInteger(R.integer.rotateDefault)
            isActive = true
        } else {
            val obtain = context.obtainStyledAttributes(attrs, R.styleable.CircleStatusImageView, defStyle, 0)
            ratioActive = obtain.getDimensionPixelOffset(R.styleable.CircleStatusImageView_radioActive,
                    context.resources.getDimensionPixelOffset(R.dimen.radioActiveDefault))
            ratioInActive = obtain.getDimensionPixelOffset(R.styleable.CircleStatusImageView_radioInActive,
                    context.resources.getDimensionPixelOffset(R.dimen.radioInActiveDefault))
            sizeActiveBorder = obtain.getDimensionPixelOffset(R.styleable.CircleStatusImageView_sizeActiveBorder,
                    context.resources.getDimensionPixelOffset(R.dimen.sizeActiveBorderDefault))
            sizeActiveBorder = obtain.getDimensionPixelOffset(R.styleable.CircleStatusImageView_sizeInActiveBorder,
                    context.resources.getDimensionPixelOffset(R.dimen.sizeInActiveBorderDefault))
            colorActive = obtain.getColor(R.styleable.CircleStatusImageView_colorActive,
                    ContextCompat.getColor(context, R.color.colorActiveDefault))
            colorActiveBorder = obtain.getColor(R.styleable.CircleStatusImageView_colorActiveBorder,
                    ContextCompat.getColor(context, R.color.colorActiveBorderDefault))
            colorInActive = obtain.getColor(R.styleable.CircleStatusImageView_colorInActive,
                    ContextCompat.getColor(context, R.color.colorInActiveDefault))
            colorInActiveBorder = obtain.getColor(R.styleable.CircleStatusImageView_colorInActiveBorder,
                    ContextCompat.getColor(context, R.color.colorInActiveBorderDefault))
            rotate = obtain.getInt(R.styleable.CircleStatusImageView_rotate,
                    context.resources.getInteger(R.integer.rotateDefault))
            isActive = obtain.getBoolean(R.styleable.CircleStatusImageView_isActive, true)
            obtain.recycle()

        }

    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val width = width
        val height = height
        canvas?.translate(width.toFloat() / 2, height.toFloat() / 2)
        canvas?.rotate(rotate.toFloat(), 0.0f, 0.0f)
        val paint = Paint()
        paint.isAntiAlias = true
        if (isActive) {
            paint.color = colorActive
            canvas?.drawCircle(width.toFloat() / 2, 0.0f, ratioActive.toFloat(), paint)
            paint.strokeWidth = sizeActiveBorder.toFloat()
            paint.style = Paint.Style.STROKE
            paint.color = colorActiveBorder
            canvas?.drawCircle(width.toFloat() / 2, 0.0f, ratioActive.toFloat(), paint)
        }else {
            paint.color = colorInActive
            canvas?.drawCircle(width.toFloat() / 2, 0.0f, ratioInActive.toFloat(), paint)
            paint.strokeWidth = sizeInActiveBorder.toFloat()
            paint.style = Paint.Style.STROKE
            paint.color = colorInActiveBorder
            canvas?.drawCircle(width.toFloat() / 2, 0.0f, ratioInActive.toFloat(), paint)
        }

    }
}
