package com.form;

import org.openqa.selenium.WebDriver;
import com.utility.Keywords;
import com.utility.sparklogs;

public class form_details {

	public Keywords k;
	sparklogs s;

	public form_details(WebDriver driver, sparklogs s){
		this.s=s;
		k=new  Keywords(driver,s);
	}


	//Entering in name field
	public void name(String name) {
		//String namepath="(//input[@type=\"text\"])[1]";
		//s.info("Entering data");
		String namepath="//span[contains(text(),\"Name\")]/../../../../div[2]/div/div[1]/div/div[1]/input";
		s.info("Entering "+ name+" in name field with path("+namepath+")");
		k.sendkeys(namepath, name);
	}


	//Entering in email field
	public void email( String email) {
		String emailpath="//span[contains(text(),\"Email\")]/../../../../div[2]/div/div[1]/div/div[1]/input";
		s.info("Entering "+ email+" in email field with path("+emailpath+")");
		k.sendkeys(emailpath, email);
	}

	//Entering in phone_number field
	public void contact_no( String number) {
		//String contact_path="(//input[@type=\"text\"])[2]";
		String contact_path="//span[contains(text(),\"Phone number\")]/../../../../div[2]/div/div[1]/div/div[1]/input";
		s.info("Entering "+ number+" in phonenumber field with path("+contact_path+")");
		k.sendkeys(contact_path, number);
	}

	//Entering in cover resume field
	public void resume_cover(String cover) {
		//String contact_path="//span[contains(text(),\"Submit your cover letter or resume\")]/../../../../div[2]/div/div[1]/div/div[1]/input";
		String coverpath="//textarea";
		s.info("Entering "+ cover+" in resume cover field with path("+coverpath+")");
		k.sendkeys(coverpath, cover);
	}
	
	//selecting in position field
	public void select_position( String positions) {
		//String positionpath="//textarea";
		String position[]=positions.split(":");
		System.out.println();
		for(int i=0;i<position.length;i++) {	
			String checkbox="//span[text()=\""+position[i]+"\"]/../../../div[1]";
			if(k.Check_for_presence_of_Element(checkbox)) {
				s.info("Selecting "+ position[i]+" in position field with path("+checkbox+")");
				k.click(checkbox);
			}
			else {
				s.warning(position[i] +"not found");
			}
		}
	}

	//Entering in cover resume field
	public void select_Experience(String Experience) {
		String Experiencepath="//div[@class=\"jgvuAb ybOdnf cGN2le t9kgXb llrsB\"]";
		k.click(Experiencepath);
		String dropdownlistpath="(//span[text()=\""+Experience+"\"]/parent::div)[2]";
		if(k.Check_for_presence_of_Element(dropdownlistpath)) {
			s.info("Selecting "+ Experience+" in position field with path("+dropdownlistpath+")");
			k.click(dropdownlistpath);
		}else {
			s.warning(Experience +"not found");
		}

		//k.click();

	}		

	//click submit button
	public void submit() {
		String submitpath="//span[contains(text(),\"Submit\")]/../../../div[@role=\"button\"]";
		s.info("Click on Submit button path="+submitpath);
		k.click(submitpath);
	}

}
