package com.chenlong.testbc2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class SMSReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		System.out.println("receiver message");
		//接受Intent对象中的数据
		Bundle bundle=intent.getExtras();
		//在Bundle对象当中有一个属性名为pdus,这个属性的值是一个Object数组
		Object[] myPdus=(Object[])bundle.get("pdus");
		//创建一个SmsMessage类型的数组
		SmsMessage[] messages=new SmsMessage[myPdus.length]; 
		System.out.println(messages.length);
		for(int i=0;i<myPdus.length;i++){
			//使用Object数组当中的对象创建SmsMessage对象
			messages[i]=SmsMessage.createFromPdu((byte[])myPdus[i]);
			//调用SmsMessage对象的getDisplayMessageBody方法就可以得到消息中的内容
			System.out.println(messages[i].getDisplayMessageBody());
		}
	}

}
