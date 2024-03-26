package qtriptest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class ReportSingleton {

    private static ExtentReports extent;
    private static ExtentTest test;

    private ReportSingleton() {
        // private constructor to prevent instantiation
    }

    // public static ExtentReports getExtentInstance() {
    //     if (extent == null) {
    //         // provide the path where you want to save the Extent Report
    //         String timestampString = String.valueOf(System.currentTimeMillis());
    //         extent = new ExtentReports(System.getProperty("user.dir") + "/OurExtentReport" + timestampString + ".html", true);
    //         extent.loadConfig(new File(System.getProperty("user.dir") + "/extent_customization_configs.xml"));
    //         // Additional configuration if needed
    //     }
    //     return extent;
    // }

    public static ExtentReports getExtentInstance() {
        if (extent == null) {
            extent = new ExtentReports("path/to/extent-report.html", true);
            // Load custom configurations
            loadCustomConfigurations();
            // Additional configuration if needed
        }
        return extent;
    }

    private static void loadCustomConfigurations() {
        // Load custom configurations from the XML file
        String xmlConfigPath = System.getProperty("user.dir") + "/extent_customization_configs.xml";
        File xmlConfigFile = new File(xmlConfigPath);

        if (xmlConfigFile.exists()) {
            extent.loadConfig(xmlConfigFile);
        } else {
            // Log a warning or handle the absence of the XML file
            System.out.println("Custom configuration file not found. Using default configurations.");
        }
    }

    public static void startTest(String testName, String testDescription) {
        test = extent.startTest(testName, testDescription);
    }

    public static void logStep(String logStatus, String stepDescription) {
        if (logStatus.equalsIgnoreCase("pass")) {
            test.log(LogStatus.PASS, stepDescription);
        } else if (logStatus.equalsIgnoreCase("fail")) {
            test.log(LogStatus.FAIL, stepDescription);
        } else if (logStatus.equalsIgnoreCase("error")) {
            test.log(LogStatus.ERROR, stepDescription);
        } else {
            test.log(LogStatus.INFO, stepDescription);
        }
    }

    public static void captureAndAttachScreenshot(WebDriver driver) {
        try {
            String screenshotPath = capture(driver);
            test.log(LogStatus.INFO, "Screenshot: " + test.addScreenCapture(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String capture(WebDriver driver) throws IOException {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "/QKARTImages/" + System.currentTimeMillis() + ".png");
        String filePath = dest.getAbsolutePath();
        FileUtils.copyFile(scrFile, dest);
        return filePath;
    }

    public static void endTest() {
        extent.endTest(test);
    }

    public static void flushReport() {
        extent.flush();
    }
}