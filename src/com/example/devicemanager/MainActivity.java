package com.example.devicemanager;

import java.util.logging.Logger;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
	Button active;
	Button delete;
	Button lock;
	Button mylock;
	private DevicePolicyManager policyManager;  
    private ComponentName componentName;  
      
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
          
        //��ȡ�豸�������  
        policyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);  
          
        //AdminReceiver �̳��� DeviceAdminReceiver  
        componentName = new ComponentName(this, AdminReceiver.class);  
          
        init();  
    }  
    private void init() {  
         active = (Button)findViewById(R.id.active);  
         delete = (Button)findViewById(R.id.delete);  
         lock = (Button)findViewById(R.id.lock);  
         mylock=(Button) findViewById(R.id.mylock);
         mylock.setOnClickListener(this);
        active.setOnClickListener(this);  
        delete.setOnClickListener(this);  
        lock.setOnClickListener(this);  
    }  
    @Override  
    public void onClick(View v) {  
        switch(v.getId()) {  
            case R.id.active:  
                activeManage();  
                break;  
            case R.id.delete:  
                unActiveManage();  
                break;  
            case R.id.lock:  
                systemLock();  
                break;  
            case R.id.mylock:  
                start(); 
                finish();
                break;  
            default:  
                break;  
        }  
    }  
      
    private void start() {
		// TODO Auto-generated method stub
		Intent intent =new Intent(getApplicationContext(), Pop.class);
		startService(intent);
	}
	/** 
     * �����豸����Ȩ�� 
     * �ɹ�ִ�м���ʱ��DeviceAdminReceiver�е� onEnabled ����Ӧ 
     */  
    private void activeManage() {  
        // �����豸����(��ʽIntent) - ��AndroidManifest.xml���趨��Ӧ������  
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);  
          
        //Ȩ���б�  
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);  
          
        //����(additional explanation)  
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "------ �뼤���豸������Ӱ���豸������ʹ�� ------");  
          
        startActivityForResult(intent, 0);  
    }  
      
    /** 
     * �����豸����Ȩ�� 
     * �ɹ�ִ�н���ʱ��DeviceAdminReceiver�е� onDisabled ����Ӧ 
     */  
    private void unActiveManage() {  
        Log.e("unActiveManage","------ unActiveManage ------");  
        boolean active = policyManager.isAdminActive(componentName);  
        if (active) {  
            policyManager.removeActiveAdmin(componentName);  
        }  
    }  
      
    /** 
     * ����ϵͳ�� 
     */  
    private void  systemLock() {  
        Log.e("systemLock","------ Lock Screen ------");  
        boolean active = policyManager.isAdminActive(componentName);  
        if (active) {  
            policyManager.lockNow();  
        }  
    }  
}
