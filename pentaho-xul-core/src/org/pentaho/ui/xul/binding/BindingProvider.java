package org.pentaho.ui.xul.binding;

import org.pentaho.ui.xul.XulComponent;
import org.pentaho.ui.xul.XulEventSource;

/**
 * User: nbaker
 * Date: Jun 28, 2010
 * Time: 8:47:29 AM
 *
 * Registering a BindingProvider with a XulComponent allows bindings created by that component to be
 * customized.
 */
public interface BindingProvider {
  Binding getBinding( XulEventSource source, String prop1, XulEventSource target, String prop2);
  Binding getBinding( XulEventSource source, String prop1, XulEventSource target, String prop2, BindingConvertor<?,?> defaultConvertor);
}
