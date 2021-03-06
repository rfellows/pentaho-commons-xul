package org.pentaho.ui.xul.gwt.tags;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import org.pentaho.gwt.widgets.client.utils.StringUtils;
import org.pentaho.ui.xul.XulComponent;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.components.XulImage;
import org.pentaho.ui.xul.dom.Element;
import org.pentaho.ui.xul.gwt.AbstractGwtXulComponent;
import org.pentaho.ui.xul.gwt.GwtXulHandler;
import org.pentaho.ui.xul.gwt.GwtXulParser;
import org.pentaho.ui.xul.stereotype.Bindable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class GwtImage extends AbstractGwtXulComponent implements XulImage {

  static final String ELEMENT_NAME = "image"; //$NON-NLS-1$
  private enum Property{
    ID, DISABLED, SRC
  }
  public static void register() {
    GwtXulParser.registerHandler(ELEMENT_NAME, new GwtXulHandler() {
      public Element newInstance() {
        return new GwtImage();
      }
    });
  }

  private Image image;
  private String onclick;

  public GwtImage() {
    super(ELEMENT_NAME);
    image = new Image();
    setManagedObject(image);
    image.setStyleName("xul-image"); //$NON-NLS-1$
  }

  @Override
  public void setAttribute(String name, String value) {
    super.setAttribute(name, value);
    try{
      Property prop = Property.valueOf(name.replace("pen:", "").toUpperCase());
      switch (prop) {

        case DISABLED:
          setDisabled("true".equals(value));
          break;
        case SRC:
          setSrc(value);
          break;
      }
    } catch(IllegalArgumentException e){
      System.out.println("Could not find Property in Enum for: "+name+" in class"+getClass().getName());
    }
  }

  public void init(com.google.gwt.xml.client.Element srcEle, XulDomContainer container) {
    super.init(srcEle, container);
    setSrc(srcEle.getAttribute("src")); //$NON-NLS-1$
    if(StringUtils.isEmpty(srcEle.getAttribute("onclick")) == false){
      setOnclick(srcEle.getAttribute("onclick")); //$NON-NLS-1$
    }
    setDisabled("true".equals(srcEle.getAttribute("disabled"))); //$NON-NLS-1$//$NON-NLS-2$
  }

  public void layout() {
    image.setTitle(this.getTooltiptext());
    if (StringUtils.isEmpty(this.getTooltiptext()) == false) {
      image.setTitle(this.getTooltiptext());
    }
  }

  @Bindable
  public String getSrc() {
    return image.getUrl();
  }

  @Bindable
  public void setSrc(String src) {
    if(src == null){
      image.setUrl(src);
      return;
    }
    image.setUrl(GWT.getModuleBaseURL() + src);
  }

  @Bindable
  public void setSrc(Object img) {
    if (img instanceof String) {
      setSrc(GWT.getModuleBaseURL() + img);
    }
    if (img instanceof Image) {
      setSrc(((Image) img).getUrl());
    } else {
      throw new UnsupportedOperationException();
    }
  }

  public void refresh() {

  }

  @Override
  @Bindable
  public void setTooltiptext(String tooltip) {
    super.setTooltiptext(tooltip);
    image.setTitle(this.getTooltiptext());
  }
  
  @Override
  @Bindable
  public void setVisible(boolean visible) {
    image.setVisible(visible);
  }
  
  @Override
  @Bindable
  public boolean isVisible() {
    return image.isVisible();
  }

  public String getOnclick() {
    return onclick;
  }

  public void setOnclick(final String onclick) {
    this.onclick = onclick;
    image.addClickHandler(new ClickHandler(){
      public void onClick(ClickEvent clickEvent) {
        invoke(onclick, new Object[]{});
      }
    });
    image.getElement().getStyle().setProperty("cursor","pointer");
  }
}
