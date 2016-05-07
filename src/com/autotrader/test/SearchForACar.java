package com.autotrader.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.autotrader.helper.AutotraderHelper;
import com.autotrader.pages.HomePage;
import com.autotrader.pages.SearchResultPage;
import com.autotrader.utilities.ExcelUtils;

public class SearchForACar extends AutotraderHelper
{
	private HomePage homepage;
	private SearchResultPage searchResultPage;
	private String postcode;
	private String distance;
	private String carMake;
	private String path;
	
	@Before
	public void setUp() throws Exception
	{
		postcode = null;
		distance = null;
		carMake = null;
		path = "./src/com/autotrader/data/ExcelData.xlsx";
		
		
		launchBrowser("Chrome");
		homepage = new HomePage();
		searchResultPage = new SearchResultPage();	
		ExcelUtils.setExcelFile(path);
	}
	
	@After
	public void tearDown() throws Exception
	{
		ExcelUtils.closeWorkBook();
		closeBrowser();
	}
	
	@Test
	public void SearchForACarTest() throws Exception
	{
		
		for(int row=1; row<ExcelUtils.LastRowNum(path); row++)
		{
			postcode = ExcelUtils.getCellData(row, 0);
			distance = ExcelUtils.getCellData(row, 1);
			carMake = ExcelUtils.getCellData(row, 2);
			
			GivenINavigateAutotraderHomepage();
			homepage.andIAmOnAutotraderHomepage();
			homepage.whenIEnterValidPostcode(postcode);
			homepage.andISelectDistanceToPostcode(distance);
			homepage.andISelectACarMake(carMake);
			homepage.andIClickOnSearchCarButton();
			searchResultPage.thenIAmOnSearchResultPage(carMake);
		
		}
		
	}
}
