import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

// TODO: Auto-generated Javadoc
/**
 * The Frame used to mass select and change maxFlightSize for multiple events.
 */
public class EventChangeFlightSizeFrame extends JFrame {

	/**
	 * Instantiates a new frame used as interface.
	 *
	 * @param eventList the event list
	 * @param data the data
	 * @param currentViewList the current view list
	 */
	public EventChangeFlightSizeFrame(List<Event> eventList, JPanel data, List<Event> currentViewList) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		this.setSize(350,220);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.add(mainPanel);

		JPanel settingsTitlePanel = new JPanel();
		JLabel settingsTitle = new JLabel("Change Flight Size");
		settingsTitlePanel.add(settingsTitle);
		mainPanel.add(settingsTitlePanel, Component.CENTER_ALIGNMENT);

		JPanel flightPanel = new JPanel();
		JLabel flightLabel = new JLabel("New Flight Size: ");
		flightPanel.add(flightLabel);
		JComboBox<String> flightBox = new JComboBox<String>();
		for (int i = 1; i <= 10; i++)
			flightBox.addItem(String.valueOf(i));
		flightBox.addItem("None");
		if (EventSettingsFrame.getDefaultFlightSize() != Integer.MAX_VALUE)
			flightBox.setSelectedIndex(EventSettingsFrame.getDefaultFlightSize() -1);
		else flightBox.setSelectedItem(9);
		flightPanel.add(flightBox);
		mainPanel.add(flightPanel,Component.CENTER_ALIGNMENT);

		JPanel confirmPanel = new JPanel();
		JButton confirm = new JButton("Confirm");
		this.getRootPane().setDefaultButton(confirm);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int newFlightSize;
				if (flightBox.getSelectedItem().equals("None"))
					newFlightSize = Integer.MAX_VALUE;
				else newFlightSize = (Integer.parseInt((String)flightBox.getSelectedItem()));
				Component[] component = data.getComponents();
				for (int i = 0; i < component.length; i++) {
					if (component[i] instanceof EventDetailDataBar) {
						if (((EventDetailDataBar) component[i]).getEvent().getSelected())
							((EventDetailDataBar) component[i]).getEvent().setFlightSize(newFlightSize);
					}
				}
				data.removeAll();
				data.repaint();
				data.revalidate();
					for (int i = 0; i < currentViewList.size(); i++)
						data.add(new EventDetailDataBar(currentViewList.get(i), eventList, data, currentViewList));
				data.repaint();
				data.revalidate();
				closeFrame();
			}
		});		
		confirmPanel.add(confirm);
		mainPanel.add(confirmPanel,Component.CENTER_ALIGNMENT);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {

			}
		});
	}
	
	/**
	 * Closes the frame.
	 */
	public void closeFrame() {
		this.setVisible(false);
	}
}
