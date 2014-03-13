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
		//����Intent�����е�����
		Bundle bundle=intent.getExtras();
		//��Bundle��������һ��������Ϊpdus,������Ե�ֵ��һ��Object����
		Object[] myPdus=(Object[])bundle.get("pdus");
		//����һ��SmsMessage���͵�����
		SmsMessage[] messages=new SmsMessage[myPdus.length]; 
		System.out.println(messages.length);
		for(int i=0;i<myPdus.length;i++){
			//ʹ��Object���鵱�еĶ��󴴽�SmsMessage����
			messages[i]=SmsMessage.createFromPdu((byte[])myPdus[i]);
			//����SmsMessage�����getDisplayMessageBody�����Ϳ��Եõ���Ϣ�е�����
			System.out.println(messages[i].getDisplayMessageBody());
		}
	}

}
