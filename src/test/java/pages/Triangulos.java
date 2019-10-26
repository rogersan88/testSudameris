package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import tests.testBase;
import utils.ThreadLocalDriver;

public class Triangulos {

	@AndroidFindBy(id = "com.eliasnogueira.trianguloapp:id/txtLado1")
	private MobileElement lado1;

	@AndroidFindBy(id = "com.eliasnogueira.trianguloapp:id/txtLado2")
	private MobileElement lado2;

	@AndroidFindBy(id = "com.eliasnogueira.trianguloapp:id/txtLado3")
	private MobileElement lado3;

	@AndroidFindBy(id = "com.eliasnogueira.trianguloapp:id/btnCalcular")
	private MobileElement buttonCalcular;

	public WebDriverWait wait;

	protected AndroidDriver<MobileElement> driver;

	public Triangulos(AndroidDriver<MobileElement> driver) {

		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(1)), this);

	}

	public void addLado1(String dato) throws Exception {

		// DECLARA GLOBAL EL WAIT

		wait = new WebDriverWait(driver, testBase.time);

		wait.until(ExpectedConditions.elementToBeClickable(this.lado1)).click();

		// LIMPIAR EL CAMPO

		this.lado1.clear();

		// INGRESA EL DATO

		this.lado1.sendKeys(dato);

		System.out.println("TRIANGULOS: SE INGRESO EL LADO 1 ");
	}

	public void addLado2(String dato) throws Exception {

		// DECLARA GLOBAL EL WAIT

		wait = new WebDriverWait(driver, testBase.time);

		wait.until(ExpectedConditions.elementToBeClickable(this.lado2)).click();

		// LIMPIAR EL CAMPO

		this.lado2.clear();

		// INGRESA EL DATO

		this.lado2.sendKeys(dato);

		System.out.println("TRIANGULOS: SE INGRESO EL LADO 2 ");
	}

	public void addLado3(String dato) throws Exception {

		// DECLARA GLOBAL EL WAIT

		wait = new WebDriverWait(driver, testBase.time);

		wait.until(ExpectedConditions.elementToBeClickable(this.lado3)).click();

		// LIMPIAR EL CAMPO

		this.lado3.clear();

		// INGRESA EL DATO

		this.lado3.sendKeys(dato);

		System.out.println("TRIANGULOS: SE INGRESO EL LADO 3 ");
	}

	public void clickButtonCalcular() throws Exception {

		// DECLARA GLOBAL EL WAIT

		wait = new WebDriverWait(driver, testBase.time);

		wait.until(ExpectedConditions.elementToBeClickable(this.buttonCalcular)).click();

		System.out.println("TRIANGULOS: CLICK EN EL BOTON CALCULAR ");
	}

	public void validacionTriangulo(String lado1, String lado2, String lado3) throws Exception {

		int a = Integer.parseInt(lado1);
		int b = Integer.parseInt(lado2);
		int c = Integer.parseInt(lado3);
		String resultado = "";

		if (a == b && a == c && b == c) {

			resultado = "O triângulo é Equilátero";

		} else {

			if ((a == b) || (a == c) || (b == c)) {

				resultado = "O triângulo é Isósceles";

			} else {
				resultado = "O triângulo é Escaleno";

			}

		}

		MobileElement result = (MobileElement) driver.findElementById("com.eliasnogueira.trianguloapp:id/txtResultado");
		Assert.assertEquals(result.getText(), resultado, "TRIANGULO:EL RESULTADO DEL TRIANGULO NO ES EL CORRECTO ");

		System.out.println("TRIANGULOS: EL TRIANGULO ES " + resultado.toUpperCase());
	}
}
