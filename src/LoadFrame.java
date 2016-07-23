import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

// TODO: Auto-generated Javadoc
/**
 * The JFrame which acts as the main interface for when the user wishes to load a file.
 */
public class LoadFrame extends JFrame {

	/**
	 * Instantiates a new load frame.
	 *
	 * @param data the data
	 * @param eventList the event list
	 * @param studentList the student list
	 */
	public LoadFrame(JPanel data, List<Event> eventList, List<Student> studentList) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		this.setSize(450,150);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.add(mainPanel);

		JPanel settingsTitlePanel = new JPanel();
		JLabel settingsTitle = new JLabel("Load from File");
		settingsTitlePanel.add(settingsTitle);
		mainPanel.add(settingsTitlePanel, Component.CENTER_ALIGNMENT);

		JPanel namePanel = new JPanel();
		JLabel name = new JLabel("File Name(.txt): ");
		namePanel.add(name);
		JTextField nameBar = new JTextField(20);
		namePanel.add(nameBar);
		mainPanel.add(namePanel,Component.CENTER_ALIGNMENT);

		JPanel errorPanel = new JPanel();
		errorPanel.setPreferredSize(new Dimension(300,30));
		JLabel error = new JLabel("");
		errorPanel.add(error);
		mainPanel.add(errorPanel);

		JPanel confirmPanel = new JPanel();
		JButton confirm = new JButton("Confirm");
		this.getRootPane().setDefaultButton(confirm);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				error.setText("");
				if (nameBar.getText().isEmpty())
					error.setText("Invalid txt file name.");
				else {
					String fileName = nameBar.getText();
					if(!fileName.endsWith(".txt"))
						fileName += ".txt";
					try {
						FileReader fr;	
						fr = new FileReader(fileName);
						BufferedReader br = new BufferedReader(fr);

						int num = Integer.parseInt(br.readLine());
						for (int i = 0; i < num; i++) {
							try {
								Event event = new Event(br.readLine(),br.readLine(), br.readLine(),br.readLine(),Integer.parseInt(br.readLine()));
								eventList.add(event);
							}
							catch (InputMismatchException|NullPointerException|NumberFormatException e1){
								e1.printStackTrace();
							}

						}
						if (studentList != null) {
							
								num = Integer.parseInt(br.readLine());
								for (int i = 0; i < num; i++) {
									try {
									String name = br.readLine();
									int grade = Integer.parseInt(br.readLine());
									String input = br.readLine();
									boolean priority;
									if (input.equals("T"))
										priority = true;
									else priority = false;
									String email = br.readLine();
									String partner = br.readLine();
									input = br.readLine();
									boolean payment;
									if (input.equals("T"))
										payment = true;
									else payment = false;
									int day = Integer.parseInt(br.readLine());
									int month = Integer.parseInt(br.readLine());
									int year = Integer.parseInt(br.readLine());
									String choice1 = br.readLine();
									String choice2 = br.readLine();
									String choice3 = br.readLine();
									Event c1 = null;
									Event c2 = null;
									Event c3 = null;
									for (int j = 0; j < eventList.size(); j++) {
										if (eventList.get(i).getName().equals(choice1))
											c1 = eventList.get(i);
										if (eventList.get(i).getName().equals(choice2))
											c2 = eventList.get(i);
										if (eventList.get(i).getName().equals(choice3))
											c3 = eventList.get(i);
									}
									if (c1 == null)
										c1 = Event.NULL_EVENT;
									if (c2 == null)
										c2 = Event.NULL_EVENT;
									if (c3 == null)
										c3 = Event.NULL_EVENT;
									if (partner.equals(null))
										partner = "";
									if (email.equals(null))
										email = "";
									studentList.add(new Student(name, grade, priority, partner, email,
											 payment,  day,  month,  year,  c1,  c2,  c3));
									}
									catch (InputMismatchException|NullPointerException|NumberFormatException e1){
										e1.printStackTrace();
									}
								}
							
						}
					br.close();
					JOptionPane.showMessageDialog(new JFrame(),"Load complete, select \"View All\" to see new Objects");
					closeFrame();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
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
 * Close frame.
 */
public void closeFrame() {
	this.setVisible(false);
}
}
