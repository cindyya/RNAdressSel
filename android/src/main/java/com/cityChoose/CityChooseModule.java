package com.cityChoose;

import android.app.Activity;
import android.content.Intent;

import com.cityChoose.city.CityActivity;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;


public class CityChooseModule extends ReactContextBaseJavaModule implements ActivityEventListener {

  static Callback mcallback;
  public CityChooseModule(ReactApplicationContext reactContext) {
    super(reactContext);
    reactContext.addActivityEventListener(this);
  }

  @Override
  public String getName() {
    return "CityChooseManager";
  }

  @ReactMethod
  public void getCity(String currentCity, Callback callback){
    mcallback=callback;
    Intent intent=new Intent(getReactApplicationContext(), CityActivity.class);
    intent.putExtra("currentCity",currentCity);
    getCurrentActivity().startActivityForResult(intent,1001);
  }

@Override
public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
    if(requestCode==1001){
      if(mcallback!=null){
        if(data!=null){
          String city = data.getStringExtra("city");
          mcallback.invoke(city);
        }
        mcallback=null;
      }
    }
}

  @Override
  public void onNewIntent(Intent intent) {

  }
}
