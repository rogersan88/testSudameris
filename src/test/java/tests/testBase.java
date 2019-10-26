package tests;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import pages.Triangulos;
import utils.ThreadLocalDriver;

public class testBase {

	public DesiredCapabilities capabilities;
	public static String language = "es";
	protected static AndroidDriver<MobileElement> driver;
	public static String fecha = "";
	public static int time = 60;
	protected AppiumDriverLocalService service;
	protected Triangulos triangulos;

	@Parameters({ "deviceName", "version", "port" })
	@BeforeTest()
	public void beforeTest(String deviceName, String platformVersion, String port) throws Exception {

		// CON LA FECHA , SE GENERA PARTE DEL NOMBRE DEL REPORTE DE LA SUITE
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String requiredDate = df.format(new Date()).toString();
		// *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"
		fecha = requiredDate.replace(":", "-").replace(" ", "_");

		// CAPABILITIES

		capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("deviceName", deviceName);
		capabilities.setCapability("autoGrantPermissions", true);
		capabilities.setCapability("appPackage", "com.eliasnogueira.trianguloapp");
		capabilities.setCapability("appActivity", "com.eliasnogueira.trianguloapp.MainActivity");
		capabilities.setCapability("noReset", false);
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("language", language);
		capabilities.setCapability("locale", "CO");
		capabilities.setCapability("platformVersion", platformVersion);
		capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, port);

		// SET ANDROIDHOME

		HashMap<String, String> environment = new HashMap();
		environment.put("ANDROID_HOME", "/Users/macbookpro/android-sdks");

		// CONEXION APPIUM

		service = AppiumDriverLocalService
				.buildService(new AppiumServiceBuilder().withArgument(GeneralServerFlag.LOG_LEVEL, "error")
						.usingAnyFreePort().usingAnyFreePort().withEnvironment(environment));

		service.start();

		ThreadLocalDriver.setDriver(new AndroidDriver<MobileElement>(service.getUrl(), capabilities));

		// INICIA LAS VARIABLES DE CADA UNA DE LAS CLASES

		triangulos = new Triangulos(ThreadLocalDriver.getDriver());

	}

	@AfterSuite()
	public void finish() throws Exception {

		service.stop();

	}

}
