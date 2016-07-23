import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.Component;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Main menu bar for the flight module, allow the user to change the view and sort the objects.
 */
public class FlightMenuBar extends JMenuBar{

	/** The flight list. */
	private List<Flight> flightList;
	
	/** The data. */
	private JPanel data;
	
	/** The view item all. */
	private static JCheckBoxMenuItem viewItemAll;
	
	/** The view item finance. */
	private static JCheckBoxMenuItem viewItemFinance;
	
	/** The view item hospitality. */
	private static JCheckBoxMenuItem viewItemHospitality;
	
	/** The view item marketing. */
	private static JCheckBoxMenuItem viewItemMarketing;
	
	/** The view item business admin. */
	private static JCheckBoxMenuItem viewItemBusinessAdmin;
	
	/** The view item cluster none. */
	private static JCheckBoxMenuItem viewItemClusterNone;
	
	/** The view item individual. */
	private static JCheckBoxMenuItem viewItemIndividual;
	
	/** The view item team. */
	private static JCheckBoxMenuItem viewItemTeam;
	
	/** The view item principles. */
	private static JCheckBoxMenuItem viewItemPrinciples;
	
	/** The view item written. */
	private static JCheckBoxMenuItem viewItemWritten;
	
	/** The view item other. */
	private static JCheckBoxMenuItem viewItemOther;
	
	/** The view item type none. */
	private static JCheckBoxMenuItem viewItemTypeNone;

