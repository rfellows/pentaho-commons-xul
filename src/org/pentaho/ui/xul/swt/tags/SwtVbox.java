package org.pentaho.ui.xul.swt.tags;

import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.XulElement;
import org.pentaho.ui.xul.XulWindowContainer;
import org.pentaho.ui.xul.containers.XulVbox;
import org.pentaho.ui.xul.swt.Orient;

public class SwtVbox extends SwtBox implements XulVbox {
  private static final long serialVersionUID = 1151231071964721100L;

  public SwtVbox(XulElement parent, XulDomContainer container, String tagName) {
    super(parent, tagName, Orient.VERTICAL);
  }

}
