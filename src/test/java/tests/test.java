package tests;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import utils.ExcelDataProviderAndroid;
import utils.ExtentTestManagerAndroid;

public class test extends testBase {
	
	
	@Test(dataProvider = "dataTestTriangulo", dataProviderClass = ExcelDataProviderAndroid.class)
	public void testTriangulos(String lado1, String lado2, String lado3, Method test)
			throws Exception {
		
		
		ExtentTestManagerAndroid.startTest(test.getName(), "Test to check Triangulos");

		triangulos.addLado1(lado1);
		triangulos.addLado2(lado2);
		triangulos.addLado3(lado3);
		triangulos.clickButtonCalcular();
		triangulos.validacionTriangulo(lado1, lado2, lado3);
		
	}

}
