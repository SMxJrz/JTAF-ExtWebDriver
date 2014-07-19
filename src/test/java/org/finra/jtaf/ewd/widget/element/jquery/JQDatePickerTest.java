package org.finra.jtaf.ewd.widget.element.jquery;

import org.finra.jtaf.ewd.ExtWebDriver;
import org.finra.jtaf.ewd.session.SessionManager;
import org.finra.jtaf.ewd.widget.IInteractiveElement;
import org.finra.jtaf.ewd.widget.element.Element;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JQDatePickerTest {
//	public static String url = "http://jqueryui.com/button/";
	public static String url = "http://appitize.io:49220/";

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
    
    //temporary
    private void openApp() throws Exception{
    	wd.open(url);
    	
//    	wd.selectFrame(new Element("//iframe[@class='demo-frame']"));
    }
    
    @Test
    public void testGettingYear() throws Exception {
    	openApp();
    	JQDatePicker datePicker = new JQDatePicker("//input[@id='dpicker']");
    	datePicker.waitForElementPresent();
    	datePicker.focusOn();
    	Assert.assertEquals("Testing the retrieval of the year field", "2014", datePicker.getYear());
    	
    }
    
    @Test
    public void testGettingMonth() throws Exception {
    	openApp();
    	JQDatePicker datePicker = new JQDatePicker("//input[@id='dpicker']");
    	datePicker.waitForElementPresent();
    	datePicker.focusOn();
    	Assert.assertEquals("Testing the retrieval of the month field", "July", datePicker.getMonth());
    	
    }
    
    @Test
    public void testClickingNext() throws Exception {
    	openApp();
    	JQDatePicker datePicker = new JQDatePicker("//input[@id='dpicker']");
    	datePicker.waitForElementPresent();
    	datePicker.focusOn();
    	datePicker.clickNext();
    	Assert.assertEquals("Testing the retrieval of the next button", "August", datePicker.getMonth());
    	
    }
    
    @Test
    public void testClickingPrev() throws Exception {
    	openApp();
    	JQDatePicker datePicker = new JQDatePicker("//input[@id='dpicker']");
    	datePicker.waitForElementPresent();
    	datePicker.focusOn();
    	datePicker.clickPrev();
    	Assert.assertEquals("Testing the retrieval of the previous button", "June", datePicker.getMonth());
    	
    }
    
    @Test
    public void testClickingDay() throws Exception {
    	openApp();
    	JQDatePicker datePicker = new JQDatePicker("//input[@id='dpicker']");
    	datePicker.waitForElementPresent();
    	datePicker.focusOn();
    	datePicker.clickPrev();
    	datePicker.selectDay(10);
    	Assert.assertEquals("Test month", "June", datePicker.getMonth());
    	Assert.assertEquals("Test year", "2014", datePicker.getYear());
    	Assert.assertEquals("Test day", "10", datePicker.getDay());    		
    }
    
    @Test
    public void testSendKeys() throws Exception {
    	openApp();
    	JQDatePicker datePicker = new JQDatePicker("//input[@id='dpicker']");
    	datePicker.waitForElementPresent();
    	datePicker.focusOn();
    	datePicker.type("01/01/2001");
    	datePicker.focusOn();
    	Assert.assertEquals("Test month", "January", datePicker.getMonth());
    	Assert.assertEquals("Test year", "2001", datePicker.getYear());
    	Assert.assertEquals("Test day", "1", datePicker.getDay());    	
    }
    
    @Test
    public void testSelectDateForwards() throws Exception {
    	openApp();
    	JQDatePicker datePicker = new JQDatePicker("//input[@id='dpicker']");
    	datePicker.waitForElementPresent();
    	datePicker.focusOn();
    	datePicker.selectDate("08/23/2016");
    	Assert.assertEquals("Test month", "August", datePicker.getMonth());
    	Assert.assertEquals("Test year", "2016", datePicker.getYear());
    	Assert.assertEquals("Test day", "23", datePicker.getDay());
    }
    
    @Test
    public void testSelectDateBackwards() throws Exception {
    	openApp();
    	JQDatePicker datePicker = new JQDatePicker("//input[@id='dpicker']");
    	datePicker.waitForElementPresent();
    	datePicker.focusOn();
    	datePicker.selectDate("08/23/2012");
    	Assert.assertEquals("Test month", "August", datePicker.getMonth());
    	Assert.assertEquals("Test year", "2012", datePicker.getYear());
    	Assert.assertEquals("Test day", "23", datePicker.getDay());    	
    }
}