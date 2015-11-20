package swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

@SuppressWarnings("serial")
public class IWannaTakinToAMenuBar extends JFrame {
	// Create and add CheckButton as a menu item to one of the drop down
	// menu
	JCheckBoxMenuItem checkAction1 = new JCheckBoxMenuItem("Set/Reset check box");
	JCheckBoxMenuItem checkAction2 = new JCheckBoxMenuItem("Check Box 1");
	JCheckBoxMenuItem checkAction3 = new JCheckBoxMenuItem("Check Box 2");
	JCheckBoxMenuItem checkAction4 = new JCheckBoxMenuItem("Check Box 3");

	public IWannaTakinToAMenuBar() {

		setTitle("Menu Example");
		setSize(800, 600);

		// Creates a menubar for a JFrame
		JMenuBar menuBar = new JMenuBar();

		// Add the menubar to the frame
		setJMenuBar(menuBar);

		// Define and add two drop down menu to the menubar
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);

		// Create and add simple menu item to one of the drop down menu
		JMenuItem newAction = new JMenuItem("New");
		JMenuItem openAction = new JMenuItem("Open");
		JMenuItem exitAction = new JMenuItem("Exit");
		JMenuItem cutAction = new JMenuItem("Cut");
		JMenuItem copyAction = new JMenuItem("Copy");
		JMenuItem pasteAction = new JMenuItem("Paste");





		// Create and add Radio Buttons as simple menu items to one of the drop
		// down menu
		JRadioButtonMenuItem radioAction1 = new JRadioButtonMenuItem(
				"Radio Button1");
		JRadioButtonMenuItem radioAction2 = new JRadioButtonMenuItem(
				"Radio Button2");
		// Create a ButtonGroup and add both radio Button to it. Only one radio
		// button in a ButtonGroup can be selected at a time.
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioAction1);
		bg.add(radioAction2);
		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.addSeparator();
		fileMenu.add(checkAction1);
		fileMenu.addSeparator();
		fileMenu.add(checkAction2);
		fileMenu.add(checkAction3);
		fileMenu.add(checkAction4);
		fileMenu.addSeparator();
		fileMenu.add(exitAction);
		editMenu.add(cutAction);
		editMenu.add(copyAction);
		editMenu.add(pasteAction);
		editMenu.addSeparator();
		editMenu.add(radioAction1);
		editMenu.add(radioAction2);
		// Add a listener to the New menu item. actionPerformed() method will
		// invoked, if user triggred this menu item
		newAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You have clicked on the new action");
			}
		});

		openAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You have clicked on the open action");
			}
		});

		exitAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You have clicked on the exit action");
			}
		});

		cutAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You have clicked on the cut action");
			}
		});

		copyAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You have clicked on the copy action");
			}
		});

		pasteAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You have clicked on the paste action");
			}
		});

		radioAction1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You have clicked on the radio button 1");
			}
		});

		radioAction2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You have clicked on the radio button 2");
			}
		});




		checkAction1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You have checked the Set/Reset box");
				boolean reset = checkAction1.isSelected();
				checkAction2.setSelected(reset);
				checkAction3.setSelected(reset);
				checkAction4.setSelected(reset);
			}
		});
		checkAction2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You have checked the check box 1");
			}
		});

		checkAction3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You have checked the check box 2");
			}
		});

		checkAction4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("You have checked the check box 3");
			}
		});




	}

	public static void main(String[] args) {
		IWannaTakinToAMenuBar me = new IWannaTakinToAMenuBar();
		me.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		me.setVisible(true);
	}
}