package com.exclr8.n1corporate.Helpers

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.exclr8.n1corporate.Activities.OfflineActivity
import com.exclr8.n1corporate.Activities.OutdateActivity

class State{
    fun showOutdated(context: Context, activity: Activity) {
        val offline = Intent(context, OutdateActivity::class.java)
        offline.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(offline)
        activity.finish()
    }

    fun showOffline(context: Context, activity: Activity) {
        val offline = Intent(context, OfflineActivity::class.java)
        offline.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(offline)
        activity.finish()
    }

    fun showErrorToastOnUIThread(context: Context?, activity: Activity) {
        activity.runOnUiThread { Toast.makeText(context, "Please try again later!", Toast.LENGTH_SHORT).show() }
    }

    fun showCustomErrorToastOnUIThread(context: Context?, activity: Activity, message: String?) {
        activity.runOnUiThread { Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }
    }
}