package com.utility;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Screenshot {
	int get_random_number() {

		int b = (int)(Math.random()*(10000-1000+1)+1000);  
		return b;
	}

	public String take_screenshoot(WebDriver driver) {
		//int b=0;
		int b=get_random_number();
		ru.yandex.qatools.ashot.Screenshot s1= new AShot().shootingStrategy(ShootingStrategies.viewportPasting(5)).takeScreenshot(driver);
		try {
			//b = (int)(Math.random()*(10000-1000+1)+1000);  
			ImageIO.write(s1.getImage(), "PNG", new File("target\\new_report\\image\\"+b+".png"));
			//System.getProperty(key)
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ""+b+".png";
	}


	public String take_screenshot(WebDriver driver) {
		//int b=0;
		int b=get_random_number();
		//ru.yandex.qatools.ashot.Screenshot s1= new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		try {
			File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			//w=w+b;
			FileUtils.copyFile(f, new File("target\\new_report\\image\\"+b+".png"));

			//b = (int)(Math.random()*(10000-1000+1)+1000);  
			//	ImageIO.write(s1.getImage(), "PNG", new File("C:\\Users\\DELL\\Documents\\eclipse Documents Files\\GelCareers\\target\\new_report\\image\\"+b+".png"));
			//System.getProperty(key)
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(b);
		return ""+b+".png";
	}
	/*public int take_screenshoot(WebDriver driver,String reason,String undertake) {
		int b=0;
		Screenshot s1= new AShot().shootingStrategy(ShootingStrategies.viewportPasting(10)).takeScreenshot(driver);
		try {
			b = (int)(Math.random()*(10000-1000+1)+1000);  
			ImageIO.write(s1.getImage(), "PNG", new File(System.getProperty("user.dir")+b+" "+reason+" error.png"));
			//System.getProperty(key)
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return b;*/
}

