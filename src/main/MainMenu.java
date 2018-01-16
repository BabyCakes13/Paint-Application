package main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileFilter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import drawpanel.DrawPanel;

public class MainMenu {

	private JMenu File, Help, Options;
	private JMenuBar Menu;
	private JMenuItem Quit, New, Open, Save, Helpp, Undo, Redo, Clear;
	private String newPath;
	private boolean newSuccess = false;
	DrawPanel paint;

	public MainMenu(DrawPanel paint) {
		this.paint = paint;
	}

	public JMenuBar set_mainmenu(Application app) {

		Menu = new JMenuBar();

		Options = new JMenu("Options");

		File = new JMenu("File");
		File.setToolTipText("File - Can access New, Open, Save, Quit");

		Help = new JMenu("Help");
		Help.setToolTipText("Help - Can access Help");

		New = new JMenuItem("New");
		New.setToolTipText("New - Create a new image");
		New.addActionListener(new MenuListener());

		Open = new JMenuItem("Open");
		Open.setToolTipText("Open - Select an image from the computer and load it");
		Open.addActionListener(new MenuListener());

		Save = new JMenuItem("Save");
		Save.setToolTipText("Save - Write the full path and full name of the picture with the correct file extension");
		Save.addActionListener(new MenuListener());

		Quit = new JMenuItem("Quit");
		Quit.setToolTipText("Quit - Quit the application");
		Quit.addActionListener(new MenuListener());

		Helpp = new JMenuItem("Help");
		Helpp.setToolTipText("Help - show information about the application");
		Helpp.addActionListener(new MenuListener());

		Undo = new JMenuItem("Undo");
		Undo.addActionListener(new MenuListener());

		Redo = new JMenuItem("Redo");
		Redo.addActionListener(new MenuListener());

		File.add(New);
		File.add(Open);
		File.add(Save);
		File.add(Quit);
		Help.add(Helpp);
		Options.add(Undo);
		Options.add(Redo);

		Menu.add(File);
		Menu.add(Options);
		Menu.add(Help);

		return Menu;

	}

	private class MenuListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == New) {

				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Choose the directory where the file will be located and name the file");
				chooser.addChoosableFileFilter(new FileNameExtensionFilter("Image files", "jpg", "png", "tif", "jpeg", "bmp"));
				chooser.setAcceptAllFileFilterUsed(false);
				if (chooser.showOpenDialog(Open) == JFileChooser.APPROVE_OPTION) {
					newPath = chooser.getSelectedFile().getAbsolutePath();
					newSuccess = true;
					System.out.println(newPath);
				}

			} else if (e.getSource() == Open) {

				try {

					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new java.io.File("C:/Users/maria"));
					chooser.setDialogTitle("Choose a file to open");
					chooser.addChoosableFileFilter(new FileNameExtensionFilter("Image files", "jpg", "png", "tif", "jpeg", "bmp"));
					chooser.setAcceptAllFileFilterUsed(false);
					if (chooser.showOpenDialog(Open) == JFileChooser.APPROVE_OPTION) {
						paint.load(chooser.getSelectedFile().getAbsolutePath());
					}

				} catch (IOException e1) {

					e1.printStackTrace();
				}

				paint.repaint();

			} else if (e.getSource() == Save) {

				paint.save(newPath, newSuccess);
				newSuccess = false;

			} else if (e.getSource() == Quit) {

				System.exit(0);

			} else if (e.getSource() == Helpp) {

				JPanel helpPanel = new JPanel(new BorderLayout());
				JFrame helpWindow = new JFrame("Help");
				JLabel helpLabel = new JLabel();

				helpWindow.setSize(700, 800);
				helpWindow.setLocationRelativeTo(null);
				helpWindow.setResizable(false);

				helpLabel.setText(
						"<html>" + "<h2> HOW TO USE THE APPLICATION </h2>" + "<h3> HOW TO USE THE MENU </h3>" + "<p> The menu consists of two main objects - File and Help. </p>"
								+ "<h4> FILE </h4>" + "<p> File has four options: </p>" + "<ul> <li>New</li> <li>Open</li> <li>Save</li> <li>Quit</li> </ul>"
								+ "<h4>New</h4> <p>When opening a new file, you must save the file and later update it.</p>"
								+ "<h4>Open</h4> <p>When opening a file, you must choose the image and then open it.</p>"
								+ "<h4>Save</h4> <p>When saving a file, you must choose the directory where the file is found, then write the full path, including the name and the file extension of the image, then enter.</p>"
								+ "<h4>Quit</h4> <p>Quits the application.</p>" + "<h4>HELP</h4> <p>You know what this does.</p>" + "<h3>HOW TO USE THE BUTTONS</h3>"
								+ "<p>Each button has a tool tip which explains what it does</p>" + "</html>");

				helpPanel.setBorder(BorderFactory.createEmptyBorder());
				helpPanel.add(helpLabel, BorderLayout.NORTH);

				helpWindow.add(helpPanel);
				helpWindow.setVisible(true);

			}

		}
	}
}
