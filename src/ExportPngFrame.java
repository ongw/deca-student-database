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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
 * The frame used to get the file name from the user in which to export the panel.
 */
public class ExportPngFrame extends JFrame {

	/**
	 * Instantiates a new export png frame.
	 *
	 * @param data the data
	 */
	public ExportPngFrame(JPanel data) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.PAGE_AXIS));
		this.setSize(450,150);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
		this.add(mainPanel);

		JPanel settingsTitlePanel = new JPanel();
		JLabel settingsTitle = new JLabel("Export to image");
		settingsTitlePanel.add(settingsTitle);
		mainPanel.add(settingsTitlePanel, Component.CENTER_ALIGNMENT);

		JPanel namePanel = new JPanel();
		JLabel name = new JLabel("File Name(.png): ");
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
					error.setText("Invalid png file name.");
				else {
					String fileName = nameBar.getText();
					if(!fileName.endsWith(".png"))
						fileName += ".png";
					BufferedImage image = new BufferedImage(data.getWidth(),
							data.getHeight(), BufferedImage.TYPE_INT_RGB);
					data.paint(image.getGraphics());
					Graphics newImage = image.createGraphics();
					data.paint(newImage);
					newImage.dispose();
					try {
						ImageIO.write(image,"png",new File(nameBar.getText()));
						closeFrame();
					} catch (IOException e1) {
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
