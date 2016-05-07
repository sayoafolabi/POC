package com.autotrader.test;

import org.junit.*;

import com.autotrader.helper.AutotraderHelper;
import com.autotrader.pages.HomePage;
import com.autotrader.pages.SearchResultPage;

public class SearchForACarWithinAParticularPriceRange extends AutotraderHelper
{
	private HomePage homepage;
	private SearchResultPage searchPage;
	
	@Before
	public void setUp() throws Exception
	{
		launchBrowser("Chrome");
		homepage = new HomePage();
		searchPage = new SearchResultPage();
		
	}
	
	@After
	public void tearDown() throws Exception
	{
		closeBrowser();
		
	}
	
	@Test
	public void SearchForACarWithinAParticularPriceRangeTest() throws Exception
	{
		GivenINavigateAutotraderHomepage();
		homepage.andIAmOnAutotraderHomepage();
		homepage.whenIEnterValidPostcode("OL9 8LE");
		homepage.andISelectDistanceToPostcode("55");
		homepage.andISelectACarMake("Audi");
		homepage.andISelectAPriceFrom("4500");
		homepage.andISelectAPriceTo("15000");
		homepage.andIClickOnSearchCarButton();
		searchPage.thenIAmOnSearchResultPage("Audi");
	}
}
