package com.ak.samplefirebasenotification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val channelId = "notification"
    private val channelName = "com.ak.samplefirebasenotification"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

    }



    @SuppressLint("RemoteViewLayout")
    private fun getRemoteView(title: String, message: String): RemoteViews {
        val remoteViews = RemoteViews("com.ak.samplefirebasenotification",R.layout.notification)
        remoteViews.setTextViewText(R.id.tv_title,title)
        remoteViews.setTextViewText(R.id.tv_sub_title,message)
        remoteViews.setImageViewResource(R.id.iv_notify_logo,R.drawable.notification)

        return remoteViews
    }

    private fun generateNotification(title: String, message: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        var builder: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, channelId).setAutoCancel(true)
                .setSmallIcon(R.drawable.notification)
                .setVibrate(longArrayOf(1000, 1000, 1000, 1000)).setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent)
        builder = builder.setContent(getRemoteView(title, message))

        val notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){

            val notificationChannel=NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        notificationManager.notify(0,builder.build())
    }

}