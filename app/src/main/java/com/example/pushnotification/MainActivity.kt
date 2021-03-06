package com.example.pushnotification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 4typeHEadsON PushNotification
        //1 type is edited to 4 1  code is writtrenin notebook
        //only to work for devices greater than Oreo
        val nm=getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel:NotificationChannel=(NotificationChannel("first",
                "dafault",
                NotificationManager.IMPORTANCE_HIGH))

            channel.apply {
                enableLights(true)
                enableVibration(true)
            }
            nm.createNotificationChannel(channel)
        }
       button.setOnClickListener{
           val builder=
               if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                   Notification.Builder(this,"first")
       }
           else{
               Notification.Builder(this)
                   .setPriority(Notification.PRIORITY_MAX)
                   .setDefaults(Notification.DEFAULT_VIBRATE or Notification.DEFAULT_LIGHTS)
               }

            //notificationCompat is using for setting notification
           val simpleNotification= NotificationCompat.Builder(this,"first")
               .setContentTitle("Simple Title")
               .setContentText("This is the simple description of the notification")
               .setSmallIcon(R.drawable.ic_launcher_foreground)
               .setPriority(NotificationCompat.PRIORITY_DEFAULT)
           .build()
           nm.notify(1,simpleNotification)
        }
        //clickableNotification
        button2.setOnClickListener{

            val i= Intent()
            i.action=Intent.ACTION_VIEW
            i.data= Uri.parse("https://www.google.com")
            val pi= PendingIntent.getActivity(this,123,i,PendingIntent.FLAG_UPDATE_CURRENT)
            val clickableNotification=NotificationCompat.Builder(this,"first")
                .setContentTitle("Simple Title")
                .setContentText("This is the simple description of the notification")
                .setAutoCancel(true)
                .setContentIntent(pi)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(2,clickableNotification)
        }
        //Notification with a Button
        button3.setOnClickListener{

            val i= Intent()
            i.action=Intent.ACTION_VIEW
            i.data= Uri.parse("https://www.google.com")
            val pi= PendingIntent.getActivity(this,123,i,PendingIntent.FLAG_UPDATE_CURRENT)
            val clickableNotification=NotificationCompat.Builder(this,"first")
                .setContentTitle("Simple Title")
                .setContentText("This is the simple description of the notification")
                .setAutoCancel(true)
                //instead of setContentIntent we will use .addAction
              //  .setContentIntent(pi)
                .addAction(R.drawable.ic_launcher_foreground,"Click me",pi)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(3,clickableNotification)
        }

    }}
