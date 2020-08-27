package com.luisalvarez.openlibrary.views

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.luisalvarez.openlibrary.R

class LoadingScaffoldingView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = -1
) : CardView(context, attrs, defStyleAttr) {

    private var aggregatedIsVisible = true
    private val anim: ObjectAnimator

    init {
        var startColor =
            ContextCompat.getColor(context, R.color.loading_start_gray)
        var endColor = ContextCompat.getColor(context, R.color.loading_end_gray)

        if (attrs != null) {
            val typedArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.LoadingScaffoldingView, 0, 0
            )

            val attrStartColor =
                typedArray.getColor(R.styleable.LoadingScaffoldingView_animStartColor, 0)
            val attrEndColor =
                typedArray.getColor(R.styleable.LoadingScaffoldingView_animEndColor, 0)

            if (attrStartColor != 0) {
                startColor = attrStartColor
            }

            if (attrEndColor != 0) {
                endColor = attrEndColor
            }

            typedArray.recycle()
        }

        cardElevation = 0f
        setCardBackgroundColor(startColor)
        anim = ObjectAnimator.ofInt(
            this,
            "cardBackgroundColor",
            startColor,
            endColor
        ).apply {
            duration = 500
            setEvaluator(ArgbEvaluator())
            repeatCount = ValueAnimator.INFINITE
            repeatMode = ValueAnimator.REVERSE
        }
    }

    override fun onVisibilityAggregated(isVisible: Boolean) {
        super.onVisibilityAggregated(isVisible)
        if (aggregatedIsVisible != isVisible) {
            aggregatedIsVisible = isVisible

            // Stop animation if self or parent visible changes
            // Can cause issues in espresso for infinite repeating animations
            if (isVisible) {
                anim.start()
            } else {
                anim.cancel()
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (visibility == View.VISIBLE) {
            anim.start()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        anim.cancel()
    }
}