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
 * The Menu bar for the Event Module.
 */
public class EventMenuBar extends JMenuBar{

	/** The event list. */
	private List<Event> eventList;
	
	/** The data. */
	private JPanel data;
	
	/** The JCheckbox view item all. */
	private static JCheckBoxMenuItem viewItemAll;
	
	/** The JCheckbox view item finance. */
	private static JCheckBoxMenuItem viewItemFinance;
	
	/** The JCheckbox view item hospitality. */
	private static JCheckBoxMenuItem viewItemHospitality;
	
	/** The JCheckbox view item marketing. */
	private static JCheckBoxMenuItem viewItemMarketing;
	
	/** The JCheckbox view item business admin. */
	private static JCheckBoxMenuItem viewItemBusinessAdmin;
	
	/** The JCheckbox view item cluster none. */
	private static JCheckBoxMenuItem viewItemClusterNone;
	
	/** The JCheckbox view item individual. */
	private static JCheckBoxMenuItem viewItemIndividual;
	
	/** The JCheckbox view item team. */
	private static JCheckBoxMenuItem viewItemTeam;
	
	/** The JCheckbox view item principles. */
	private static JCheckBoxMenuItem viewItemPrinciples;
	
	/** The JCheckbox view item written. */
	private static JCheckBoxMenuItem viewItemWritten;
	
	/** The JCheckbox view item other. */
	private static JCheckBoxMenuItem viewItemOther;
	
	/** The JCheckbox view item type none. */
	private static JCheckBoxMenuItem viewItemTypeNone;

