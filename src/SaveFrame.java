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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.imageio.ImageIO;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

// TODO: Auto-generated Javadoc
/**
 * Creates a JFrame which acts as the interface for the user to save a panel's details..
 */
public class SaveFrame extends JFrame {

	/**
	 * Instantiates a new save frame, works for the Student and the Event module.
	 *
	 * @param data the data
	 * @param eventList the event list
	 * @param studentList the student list
	 */
	public SaveFrame(JPanel data, List<Event> eventList, List<Student> studentList) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		this.setSize(450,150);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.add(mainPanel);

		JPanel settingsTitlePanel = new JPanel();
		JLabel settingsTitle = new JLabel("Save from File");
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
						File file = new File(fileName);
						if (!file.exists()) {
							file.createNewFile();
						}
						FileWriter fw;	
						fw = new FileWriter(file.getAbsoluteFile());
						BufferedWriter bw = new BufferedWriter(fw);

						bw.write(String.valueOf(eventList.size()));
						for (int i = 0; i < eventList.size(); i++) {
							bw.newLine();
							bw.write(eventList.get(i).getName());
							bw.newLine();
							bw.write(eventList.get(i).getAcronym());
							bw.newLine();
							bw.write(eventList.get(i).getCluster());
							bw.newLine();
							bw.write(eventList.get(i).getType());
							bw.newLine();
							bw.write(String.valueOf(eventList.get(i).getFlightSize()));
						}
						if (studentList != null) {
							bw.newLine();
							bw.write(String.valueOf(studentList.size()));
							for (int i = 0; i < studentList.size(); i++) {
								bw.newLine();
								bw.write(studentList.get(i).getName());
								bw.newLine();
								bw.write(String.valueOf(studentList.get(i).getGrade()));
								bw.newLine();
								if (studentList.get(i).getPriorityStatus())
								bw.write("T");
								else 
									bw.write("F");
								bw.newLine();
								bw.write(studentList.get(i).getPartnerName());
								bw.newLine();
								bw.write(studentList.get(i).getEmail());
								bw.newLine();
								if (studentList.get(i).getPaymentStatus())
									bw.write("T");
									else 
										bw.write("F");
								bw.newLine();
								bw.write(String.valueOf(studentList.get(i).getDay()));
								bw.newLine();
								bw.write(String.valueOf(studentList.get(i).getMonth()));
								bw.newLine();
								bw.write(String.valueOf(studentList.get(i).getYear()));
								bw.newLine();
								bw.write(studentList.get(i).getChoice1().getName());
								bw.newLine();
								bw.write(studentList.get(i).getChoice2().getName());
								bw.newLine();
								bw.write(studentList.get(i).getChoice3().getName());
							}
						}
						bw.close();
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
