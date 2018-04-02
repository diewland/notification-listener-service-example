package com.github.chagall.notificationlistenerexample;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * MIT License
 *
 *  Copyright (c) 2016 Fábio Alves Martins Pereira (Chagall)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class NotificationListenerExampleService extends NotificationListenerService {

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onNotificationPosted(StatusBarNotification sbn){

        Date date = new Date(sbn.getPostTime());
        DateFormat formatter = new SimpleDateFormat("YY/MM/dd HH:mm:ss");
        String dt = formatter.format(date);

        String pkg = sbn.getPackageName();
        String title = sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TITLE).toString();
        String text = sbn.getNotification().extras.getCharSequence(Notification.EXTRA_TEXT).toString();
        Log.d("DIEWLAND", "package --> " + pkg);
        Log.d("DIEWLAND", "datetime --> " + dt);
        Log.d("DIEWLAND", "title --> " + title);
        Log.d("DIEWLAND", "text --> " + text);

        Intent intent = new  Intent("com.github.chagall.notificationlistenerexample");
        intent.putExtra("pkg", pkg);
        intent.putExtra("title", title);
        intent.putExtra("text", text);
        intent.putExtra("dt", dt);
        sendBroadcast(intent);
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn){}
}
