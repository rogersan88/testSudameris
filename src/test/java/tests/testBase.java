package tests;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.tools.ant.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import io.appium.java_client.android.AndroidDriver;

public class testBase {

	public static DesiredCapabilities capabilities;
	public static String language = "es";
	public static AndroidDriver<?> driver;
	public static String fecha = "";
	public static WebDriverWait waiting;
	public static int time = 60;

	@AfterSuite()
	public void finish(ITestContext context) throws Exception {

		// CIERRA EL DRIVER

		driver.quit();

	}

	@AfterMethod()
	public void finishMethod(Method method, ITestResult iTestResult, ITestContext context) throws Exception {

		// VALIDA QUE EL RESULTADO DEL TEST SOLO INGRESA SI ES DIFERENTE A EXITOSO

		// CIERRA LA APP

		driver.closeApp();

	}

	@BeforeMethod()
	public void AbrirApp(Method method, ITestContext context) throws Exception {

		// INICIA LAS VARIABLES DE CADA UNA DE LAS CLASES

	}

}
