package org.finra.jtaf.ewd.widget.element.jquery;

import org.finra.jtaf.ewd.widget.IInteractiveElement;
import org.finra.jtaf.ewd.widget.WidgetException;
import org.finra.jtaf.ewd.widget.element.Element;
import org.finra.jtaf.ewd.widget.element.InteractiveElement;
import org.openqa.selenium.Keys;

/**
 * This widget automates a DatePicker created using JQuery
 * 
 * @author MeleS
 *
 */
public class JQDatePicker extends InteractiveElement implements IInteractiveElement {

	//Moving parts
	private final String container = "//div[@id='ui-datepicker-div']";
	private final String headerLocator = container + "/div[contains(@class, 'ui-datepicker-header')]";
	private final String previousLocator = headerLocator + "/a[contains(@class, 'ui-datepicker-prev')]";
	private final String nextLocator = headerLocator + "/a[contains(@class, 'ui-datepicker-next')]";	
	private final String titleLocator = headerLocator + "/div[@class='ui-datepicker-title']";
	private final String currYearLocator = titleLocator + "/span[@class='ui-datepicker-year']";
	private final String currMonthLocator = titleLocator + "/span[@class='ui-datepicker-month']";
	private final String calendarLocator = container + "/table[@class='ui-datepicker-calendar']";
	private final String currDayLocator = calendarLocator + "/descendant::td[contains(@class, '  ui-datepicker-current-day')]";
	
	private InteractiveElement previous;
	private InteractiveElement next;
	private Element year;
	private Element month;
	private Element day;
	
	public enum Months {
		JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST,
		SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;
	}
	
	/**
	 * Creates a JQDatePicker widget using the provided locator, only provide the field 
	 * for the date picker and not the actual calendar itself!
	 * 
	 * @param locator
	 */
	public JQDatePicker(String locator) {
		super(locator);
		this.previous =  new InteractiveElement(previousLocator);
		this.next =  new InteractiveElement(nextLocator);
		this.year =  new Element(currYearLocator);
		this.month = new Element(currMonthLocator);
		this.day = new Element(currDayLocator);
	}
	
	/**
	 * Gets the year currently selected within the datepicker
	 * @return the currently selected year
	 * @throws WidgetException
	 */
	public String getYear() throws WidgetException{
		if(!year.isElementPresent()){
			this.focusOn();
			year.waitForVisible();
		}
		return year.getText();
	}
	
	/**
	 * Gets the month currently selected within the datepicker
	 * @return the currently selected month
	 * @throws WidgetException
	 */
	public String getMonth() throws WidgetException{
		if(!month.isElementPresent()){
			this.focusOn();
			month.waitForVisible();
		}
		return month.getText();
	}
	
	/**
	 * Gets the current day selected within the datepicker
	 * @return the current day selected within the datepicker
	 * @throws WidgetException
	 */
	public String getDay() throws WidgetException{
		if(!day.isElementPresent()){
			this.focusOn();
			day.waitForVisible();
		}
		return day.getText();
	}
	
	/**
	 * Clicks the next button within the datepicker
	 * 
	 * TODO this should probably just be private...
	 * @throws WidgetException
	 */
	public void clickNext() throws WidgetException{
		next.click();
	}
	
	/**
	 * Clicks the previous button within the datepicker
	 * 
	 * TODO this should probably just be private...
	 * @throws WidgetException
	 */
	public void clickPrev() throws WidgetException{
		previous.click();
	}
	
	/**
	 * Clicks the day within the datepicker
	 * 
	 * TODO this should probably just be private as well
	 * 
	 * @param theDay
	 * @throws WidgetException
	 */
	public void selectDay(Integer theDay) throws WidgetException{
		IInteractiveElement clickMe = new InteractiveElement(calendarLocator + "/descendant::a[text()='" + theDay + "']");
		clickMe.click();
	}

	/**
	 * Just types the date into the field
	 */
	public void type(String date) throws WidgetException{
		super.type(date);
		super.sendKeys(Keys.ENTER);
	}
	
	/**
	 * send date in mm/dd/yyyy this uses the UI to select the date
	 * @param date
	 * @throws WidgetException 
	 * @throws NumberFormatException 
	 */
	public void selectDate(String date) throws NumberFormatException, WidgetException{
		String[] brokenUp = date.split("/");
		String month = brokenUp[0];
		String day = brokenUp[1];
		String year = brokenUp[2];
		gotoMonthYear(Months.values()[Integer.valueOf(month) -1], Integer.valueOf(year));
		this.selectDay(Integer.valueOf(day));
		System.out.println("done");
	}
	
	/*helper*/
	private void gotoMonthYear(Months month, int year) throws WidgetException{
		Months currMonth = Months.valueOf(this.getMonth().toUpperCase());
		int currYear = Integer.valueOf(this.getYear());
		while(year != currYear || currMonth != month){
			if(year < currYear){
				this.clickPrev();
			}else if(year > currYear){
				this.clickNext();
			}else if (month.ordinal() < currMonth.ordinal()){
				this.clickPrev();
			}else {
				this.clickNext();
			}
			currMonth = Months.valueOf(this.getMonth().toUpperCase());
			currYear = Integer.valueOf(this.getYear());
		}
	}
}