	/**
	 * Instantiates a new flight menu bar.
	 *
	 * @param flightList the flight list
	 * @param data the data
	 * @param currentViewList the current view list
	 * @param studentList the student list
	 */
	public FlightMenuBar(List<Flight> flightList, JPanel data, List<Flight> currentViewList, List<Student> studentList){
		this.data = data;
		this.flightList = flightList;

		JMenu sortMenu = new JMenu("<html><body><table width='300'><td align='center'>"
				+ "Sort by</td></table></body></html>");
		ButtonGroup sort = new ButtonGroup();

		JRadioButtonMenuItem sortItemNone = new JRadioButtonMenuItem("None");
		sortItemNone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){

			}
		});
		sortItemNone.setSelected(true);
		sort.add(sortItemNone);
		sortMenu.add(sortItemNone);

		JRadioButtonMenuItem sortItemName = new JRadioButtonMenuItem("Event Name");
		sortItemName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Collections.sort(currentViewList, Flight.NameComparator);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		sortMenu.add(sortItemName);
		sort.add(sortItemName);

		JRadioButtonMenuItem sortItemCluster = new JRadioButtonMenuItem("Event Cluster");
		sortItemCluster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Collections.sort(currentViewList, Flight.ClusterComparator);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		sortMenu.add(sortItemCluster);
		sort.add(sortItemCluster);

		JRadioButtonMenuItem sortItemType = new JRadioButtonMenuItem("Event Type");
		sortItemType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Collections.sort(currentViewList, Flight.TypeComparator);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		sortMenu.add(sortItemType);
		sort.add(sortItemType);

		this.add(sortMenu);

		JMenu viewMenu = new JMenu("<html><body><table width='300'><td align='center'>"
				+ "View</td></table></body></html>");
		JMenu clusterMenu = new JMenu("Event Cluster");
		JMenu typeMenu = new JMenu("Event Type");

		viewItemAll = new JCheckBoxMenuItem("All");
		viewItemFinance = new JCheckBoxMenuItem("Finance");
		viewItemMarketing = new JCheckBoxMenuItem("Marketing");
		viewItemHospitality = new JCheckBoxMenuItem("Hospitality");
		viewItemBusinessAdmin = new JCheckBoxMenuItem("Business Adminsitration");
		viewItemClusterNone = new JCheckBoxMenuItem("None");
		viewItemIndividual = new JCheckBoxMenuItem("Individual");
		viewItemTeam = new JCheckBoxMenuItem("Team");
		viewItemWritten = new JCheckBoxMenuItem("Written/Presentation");
		viewItemPrinciples = new JCheckBoxMenuItem("Principles");
		viewItemOther = new JCheckBoxMenuItem("Other");
		viewItemTypeNone = new JCheckBoxMenuItem("None");


		viewItemAll.setSelected(true);
		viewItemAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemMarketing.setSelected(false);
				viewItemHospitality.setSelected(false);
				viewItemMarketing.setSelected(false);
				viewItemBusinessAdmin.setSelected(false);
				viewItemClusterNone.setSelected(false);
				viewItemIndividual.setSelected(false);
				viewItemTeam.setSelected(false);
				viewItemWritten.setSelected(false);
				viewItemPrinciples.setSelected(false);
				viewItemOther.setSelected(false);
				viewItemClusterNone.setSelected(false);

				updateView(flightList, currentViewList);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		viewMenu.add(viewItemAll);

		//View: Marketing
		viewItemFinance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(flightList, currentViewList);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		clusterMenu.add(viewItemFinance);

		//View: Marketing
		viewItemMarketing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(flightList, currentViewList);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		clusterMenu.add(viewItemMarketing);

		viewItemHospitality.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(flightList, currentViewList);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		clusterMenu.add(viewItemHospitality);

		viewItemMarketing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(flightList, currentViewList);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		clusterMenu.add(viewItemMarketing);

		viewItemBusinessAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(flightList, currentViewList);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		clusterMenu.add(viewItemBusinessAdmin);

		viewItemClusterNone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(flightList, currentViewList);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		clusterMenu.add(viewItemClusterNone);
		viewMenu.add(clusterMenu);

		viewItemIndividual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(flightList, currentViewList);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		typeMenu.add(viewItemIndividual);

		viewItemTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(flightList, currentViewList);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		typeMenu.add(viewItemTeam);

		viewItemPrinciples.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(flightList, currentViewList);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		typeMenu.add(viewItemPrinciples);

		viewItemWritten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(flightList, currentViewList);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		typeMenu.add(viewItemWritten);

		viewItemOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(flightList, currentViewList);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		typeMenu.add(viewItemOther);

		viewItemTypeNone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(flightList, currentViewList);
				resetView(flightList, data, currentViewList, studentList);
			}
		});
		typeMenu.add(viewItemTypeNone);
		viewMenu.add(typeMenu);
		this.add(viewMenu);

		JLabel search = new JLabel("<html><body><table width='55'><td align='right'>"
				+ "Search: </td></table></body></html>");
		this.add(search);

		JTextField searchBar = new JTextField(20);
		searchBar.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				int count = 0;
				while (count < currentViewList.size()) {
					if(!currentViewList.get(count).getEvent().getName().toLowerCase().contains(searchBar.getText().toLowerCase())
							&& !currentViewList.get(count).toString().toLowerCase().contains(searchBar.getText().toLowerCase()))
						currentViewList.remove(count);
					else count++;
				}
				resetView(flightList, data, currentViewList, studentList);
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateView(flightList, currentViewList);
				int count = 0;
				while (count < currentViewList.size()) {
					if(!currentViewList.get(count).getEvent().getName().toLowerCase().contains(searchBar.getText().toLowerCase())
							&& !currentViewList.get(count).toString().toLowerCase().contains(searchBar.getText().toLowerCase()))
						currentViewList.remove(count);
					else count++;
				}
				resetView(flightList, data, currentViewList, studentList);
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// N/A

			}
		});	
		this.add(searchBar);
	}

	/**
	 * Update view.
	 *
	 * @param flightList the flight list
	 * @param currentViewList the current view list
	 */
	private static void updateView(List<Flight> flightList, List<Flight> currentViewList) {
		currentViewList.clear();
		for (int i = 0; i < flightList.size(); i++)
			currentViewList.add(flightList.get(i));
		if (!viewItemFinance.isSelected() && !viewItemHospitality.isSelected() && !viewItemMarketing.isSelected()
				&& !viewItemBusinessAdmin.isSelected() && !viewItemClusterNone.isSelected() && !viewItemIndividual.isSelected()
				&& !viewItemTeam.isSelected() && !viewItemPrinciples.isSelected() && !viewItemWritten.isSelected()
				&& !viewItemOther.isSelected() && !viewItemTypeNone.isSelected() && !viewItemAll.isSelected())
			currentViewList.clear();
		if (!viewItemAll.isSelected()) {
			int count = 0;
			while (count < currentViewList.size()) {
				boolean removed = false;
				if (!(viewItemFinance.isSelected() && viewItemHospitality.isSelected()
						&& viewItemMarketing.isSelected() && viewItemBusinessAdmin.isSelected() && 
						viewItemClusterNone.isSelected()) && 
						!(!viewItemFinance.isSelected() && !viewItemHospitality.isSelected()
								&& !viewItemMarketing.isSelected() && !viewItemBusinessAdmin.isSelected() && 
								!viewItemClusterNone.isSelected())) {
					if (!viewItemFinance.isSelected())
						if (currentViewList.get(count).getEvent().getCluster().equals("Finance")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemHospitality.isSelected())
						if (currentViewList.get(count).getEvent().getCluster().equals("Hospitality and Tourism")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemMarketing.isSelected())
						if (currentViewList.get(count).getEvent().getCluster().equals("Marketing")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemBusinessAdmin.isSelected())
						if (currentViewList.get(count).getEvent().getCluster().equals("Business Administration")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemClusterNone.isSelected())
						if (currentViewList.get(count).getEvent().getCluster().equals("None")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
				}
				if (!(viewItemIndividual.isSelected() && viewItemTeam.isSelected()
						&& viewItemPrinciples.isSelected() && viewItemWritten.isSelected() && 
						viewItemOther.isSelected() && viewItemTypeNone.isSelected()) && 
						!(!viewItemIndividual.isSelected() && !viewItemTeam.isSelected()
								&& !viewItemPrinciples.isSelected() && !viewItemWritten.isSelected() && 
								!viewItemOther.isSelected() && !viewItemTypeNone.isSelected())) {
					if (!viewItemIndividual.isSelected())
						if (currentViewList.get(count).getEvent().getType().equals("Individual")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemTeam.isSelected())
						if (currentViewList.get(count).getEvent().getType().equals("Team")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemPrinciples.isSelected())
						if (currentViewList.get(count).getEvent().getType().equals("Principles")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemWritten.isSelected())
						if (currentViewList.get(count).getEvent().getType().equals("Written")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemOther.isSelected())
						if (currentViewList.get(count).getEvent().getType().equals("Other")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemTypeNone.isSelected())
						if (currentViewList.get(count).getEvent().getType().equals("None")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
				}
				if (!removed)
					count++;
			}
		}
	}

	/**
	 * Reset view.
	 *
	 * @param flightList the flight list
	 * @param data the data
	 * @param currentViewList the current view list
	 * @param studentList the student list
	 */
	public static void resetView(List<Flight> flightList, JPanel data, List<Flight> currentViewList, List<Student> studentList){
		data.removeAll();
		data.repaint();
		data.revalidate();
		for (int i = 0; i < currentViewList.size(); i++)
			data.add(new FlightDetailDataBar(currentViewList.get(i), flightList, data, currentViewList, studentList));
		data.repaint();
		data.revalidate();
	}
}
