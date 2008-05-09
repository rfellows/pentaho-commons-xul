/**
 * 
 */
package org.pentaho.ui.xul.swing.tags;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;

import org.pentaho.ui.xul.XulComponent;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.XulDomException;
import org.pentaho.ui.xul.components.XulRadioGroup;
import org.pentaho.ui.xul.swing.SwingElement;
import org.pentaho.ui.xul.util.Orient;

/**
 * @author aphillips
 *
 */
public class SwingRadioGroup extends SwingElement implements XulRadioGroup {

	private ButtonGroup buttonGroup = new ButtonGroup();

	public SwingRadioGroup(XulComponent parent, XulDomContainer domContainer,
			String tagName) {
		super("radiogroup");

		this.orientation = Orient.VERTICAL;

		container = new JPanel(new GridBagLayout());

		managedObject = container;

		resetContainer();
	}

	@Override
	public void addComponent(XulComponent c) {
		addComponentToButtonGroup(c);

		super.addComponent(c);
	}

	protected void addComponentToButtonGroup(XulComponent c) {
		for (XulComponent child : c.getChildNodes()) {
			addComponentToButtonGroup(child);
		}
		if (AbstractButton.class.isAssignableFrom(c.getManagedObject()
				.getClass())) {
			this.buttonGroup.add((AbstractButton) c.getManagedObject());
		}
	}

	public void resetContainer() {

		container.removeAll();

		gc = new GridBagConstraints();
		gc.gridy = GridBagConstraints.RELATIVE;
		gc.gridx = 0;
		gc.gridheight = 1;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.insets = new Insets(2, 2, 2, 2);
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.weightx = 1;

	}

	@Override
	public void replaceChild(XulComponent oldElement, XulComponent newElement)
			throws XulDomException {
		this.resetContainer();
		super.replaceChild(oldElement, newElement);
	}
}  
  