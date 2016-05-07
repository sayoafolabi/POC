package com.autotrader.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.autotrader.helper.AutotraderHelper;

public class HomePage extends AutotraderHelper
{
	
	private WebElement autoTraderLogo;
	private WebElement postcode;
	private WebElement distance;
	private WebElement make;
	private WebElement submitButton;
	private WebElement priceFrom;
	private WebElement priceTo;
	
	
	
		
	public void andIAmOnAutotraderHomepage() throws Exception
	{
		autoTraderLogo = getElementByClassName("site-header__logo");
		Assert.assertTrue("Autotrader homepage is not displayed", autoTraderLogo.isDisplayed());
	
	}
	
	public void whenIEnterValidPostcode() throws Exception
	{
		postcode = getElementById("postcode");
		postcode.clear();
		postcode.sendKeys("OL9 8LE");
	}
	
	public void whenIEnterValidPostcode(String code) throws Exception
	{
		postcode = getElementById("postcode");
		postcode.clear();
		postcode.sendKeys(code);
	}
	
	public void andISelectAPriceFrom(String from) throws Exception
	{
		priceFrom = getElementById("searchVehiclesPriceFrom");
		selectByValue(priceFrom, from);
	}
	
     public void andISelectAPriceTo(String to) throws Exception
     {
    	 priceTo = getElementById("searchVehiclesPriceTo");
    	 selectByValue(priceTo, to);
     }
	
	public void andISelectDistanceToPostcode() throws Exception
	{
		distance = getElementById("radius");
		AutotraderHelper.selectByValue(distance, "50");		
		
	}
	
	public void andISelectDistanceToPostcode(String within) throws Exception
	{
		distance = AutotraderHelper.getElementById("radius");
		AutotraderHelper.selectByValue(distance, within);		
		
	}
	
	public void andISelectACarMake() throws Exception
	{
		make = AutotraderHelper.getElementById("searchVehiclesMake");
		AutotraderHelper.selectByValue(make, "audi");
	}
	
	public void andISelectACarMake(String carMake) throws Exception
	{
		make = AutotraderHelper.getElementById("searchVehiclesMake");
		AutotraderHelper.selectByValue(make, carMake.toLowerCase());
	}
	
	public SearchResultPage andIClickOnSearchCarButton() throws Exception
	{
		submitButton = AutotraderHelper.getElementById("search");
		submitButton.click();
		
		return new SearchResultPage();
	}
	
	
	
	
}
