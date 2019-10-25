package utils;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManagerAndroid extends tests.testBase {

	private static ExtentReports extent;

	public synchronized static ExtentReports getReporter() {
		if (extent == null) {
			// Set HTML reporting file location
			String workingDir = System.getProperty("user.dir");
			extent = new ExtentReports(workingDir + "/Reportes/ExtentReportResultsAndroid_" + fecha + ".html", true);
		}
		return extent;
	}

}
