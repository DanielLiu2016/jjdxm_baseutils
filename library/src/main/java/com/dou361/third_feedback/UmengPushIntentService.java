package com.dou361.third_feedback;

import android.content.Context;
import android.content.Intent;

import com.jingwang.eluxue_online.activity.FeedBackActivity;
import com.umeng.fb.push.FeedbackPush;
import com.umeng.message.UmengBaseIntentService;


public class UmengPushIntentService extends UmengBaseIntentService{
	private static final String TAG = UmengPushIntentService.class.getName();

	@Override
	protected void onMessage(Context context, Intent intent) {
		super.onMessage(context, intent);
        FeedbackPush.getInstance(context).init(FeedBackActivity.class,true);
        if(FeedbackPush.getInstance(context).onFBMessage(intent)){
            //The push message is reply from developer.
            return;
        }

        //The push message is not reply from developer.
        /*************** other code ***************/

	}
}
