package weffer.weffer;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.form.form_details;
import com.form.validations;
import com.utility.Screenshot;
import com.utility.import_csv;
import com.utility.sparklogs;

import io.github.bonigarcia.wdm.WebDriverManager;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class test {
	WebDriver driver;
	ExtentReports extent = new ExtentReports();
	ExtentSparkReporter spark = new ExtentSparkReporter("target/new_report/Spark.html");
	private ExtentTest test;
	private SoftAssert softAssert;
	@BeforeTest
	public void Before_test() {
		extent.attachReporter(spark);
		extent.flush();
	}
	@AfterTest
	public void After_test() {
		extent.flush();
	}
	@BeforeClass
	public void beforeclass() {
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Nilesh\\Documents\\selenium components\\chrome_108\\chromedriver.exe");
	}



	//return row data object
	@DataProvider(name = "csv")
	public Object[][] getData() throws Exception{
		import_csv csv = new import_csv ();
		return csv.readcsv("config\\abcd.csv");
	}

	@BeforeMethod
	void BeforeMethod() {
		WebDriverManager.chromedriver().setup();
		//ChromeOptions option = new ChromeOptions();
		//option.setHeadless(true);
		//option.addArguments("--headless");
		driver = new ChromeDriver();

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println(driver);
		//s.pass("Open Browser opened and Maximized");
	}

	@AfterMethod
	void AfterMethod(ITestResult i) {
		//System.out.println("sdsf"+i.getSkipCausedBy());
		if (i.isSuccess()){
			test.pass("Testcase Passed");
		}
		else {
			///test.fail("Testcase Failed", );
			if (test!=null) {
				new sparklogs(test).fail("Testcase Failed", new Screenshot().take_screenshoot(driver));
			}
		}
		if (i.getThrowable()!=null) {
			if (test!=null) {
				test.fail(i.getThrowable().getMessage());
				new sparklogs(test).fail("Failed", new Screenshot().take_screenshoot(driver));
			}

		}
		if (driver!=null) {
			driver.quit();
		}
	}


	@Test(dataProvider ="csv",priority =1)
	public void f(List<Object> data) throws Exception {
		softAssert = new SoftAssert();
		test = extent.createTest("Testcase data"+data.toString());
		sparklogs s = new sparklogs(test);
		test.info("open browser");
		//new Keywords(driver, s)
		//driver= new Keywords(s).Open();
		test.info("Browser opened");

		String url="https://docs.google.com/forms/d/e/1FAIpQLSdeWwosroxuwHUKOFqjkpdKUVBW9vRnyYVpydiUEbtI5Fyfnw/viewform?usp=sf_link";
		test.info("Navigate to "+url+" URL");
		driver.get(url);

		//System.out.println(data.get(2));
		//System.out.println(data.toString());
		//System.out.println("get"+testcase);
		/*String name="Asd";
		String email="Asd@sdsd.com";
		String contactnumber="9876542";
		String cover="my life";
		String positions="QA";
		String Exprience="1";*/

		String name=(String) data.get(0);
		String email=(String) data.get(1);
		String contactnumber=(String) data.get(2);
		String cover=(String) data.get(4);
		String positions=(String) data.get(3);
		String Exprience=(String) data.get(5);
		Thread.sleep(2000);
		System.out.println((String) data.get(3));

		form_details f= new form_details(driver,s);
		f.name(name);
		f.email(email);
		f.contact_no(contactnumber);
		f.select_position(positions);
		f.resume_cover(cover); 	
		f.select_Experience(Exprience);


		//validation related
		validations v= new validations(driver,s,softAssert);
		boolean name_flag=v.validations_for_name(name);
		boolean phone_flag=v.validations_for_phonenumber(contactnumber);
		boolean email_flag=v.validations_for_email(email);
		boolean position_flag=v.validations_for_positions();
		boolean coverflag=v.validate_cover(cover);

		//System.out.println(name_flag+" "+phone_flag+" "+email_flag+" "+position_flag);
		s.info("before submit",new Screenshot().take_screenshoot(driver));

		//click on submit button
		f.submit();

		//validate post submission
		v.validate_form_submission(name_flag, phone_flag, email_flag, position_flag,coverflag);
		softAssert.assertAll();



	}
}
