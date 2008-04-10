/**
 * 
 */
package org.pentaho.ui.xul;

import org.pentaho.ui.xul.components.XulMessageBox;
import org.pentaho.ui.xul.dom.Document;
import org.pentaho.ui.xul.impl.XulEventHandler;

/**
 * @author OEM
 *
 */
public interface XulDomContainer {
  
  public void addDocument(Document document);
  
  public Document getDocumentRoot();
  
  public XulDomContainer loadFragment(String xulLocation) throws XulException;
  
  public void addEventHandler(String id, String eventClassName);
  
  public XulEventHandler getEventHandler(String key) throws XulException;
  
  public XulMessageBox createMessageBox(String message);

  public void initialize();
  
  public void close();
  
  public boolean isClosed();
}