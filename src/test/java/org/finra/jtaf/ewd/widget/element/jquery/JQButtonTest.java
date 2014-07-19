package org.finra.jtaf.ewd.widget.element.jquery;

import org.finra.jtaf.ewd.ExtWebDriver;
import org.finra.jtaf.ewd.session.SessionManager;
import org.finra.jtaf.ewd.widget.IInteractiveElement;
import org.finra.jtaf.ewd.widget.element.Element;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JQButtonTest {
//	public static String url = "http://jqueryui.com/button/";
	public static String url = "http://appitize.io:49214/";

	//public static String frame = "//iframe[@class='demo-frame']"; 	    
	
	ExtWebDriver wd;

    @Before
    public void setup() throws Exception {
	       wd = SessionManager.getInstance().getCurrentSession();
    }
	 
    @After
    public void teardown() {
        wd.close();
        SessionManager.getInstance().removeSession(wd);
    }
    
    private void openAppAndSelectFrame() throws Exception{
    	wd.open(url);
    	
//    	wd.selectFrame(new Element("//iframe[@class='demo-frame']"));
    }
    
    @Test
    public void testTheButton1() throws Exception {
    	openAppAndSelectFrame();
    	IInteractiveElement button = new JQButton("//button[contains(@class, 'ui-button')]");
    	button.waitForElementPresent();
    	button.click();
    	Assert.assertEquals("Testing the retrieval of the button", "I am a button", button.getLabel());
    	
    }
    
    @Test
    public void testTheButton2() throws Exception {
    	openAppAndSelectFrame();
    	IInteractiveElement button = new JQButton("//input[@type='submit']");
    	button.waitForElementPresent();
    	button.click();
    	Assert.assertEquals("Testing the retrieval of the button", "I am a submit", button.getLabel());
    	
    }
    
    @Test
    public void testTheButton3() throws Exception {
    	openAppAndSelectFrame();
    	IInteractiveElement button = new JQButton("//input[@type='button']");
    	button.waitForElementPresent();
    	button.click();
    	Assert.assertEquals("Testing the retrieval of the button", "I am a input button", button.getLabel());
    	
    }
    
    @Test
    public void testTheButton4() throws Exception {
    	openAppAndSelectFrame();
    	IInteractiveElement button = new JQButton("//a[@role='button']");
    	button.waitForElementPresent();
    	button.click();
    	Assert.assertEquals("Testing the retrieval of the button", "I am an anchor", button.getLabel());
    	
    }
}