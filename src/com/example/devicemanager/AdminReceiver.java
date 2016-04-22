package com.example.devicemanager;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.sax.StartElementListener;
import android.util.Log;
import android.widget.Toast;

public class AdminReceiver extends DeviceAdminReceiver {
	@Override
	public DevicePolicyManager getManager(Context context) {
		Log.e("AdminReceiver", "------" + "getManager" + "------");
		return super.getManager(context);
	}

	@Override
	public ComponentName getWho(Context context) {
		Log.e("AdminReceiver", "------" + "getWho" + "------");

		return super.getWho(context);
	}

	/**
	 * ����
	 */
	@Override
	public void onDisabled(Context context, Intent intent) {
		Log.e("AdminReceiver", "------" + "onDisabled" + "------");

		Toast.makeText(context, "�����豸����", Toast.LENGTH_SHORT).show();

		super.onDisabled(context, intent);
	}

	@Override
	public CharSequence onDisableRequested(Context context, Intent intent) {
		Log.e("AdminReceiver", "------" + "onDisableRequested" + "------");
		Intent in = context.getPackageManager().getLaunchIntentForPackage(
				"com.android.settings");
		in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(in);
		DevicePolicyManager policyManager = (DevicePolicyManager) context
				.getSystemService(Context.DEVICE_POLICY_SERVICE);
		policyManager.lockNow();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "ȡ���豸������ܻ����ϵͳ�豸��������������ʹ�ã��Ƿ�ȷ��Ҫȡ������";
	}

	/**
	 * ����
	 */
	@Override
	public void onEnabled(Context context, Intent intent) {
		Log.e("AdminReceiver", "------" + "onEnabled" + "------");

		Toast.makeText(context, "�����豸����", Toast.LENGTH_SHORT).show();

		super.onEnabled(context, intent);
	}

	@Override
	public void onPasswordChanged(Context context, Intent intent) {
		Log.e("AdminReceiver", "------" + "onPasswordChanged" + "------");

		super.onPasswordChanged(context, intent);
	}

	@Override
	public void onPasswordFailed(Context context, Intent intent) {
		Log.e("AdminReceiver", "------" + "onPasswordFailed" + "------");

		super.onPasswordFailed(context, intent);
	}

	@Override
	public void onPasswordSucceeded(Context context, Intent intent) {
		Log.e("AdminReceiver", "------" + "onPasswordSucceeded" + "------");

		super.onPasswordSucceeded(context, intent);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e("AdminReceiver", "------" + "onReceive" + "------");

		super.onReceive(context, intent);
	}

	@Override
	public IBinder peekService(Context myContext, Intent service) {
		Log.e("AdminReceiver", "------" + "peekService" + "------");

		return super.peekService(myContext, service);
	}
}
