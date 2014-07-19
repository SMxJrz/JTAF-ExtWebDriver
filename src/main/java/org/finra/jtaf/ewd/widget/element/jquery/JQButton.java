package org.finra.jtaf.ewd.widget.element.jquery;

import org.finra.jtaf.ewd.widget.IButton;
import org.finra.jtaf.ewd.widget.IElement;
import org.finra.jtaf.ewd.widget.WidgetException;
import org.finra.jtaf.ewd.widget.element.Element;
import org.finra.jtaf.ewd.widget.element.html.Button;

public class JQButton extends Button implements IButton {

	
	public JQButton(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}

	public String getLabel() throws WidgetException{
		//determine what kind of jquery button that we have
		IElement spanCheck = new Element(this.getLocator() + "/span");
		if(spanCheck.isElementPresent()){
			return super.getLabel();
		}else if(this.isAttributePresent("value")){
			return this.getAttribute("value");
		}else{
			return null;
		}
	}
}
