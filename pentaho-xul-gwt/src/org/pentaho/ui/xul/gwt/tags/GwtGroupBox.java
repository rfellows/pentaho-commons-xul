package org.pentaho.ui.xul.gwt.tags;

import org.pentaho.gwt.widgets.client.utils.StringUtils;
import org.pentaho.ui.xul.XulComponent;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.components.XulCaption;
import org.pentaho.ui.xul.containers.XulGroupbox;
import org.pentaho.ui.xul.dom.Element;
import org.pentaho.ui.xul.gwt.AbstractGwtXulContainer;
import org.pentaho.ui.xul.gwt.GwtXulHandler;
import org.pentaho.ui.xul.gwt.GwtXulParser;
import org.pentaho.ui.xul.gwt.util.GwtUIConst;
import org.pentaho.ui.xul.util.Orient;

import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class GwtGroupBox extends AbstractGwtXulContainer implements XulGroupbox {

  static final String ELEMENT_NAME = "groupbox"; //$NON-NLS-1$
  private CaptionPanel captionPanel; 
  public static void register() {
    GwtXulParser.registerHandler(ELEMENT_NAME, 
    new GwtXulHandler() {
      public Element newInstance() {
        return new GwtGroupBox();
      }
    });
  }

  private enum Property{
    ID, CAPTION
  }

  public GwtGroupBox() {
    super(ELEMENT_NAME);
    this.orientation = Orient.VERTICAL;
    captionPanel = new CaptionPanel();
    setManagedObject(captionPanel);
    captionPanel.setStylePrimaryName("xul-fieldset");
    
    VerticalPanel vp;
    container = vp = new VerticalPanel();
    vp.setHeight("100%");
    vp.setSpacing(2);
    container.setWidth("100%");
    
    vp.setStyleName("vbox");

    ((VerticalPanel) container).setStyleName("vbox");
    captionPanel.add(vp);
  }


  @Override
  public void setAttribute(String name, String value) {
    super.setAttribute(name, value);
    try{
      Property prop = Property.valueOf(name.replace("pen:", "").toUpperCase());
      switch (prop) {
        case CAPTION:
          setCaption(value);
          break;
      }
    } catch(IllegalArgumentException e){
      System.out.println("Could not find Property in Enum for: "+name+" in class"+getClass().getName());
    }
  }

  public void init(com.google.gwt.xml.client.Element srcEle, XulDomContainer container) {
    super.init(srcEle, container);
    if(!StringUtils.isEmpty(srcEle.getAttribute("width"))) {
      captionPanel.setWidth(srcEle.getAttribute("width") + "px");
    } else {
      captionPanel.setWidth("100%");
    }
    if(!StringUtils.isEmpty(srcEle.getAttribute("height"))) {
      captionPanel.setHeight(srcEle.getAttribute("height") + "px");
    } else {
      captionPanel.setHeight("100%");
    }
    
  }
  
  
  
  @Override
  public void addChild(Element element) {
    if(element instanceof XulCaption){
        setCaption(((XulCaption) element).getLabel());  
    }
    super.addChild(element);
  }

  @Override
  public void addChildAt(Element element, int idx) {
    
        // TODO Auto-generated method stub super.addChildAt(element, idx);
      
  }

  public void resetContainer(){
    
    container.clear();
  }
  
  public void setCaption(String caption){
    captionPanel.setCaptionText(caption);
  }
  
  @Override
  public void layout(){
    super.layout();
    for(XulComponent comp : this.getChildNodes()){
      if(comp instanceof GwtCaption){
        this.setCaption(((GwtCaption) comp).getLabel());
      }
    }
  }
  
  public Orient getOrientation() {
    return Orient.VERTICAL;
  }

  @Override
  public void replaceChild(Element oldElement, Element newElement) {
    this.resetContainer();
    super.replaceChild(oldElement, newElement);
  }
  

  
  @Override
  public void setHeight(int height) {
    captionPanel.setHeight(height + "px");
    super.setHeight(height);
  }

  @Override
  public void setWidth(int width) {
    captionPanel.setWidth(width + "px");
    super.setWidth(width);
  }
}
