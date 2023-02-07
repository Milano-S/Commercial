package com.exclr8.n1corporate.Helpers

import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

open class Helper{

    companion object{
        var homeFragmentScrollPosition: Int = 0
        var productListScrollPosition: Int = 0
    }

    fun hideKeyboard(activity: Activity) {
        val view = activity.findViewById<View>(android.R.id.content)
        if (view != null) {
            val imm = activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    fun priceFormatter(price: Double): String {
        val df = DecimalFormat("0.00")
        val dfs = DecimalFormatSymbols()
        dfs.decimalSeparator = '.'
        df.decimalFormatSymbols = dfs
        return "R" + df.format(price)
    }

    fun strikeTextView(textView: TextView, price: Double){
        textView.text = priceFormatter(price)
        textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    fun getScreenSize(context: Context, callback: (Width: Int, Height: Int) -> Unit){
        val displayMetrics = context.resources.displayMetrics
        callback(displayMetrics.widthPixels, displayMetrics.heightPixels)

    }
    fun calculateNoOfColumns(context: Context, columnWidthDp: Float): Int {
        val displayMetrics = context.resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        val noOfColumns = (screenWidthDp / columnWidthDp).toInt()
        return if (noOfColumns == 0) {
            1
        } else noOfColumns
    }

    fun isTablet(context: Context): Boolean {
        return context.resources.configuration.smallestScreenWidthDp >= 600
    }

    fun animateImageLoading(image: ImageView, context: Context) {
        val width = context.resources.configuration.screenWidthDp
        val truck_moving = TranslateAnimation(
            Animation.ABSOLUTE, (-800).toFloat(),
            Animation.ABSOLUTE, (width + 800).toFloat(),
            Animation.ABSOLUTE, (0).toFloat(),
            Animation.ABSOLUTE, (0).toFloat()
        )
        truck_moving.duration = 4000
        truck_moving.fillAfter = true
        truck_moving.startOffset = 0
        truck_moving.repeatCount = Animation.INFINITE
        truck_moving.repeatMode = Animation.RESTART
        image.startAnimation(truck_moving)
    }


    fun animateImage(view: View, direction: Direction, context: Context){
        val displayMetrics = context.resources.displayMetrics
        var animationMovement: TranslateAnimation? = null

        when (direction){
            Direction.UP -> {
                animationMovement = TranslateAnimation(
                    Animation.ABSOLUTE, (0).toFloat(),	//fromX
                    Animation.ABSOLUTE, (0).toFloat(),	//toX
                    Animation.ABSOLUTE, (displayMetrics.heightPixels).toFloat(),    //toY
                    Animation.ABSOLUTE, (0).toFloat()    //fromY
                )
            }
            Direction.DOWN -> {
                animationMovement = TranslateAnimation(
                    Animation.ABSOLUTE, (0).toFloat(),	//fromX
                    Animation.ABSOLUTE, (0).toFloat(),	//toX
                    Animation.ABSOLUTE, (0).toFloat(),    //toY
                    Animation.ABSOLUTE, (displayMetrics.heightPixels).toFloat()    //fromY
                )
            }
            Direction.LEFT -> {
                animationMovement = TranslateAnimation(
                    Animation.ABSOLUTE, (displayMetrics.widthPixels).toFloat(),	//fromX
                    Animation.ABSOLUTE, (0).toFloat(),	//toX
                    Animation.ABSOLUTE, (0).toFloat(),	//fromY
                    Animation.ABSOLUTE, (0).toFloat()	//toY
                )
            }
            Direction.RIGHT -> {
                animationMovement = TranslateAnimation(
                    Animation.ABSOLUTE, (0).toFloat(),	//fromX
                    Animation.ABSOLUTE, (displayMetrics.widthPixels).toFloat(),	//toX
                    Animation.ABSOLUTE, (0).toFloat(),	//fromY
                    Animation.ABSOLUTE, (0).toFloat()	//toY
                )}
        }

        animationMovement.duration = 400
        animationMovement.fillBefore = true
        animationMovement.startOffset = 0
        view.startAnimation(animationMovement)
    }
}


enum class Direction{
    UP,DOWN,LEFT,RIGHT
}