package amazon.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Log {

	public static Logger loggerText = Logger.getLogger("AmazonAssignment");
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest loggerExtent;

	public static ThreadLocal<ExtentReports> extentReporter = new ThreadLocal<ExtentReports>();
	public static ThreadLocal<ExtentTest> extentTester = new ThreadLocal<ExtentTest>();

	// Initialize logj.properties file under parent project path
	public static void initialize() {
		PropertyConfigurator.configure("log4j.properties");
	}

	// Initialize the Extent Report
	public static void initializeExtent() {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-" + timeStamp + ".html";

		System.out.println("extent path: " + System.getProperty("user.dir") + "/test-output/" + repName);

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + repName);
		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");
		htmlReporter.config().setDocumentTitle("Amazon Application test");
		htmlReporter.config().setReportName("Regression Testing Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Enviornment", "QA");
		extent.setSystemInfo("user", "Vineela");
		extentReporter.set(extent);
	}

	// Get the instance of Extent Parent HTML Report
	public static synchronized ExtentReports getExtentReport() {
		return extentReporter.get();
	}

	// Create Extent Test for Extent Reporting
	public static synchronized ExtentTest createExtentTest(String testName, String category) {
		loggerExtent = getExtentReport().createTest(testName);
		loggerExtent.assignCategory(category);
		extentTester.set(loggerExtent);
		return loggerExtent;
	}

	// Get the instance of Extent Test
	public static synchronized ExtentTest getExtentTest() {
		return extentTester.get();
	}

	// Writes test information from the started reporters to their output view
	// once test get completed. In our case, we are using ExtentHtmlReporter
	// so it going to create or append to an HTML Test Report.
	public static void flushExtent() {
		extent.flush();
	}
	
	// Logs Info messages to Logger and Extent Test Report
	public static void info(String message) {
		loggerText.info(message);
		getExtentTest().log(Status.PASS, MarkupHelper.createLabel("Passed Step: " + message, ExtentColor.GREEN));
	}

	// Logs Fatal messages to Logger and Extent Test Report
	public static void fatal(String message) {
		loggerText.fatal(message);
		getExtentTest().log(Status.FATAL, MarkupHelper.createLabel("Fatal Step: " + message, ExtentColor.BROWN));

	}

	// Logs Debug messages to Logger and Extent Test Report
	public static void debug(String message) {
		loggerText.debug(message);
		getExtentTest().log(Status.DEBUG, MarkupHelper.createLabel("Debug Step: " + message, ExtentColor.GREY));

	}

	// Logs Warn message to Logger and Extent Test Report
	public static void warn(String message) {
		loggerText.warn(message);
		getExtentTest().log(Status.WARNING, MarkupHelper.createLabel("Warning Step: " + message, ExtentColor.AMBER));

	}

	// Logs Fail message to Logger and Extent Test Report
	public static void fail(String message) {
		loggerText.warn("Failed Step: " + message);
		getExtentTest().log(Status.FAIL, MarkupHelper.createLabel("Failed Step: " + message, ExtentColor.RED));

	}

	// Logs Error message to Logger and Extent Test Report
	public static void error(String message) {
		loggerText.fatal("Recorded error message [" + message + "]");
		Assert.fail(message);
	}

	// Logs Error message & Exception message to Logger and Extent Test Report
	public static void error(String message, boolean printException) {
		if (printException == true) {
			Throwable t = new Throwable();
			Log.info("*********** Generated Stack Trace ************");
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			Log.fatal(sw.toString());
			t.printStackTrace();
		} else {
			Log.error(message);
		}
	}

	// Logs exception message to console for debugging
	public static void logException(Throwable t) {
		t.printStackTrace();
		Log.error("Exception occured!" + false);
	}

	// Logs details about URL under test
	public static void enviornmentDetailsURL(String url) {
		Log.info("#############################################");
		Log.info("# Base URL: " + url);
		Log.info("#############################################");
	}

	// Logs details about Browser under test
	public static void enviornmentDetailsBrowser(String browser) {
		Log.info("#############################################");
		Log.info("# Browser: " + browser);
		Log.info("#############################################");
	}
	
}
