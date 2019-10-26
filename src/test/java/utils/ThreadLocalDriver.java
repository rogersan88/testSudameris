package utils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ThreadLocalDriver {
	
	public static  ThreadLocal<AndroidDriver<MobileElement>> tlDriver = new ThreadLocal<>();
	   
	public synchronized static AndroidDriver<MobileElement> getDriver() {
        return tlDriver.get();
    }
 
    public synchronized static void setDriver(AndroidDriver<MobileElement> drive) {
    	tlDriver.set(drive);
    }


}
