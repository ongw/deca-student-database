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
 * Creates a settings frame for the student Module
 */
public class StudentSettingsFrame extends JFrame {
	
	/** The current view mode. */
	private static boolean currentViewMode = true; //true = "Detail", false = "Event"

	/**
	 * Instantiates a new student settings frame.
	 *
	 * @param studentList the student list
	 * @param data the data
	 * @param settingsButton the settings button
	 * @param titlePanel the title panel
	 * @param currentViewList the current view list
	 * @param eventList the event list
	 */
	public StudentSettingsFrame(List<Student> studentList, JPanel data, JButton settingsButton, JPanel titlePanel, List<Student> currentViewList
			, List<Event> eventList) {
	JPanel mainPanel = new JPanel();
	mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
	this.setSize(350,220);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setVisible(true);
	this.add(mainPanel);
	
	JPanel settingsTitlePanel = new JPanel();
	JLabel settingsTitle = new JLabel("Student Settings");
	settingsTitlePanel.add(settingsTitle);
	mainPanel.add(settingsTitlePanel, Component.CENTER_ALIGNMENT);

	JPanel savePanel = new JPanel();
	JButton save = new JButton("Save Student List");
	save.setPreferredSize(new Dimension(300,30));
	save.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			SaveFrame save = new SaveFrame(data, eventList, studentList);
		}
	});		
	savePanel.add(save);
	mainPanel.add(savePanel,Component.CENTER_ALIGNMENT);
	
	JPanel loadPanel = new JPanel();
	JButton load = new JButton("Load Student List");
	load.setPreferredSize(new Dimension(300,30));
	load.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			LoadFrame load = new LoadFrame(data, eventList, studentList);
		}
	});		
	loadPanel.add(load);
	mainPanel.add(loadPanel,Component.CENTER_ALIGNMENT);
	
	JPanel exportPanel = new JPanel();
	JButton export = new JButton("Export to Image");
	export.setPreferredSize(new Dimension(300,30));
	export.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			ExportPngFrame frame = new ExportPngFrame(data);
		}
	});		
	exportPanel.add(export);
	mainPanel.add(exportPanel,Component.CENTER_ALIGNMENT);
	
	JPanel viewPanel = new JPanel();
	JLabel viewLabel = new JLabel("View Mode: ");
	viewPanel.add(viewLabel);
	JComboBox<String> viewBox = new JComboBox<String>();
	viewBox.addItem("Student Details");
	viewBox.addItem("Student Event Preferences");
	viewBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e){
			if (viewBox.getSelectedItem().equals("Student Details")) {
				CardLayout cl = (CardLayout)(titlePanel.getLayout());
				currentViewMode = true;
				cl.show(titlePanel, "Detail");
				data.removeAll();
				for (int i = 0; i < studentList.size(); i++)
					data.add(new StudentDetailDataBar(studentList.get(i), studentList, data, currentViewList, eventList));
			}
			else if(viewBox.getSelectedItem().equals("Student Event Preferences")){
				CardLayout cl = (CardLayout)(titlePanel.getLayout());
				currentViewMode = false;
				cl.show(titlePanel, "Event");
				data.removeAll();
				for (int i = 0; i < studentList.size(); i++)
					data.add(new StudentEventDataBar(studentList.get(i), studentList, data, currentViewList, eventList));
			}
		}
	});		
	viewPanel.add(viewBox);
	mainPanel.add(viewPanel,Component.CENTER_ALIGNMENT);
	
	this.addWindowListener(new WindowAdapter() {
		  public void windowClosing(WindowEvent we) {
		    settingsButton.setEnabled(true);
		  }
		});
	}
	
	/**
	 * Gets the current view.
	 *
	 * @return the current view
	 */
	public static boolean getCurrentView() {
		return currentViewMode;
	}
}
