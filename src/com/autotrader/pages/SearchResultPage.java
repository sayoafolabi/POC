package com.autotrader.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

import com.autotrader.helper.AutotraderHelper;

public class SearchResultPage 
{
	private List<WebElement> searchResult;
	
	
	public void thenIAmOnSearchResultPage() throws Exception
	{
		searchResult = AutotraderHelper.getElementsByClassName("search-result__title");
		Assert.assertTrue("Search result list not displayed", searchResult.size()>0);
	}
	
	public void thenIAmOnSearchResultPage(String make) throws Exception
	{
		searchResult = AutotraderHelper.getElementsByClassName("search-result__title");
		
		for(WebElement element : searchResult)
		{
			Assert.assertTrue("Search result list not displayed", 
								element.getText().toLowerCase().contains(make.toLowerCase()));
		}
		
	}
	
}
