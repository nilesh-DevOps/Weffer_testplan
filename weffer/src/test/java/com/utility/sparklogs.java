package com.utility;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;

public class sparklogs {
	ExtentTest test;
	
	
	public sparklogs(ExtentTest test) {
		super();
		this.test = test;
	}


	public void fail(String details,String relative_path){
		if (test!=null) {
			System.out.println("fail "+relative_path);
			test.fail(details);
			//test.fail(MediaEntityBuilder.createScreenCaptureFromPath("image\\"+relative_path, details).build());
			test.fail("<a href="+"image\\"+relative_path+" target=\"_blank\">"+relative_path+"</a>");
		}
	}
	
	public void warning(String details,String relative_path){
		if (test!=null) {
			System.out.println("fail "+relative_path);
			test.warning(details);
			System.out.println("Details"+details);
			//test.wa
			//test.fail(MediaEntityBuilder.createScreenCaptureFromPath("image\\"+relative_path, details).build());
			test.warning("<a href="+"image\\"+relative_path+" target=\"_blank\">"+relative_path+"</a>");
		}
	}
	
	public void warning(String details){
		if (test!=null) {
		test.warning(details);
		}
	}
	public void fail(Throwable t){
		if (test!=null) {
		test.fail(t);
		}
	}
	public void fail(String details){
		if (test!=null) {
		test.fail(details);
		}
	}
	public void pass(String details){
		if (test!=null) {
		test.pass(details);
		}
	}
	
	public void info(String details){
		if (test!=null) {
		test.info(details);
		}
	}
	public void info(String details,String relative_path){
		if (test!=null) {
		test.info("<a href="+"image\\"+relative_path+" target=\"_blank\">"+relative_path+"</a>");
		//test.info(MediaEntityBuilder.createScreenCaptureFromPath("image\\"+relative_path).build());
		System.out.println("details");
		//test.addScreenCaptureFromPath("images\\"+relative_path);
		}
	}
	
	public void fail(Throwable t,String relative_path){
		if (test!=null) {
			test.fail(t);
			test.fail("<a href="+"image\\"+relative_path+" target=\"_blank\">"+relative_path+"</a>");
			}
	}
	
}
