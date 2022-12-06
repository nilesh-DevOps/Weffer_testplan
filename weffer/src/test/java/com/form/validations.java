package com.form;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import com.utility.Keywords;
import com.utility.Screenshot;
import com.utility.sparklogs;

public class validations {
	Keywords k;
	WebDriver driver;
	sparklogs s;
	SoftAssert softAssert;
	public validations(WebDriver driver,sparklogs s,SoftAssert softAssert){
		this.softAssert=softAssert;
		this.s=s;
		this.driver=driver;
		k= new Keywords(driver,s,softAssert);
	}
	public boolean validations_for_name(String name) {
		s.info("Validate Name");
		String pattern = "[a-zA-Z\\s']+";
		String phonenumbervalidationpath="//span[contains(text(),\"Name\")]/../../../../div[3]/span";
		if(!name.equals("")) {
			return commonvalidation(pattern, phonenumbervalidationpath, name,"Name");
		}
		else {
			return false;
		}
	}
	public boolean validate_cover(String cover) {
		if(!cover.equals("")) {
			return false;
		}
		else {
			return true;
		}
	}
	public boolean validations_for_email(String email) {
		s.info("Validate Email");
		//^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$
		String pattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		String emailvalidationpath="//span[contains(text(),\"Email\")]/../../../../div[3]/span";
		if(!email.equals("")) {
			return commonvalidation(pattern, emailvalidationpath, email,"Email");
		}
		else {
			return true;
		}
	}

	public boolean validations_for_positions() throws Exception {
		s.info("Validate Positions");
		String validation_position="//span[contains(text(),\"Which position(s) are you interested in?\")]/../../../../div[3]/span";

		//path of checkbox
		String selected_positions="//div[@class=\"uVccjd aiSeRd FXLARc wGQFbe BJHAP oLlshd i9xfbb N2RpBe\"]";

		List<WebElement> element= driver.findElements(By.xpath(selected_positions));
		//check for count greater then 3
		if (element.size()==0) {
			return false;
		}else {
			if(element.size()>=3) {
				if(k.Check_for_presence_of_Element(validation_position)) {
					s.pass("Validation appeared "+validation_position);
					k.check_data(validation_position, "Cannot select more than 2");
				}
				else {
					softAssert.fail("Validation didn't appeared path="+validation_position);
					s.fail("Validation didn't appeared path="+validation_position,new Screenshot().take_screenshoot(driver));
				}
				return false;
			}
			else {
				return true;
			}
		}
	}

	public boolean validations_for_phonenumber(String number) {
		s.info("Validate Phone Number");
		String pattern = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
		String phonenumbervalidationpath="//span[contains(text(),\"Phone number\")]/../../../../div[3]/span";
		return commonvalidation(pattern, phonenumbervalidationpath, number,"Phone Number");
	}

	
	//if all flag are true means , all are valid input
	public void validate_form_submission(boolean nameflag,boolean phoneflag,boolean emailflag,boolean positionflag,boolean coverflag) {
		s.info("Validate Post submission");				
		String submitanotherreponselink="//a[text()=\"Submit another response\"]";

		if (nameflag && phoneflag && emailflag && positionflag && coverflag) {
			////a[text()="Submit another response"]
			s.info("Name, email, phone number and positions are valid");
			if(k.Check_for_presence_of_Element(submitanotherreponselink)){
				s.pass("Test Passed, form submitted");
				//	s.pass("Element present "+submitanotherreponselink);
				System.out.println("sucess");
			}else {
				s.fail("Test failed, Form not submitted", new Screenshot().take_screenshoot(driver));
				System.out.println("fail");
			}

		}
		else {
			if(!k.Check_for_presence_of_Element(submitanotherreponselink)){
				s.pass("Test Passed, form not submitted");
				System.out.println("sucess1");
			}else {
				s.fail("Test failed, Form submitted", new Screenshot().take_screenshoot(driver));
				System.out.println("fail1");
			}
		}
	}

	//common validation check for name,email,phone number
	boolean commonvalidation(String pattern, String validation_path, String data,String name_validation) {
		boolean flag=false;
		//String pattern = pattern//;"^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
		if (data.matches(pattern)) {     
			System.out.println("Valid");
			s.info("Check for Valid " +name_validation);
			flag=true;

		} else {     
			System.out.println("Invalid"); 
			s.info("Check for Invalid " +name_validation);
			flag=false;
		}
		if(!flag) {
			if(k.Check_for_presence_of_Element(validation_path)) {
				s.pass(validation_path+"Validation appeared");

			}else {
				s.fail("Validation alert didn't appear path="+validation_path,new Screenshot().take_screenshoot(driver));
				softAssert.fail("Validation alert didn't appear path="+validation_path);

			}

		}
		else {
			if(k.Check_for_presence_of_Element(validation_path)) {
				s.fail("Validation alert appear path="+validation_path,new Screenshot().take_screenshoot(driver));
				softAssert.fail("Validation alert appear path="+validation_path);
			}else {
				s.pass("No validation appeared");
			}

		}
		return flag;
	}
}
