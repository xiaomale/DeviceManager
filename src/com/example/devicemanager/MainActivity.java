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
          
        //获取设备管理服务  
        policyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);  
          
        //AdminReceiver 继承自 DeviceAdminReceiver  
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
     * 激活设备管理权限 
     * 成功执行激活时，DeviceAdminReceiver中的 onEnabled 会响应 
     */  
    private void activeManage() {  
        // 启动设备管理(隐式Intent) - 在AndroidManifest.xml中设定相应过滤器  
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);  
          
        //权限列表  
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);  
          
        //描述(additional explanation)  
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "------ 请激活设备，否则将影响设备的正常使用 ------");  
          
        startActivityForResult(intent, 0);  
    }  
      
    /** 
     * 禁用设备管理权限 
     * 成功执行禁用时，DeviceAdminReceiver中的 onDisabled 会响应 
     */  
    private void unActiveManage() {  
        Log.e("unActiveManage","------ unActiveManage ------");  
        boolean active = policyManager.isAdminActive(componentName);  
        if (active) {  
            policyManager.removeActiveAdmin(componentName);  
        }  
    }  
      
    /** 
     * 调出系统锁 
     */  
    private void  systemLock() {  
        Log.e("systemLock","------ Lock Screen ------");  
        boolean active = policyManager.isAdminActive(componentName);  
        if (active) {  
            policyManager.lockNow();  
        }  
    }  
}
