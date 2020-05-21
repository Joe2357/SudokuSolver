package GUI;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Layout {
	private static GridBagLayout gBag = new GridBagLayout();
	private static GridBagConstraints gbc;
	
	public Layout() {
		gbc = new GridBagConstraints();
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
	}

	public void gbInsert(JFrame frame, Component c, int x, int y, int w, int h) {
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gBag.setConstraints(c, gbc);
		frame.add(c);
	}

	public void gbInsert(JPanel panel, Component c, int x, int y, int w, int h) {
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gBag.setConstraints(c, gbc);
		panel.add(c);
	}

	public GridBagLayout getGBC() {
		return gBag;
	}
}