	/**
	 * Instantiates a new event menu bar.
	 *
	 * @param eventList the event list
	 * @param data the data
	 * @param currentViewList the current view list
	 */
	public EventMenuBar(List<Event> eventList, JPanel data, List<Event> currentViewList){
		this.data = data;
		this.eventList = eventList;
		JMenu actionMenu = new JMenu("<html><body><table width='200'><td align='center'>"
				+ "Actions</td></table></body></html>");

		JMenuItem actionItemDelete = new JMenuItem("Delete Selected");
		actionItemDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int count = 0;
				while (count < eventList.size()) {
					if (eventList.get(count).getSelected()) {
						Event a = eventList.get(count);
						eventList.remove(a);
						currentViewList.remove(a);
					}
					else count++;
				}
				resetView(eventList, data, currentViewList);
			}
		});
		actionMenu.add(actionItemDelete);

		JMenuItem actionItemChangeFlightSize = new JMenuItem("Change Flight Size");
		actionItemChangeFlightSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				EventChangeFlightSizeFrame frame = new EventChangeFlightSizeFrame(eventList, data, currentViewList);
			}
		});
		actionMenu.add(actionItemChangeFlightSize);
		this.add(actionMenu);

		JMenu sortMenu = new JMenu("<html><body><table width='200'><td align='center'>"
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
				Collections.sort(currentViewList, Event.NameComparator);
				resetView(eventList, data, currentViewList);
			}
		});
		sortMenu.add(sortItemName);
		sort.add(sortItemName);

		JRadioButtonMenuItem sortItemCluster = new JRadioButtonMenuItem("Event Cluster");
		sortItemCluster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Collections.sort(currentViewList, Event.ClusterComparator);
				resetView(eventList, data, currentViewList);
			}
		});
		sortMenu.add(sortItemCluster);
		sort.add(sortItemCluster);

		JRadioButtonMenuItem sortItemType = new JRadioButtonMenuItem("Event Type");
		sortItemType.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Collections.sort(currentViewList, Event.TypeComparator);
				resetView(eventList, data, currentViewList);
			}
		});
		sortMenu.add(sortItemType);
		sort.add(sortItemType);

		this.add(sortMenu);

		JMenu viewMenu = new JMenu("<html><body><table width='200'><td align='center'>"
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

				updateView(eventList, currentViewList);
				resetView(eventList, data, currentViewList);
			}
		});
		viewMenu.add(viewItemAll);

		//View: Marketing
		viewItemFinance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(eventList, currentViewList);
				resetView(eventList, data, currentViewList);
			}
		});
		clusterMenu.add(viewItemFinance);

		//View: Marketing
		viewItemMarketing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(eventList, currentViewList);
				resetView(eventList, data, currentViewList);
			}
		});
		clusterMenu.add(viewItemMarketing);

		viewItemHospitality.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(eventList, currentViewList);
				resetView(eventList, data, currentViewList);
			}
		});
		clusterMenu.add(viewItemHospitality);

		viewItemMarketing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(eventList, currentViewList);
				resetView(eventList, data, currentViewList);
			}
		});
		clusterMenu.add(viewItemMarketing);

		viewItemBusinessAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(eventList, currentViewList);
				resetView(eventList, data, currentViewList);
			}
		});
		clusterMenu.add(viewItemBusinessAdmin);

		viewItemClusterNone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(eventList, currentViewList);
				resetView(eventList, data, currentViewList);
			}
		});
		clusterMenu.add(viewItemClusterNone);
		viewMenu.add(clusterMenu);

		viewItemIndividual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(eventList, currentViewList);
				resetView(eventList, data, currentViewList);
			}
		});
		typeMenu.add(viewItemIndividual);

		viewItemTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(eventList, currentViewList);
				resetView(eventList, data, currentViewList);
			}
		});
		typeMenu.add(viewItemTeam);

		viewItemPrinciples.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(eventList, currentViewList);
				resetView(eventList, data, currentViewList);
			}
		});
		typeMenu.add(viewItemPrinciples);

		viewItemWritten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(eventList, currentViewList);
				resetView(eventList, data, currentViewList);
			}
		});
		typeMenu.add(viewItemWritten);

		viewItemOther.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(eventList, currentViewList);
				resetView(eventList, data, currentViewList);
			}
		});
		typeMenu.add(viewItemOther);

		viewItemTypeNone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				viewItemAll.setSelected(false);
				updateView(eventList, currentViewList);
				resetView(eventList, data, currentViewList);
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
					if(!currentViewList.get(count).getName().toLowerCase().contains(searchBar.getText().toLowerCase()))
						currentViewList.remove(count);
					else count++;
				}
				resetView(eventList, data, currentViewList);
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				updateView(eventList, currentViewList);
				int count = 0;
				while (count < currentViewList.size()) {
					if(!currentViewList.get(count).getName().toLowerCase().contains(searchBar.getText().toLowerCase()))
						currentViewList.remove(count);
					else count++;
				}
				resetView(eventList, data, currentViewList);
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// N/A

			}
		});	
		this.add(searchBar);
	}

	/**
	 * Updates the current view according to user selections.
	 *
	 * @param eventList the event list
	 * @param currentViewList the current view list
	 */
	private static void updateView(List<Event> eventList, List<Event> currentViewList) {
		currentViewList.clear();
		for (int i = 0; i < eventList.size(); i++)
			currentViewList.add(eventList.get(i));
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
						if (currentViewList.get(count).getCluster().equals("Finance")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemHospitality.isSelected())
						if (currentViewList.get(count).getCluster().equals("Hospitality and Tourism")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemMarketing.isSelected())
						if (currentViewList.get(count).getCluster().equals("Marketing")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemBusinessAdmin.isSelected())
						if (currentViewList.get(count).getCluster().equals("Business Administration")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemClusterNone.isSelected())
						if (currentViewList.get(count).getCluster().equals("None")) {
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
						if (currentViewList.get(count).getType().equals("Individual")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemTeam.isSelected())
						if (currentViewList.get(count).getType().equals("Team")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemPrinciples.isSelected())
						if (currentViewList.get(count).getType().equals("Principles")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemWritten.isSelected())
						if (currentViewList.get(count).getType().equals("Written")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemOther.isSelected())
						if (currentViewList.get(count).getType().equals("Other")) {
							currentViewList.remove(currentViewList.get(count));
							removed = true;
							if (count >= currentViewList.size())
								break;
						}
					if (!viewItemTypeNone.isSelected())
						if (currentViewList.get(count).getType().equals("None")) {
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
	 * Resets the view and changes it to currently selected settings.
	 *
	 * @param eventList the event list
	 * @param data the data
	 * @param currentViewList the current view list
	 */
	public static void resetView(List<Event> eventList, JPanel data, List<Event> currentViewList){
		data.removeAll();
		data.repaint();
		data.revalidate();
		for (int i = 0; i < currentViewList.size(); i++)
			data.add(new EventDetailDataBar(currentViewList.get(i), eventList, data, currentViewList));
		data.repaint();
		data.revalidate();
	}
}
