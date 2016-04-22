package com.example.devicemanager;

import android.app.Service;  
import android.content.Intent;  
import android.graphics.PixelFormat;  
import android.os.Handler;  
import android.os.IBinder;  
import android.util.Log;  
import android.view.Gravity;  
import android.view.LayoutInflater;  
import android.view.MotionEvent;  
import android.view.View;  
import android.view.WindowManager;  
import android.view.View.OnClickListener;  
import android.view.View.OnTouchListener;  
import android.view.WindowManager.LayoutParams;  
import android.widget.Button;  
import android.widget.LinearLayout;  
import android.widget.Toast;
public class Pop extends Service {
	// ���帡�����ڲ���
	LinearLayout mFloatLayout;
	WindowManager.LayoutParams wmParams;
	// ���������������ò��ֲ����Ķ���
	WindowManager mWindowManager;
	private static final String TAG = "FxService";

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i(TAG, "oncreat");
		createFloatView();
	}

	private void createFloatView() {
		wmParams = new WindowManager.LayoutParams();
		// ��ȡ����WindowManagerImpl.CompatModeWrapper
		mWindowManager = (WindowManager) getApplication().getSystemService(
				getApplication().WINDOW_SERVICE);
		Log.i(TAG, "mWindowManager--->" + mWindowManager);
		// ����window type
		//wmParams.type = LayoutParams.TYPE_PHONE;
		// ����ͼƬ��ʽ��Ч��Ϊ����͸��
		wmParams.format = PixelFormat.RGBA_8888;
		// ���ø������ڲ��ɾ۽���ʵ�ֲ���������������������ɼ����ڵĲ�����
		wmParams.flags = LayoutParams.FLAG_NOT_FOCUSABLE;
		wmParams.flags = LayoutParams.FLAG_FULLSCREEN;
		//wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
		wmParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;


		// ������������ʾ��ͣ��λ��Ϊ����ö�
		wmParams.gravity = Gravity.LEFT | Gravity.TOP;
		// ����Ļ���Ͻ�Ϊԭ�㣬����x��y��ʼֵ�������gravity
		wmParams.x = 0;
		wmParams.y = 0;

		// �����������ڳ�������
		wmParams.width = WindowManager.LayoutParams.FILL_PARENT;
		wmParams.height = WindowManager.LayoutParams.FILL_PARENT;

		/*
		 * // �����������ڳ������� wmParams.width = 200; wmParams.height = 80;
		 */

		LayoutInflater inflater = LayoutInflater.from(getApplication());
		// ��ȡ����������ͼ���ڲ���
		mFloatLayout = (LinearLayout) inflater.inflate(R.layout.float_layout,
				null);
		// ���mFloatLayout
		mWindowManager.addView(mFloatLayout, wmParams);
		// �������ڰ�ť
//
//		mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
//				View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
//				.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
//	

	
	}


	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
