package main;

import buttons.ButtonApplication;
import buttons.CurrentColor;
import buttons.SliderApplication;
import drawpanel.DrawPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Application extends JFrame {

	private static final long serialVersionUID = 1L;

	public int action = 1;
	private DrawPanel paint;
	private ButtonApplication button;
	private SliderApplication slider;
	private CurrentColor current;
	private JPanel buttonPanelWest, buttonPanelSouth, buttonPanelNorth;
	private JPanel bpw1, bpw2, bpw3, bpw4;
	private JPanel colorPanel;
	private Color backgroundColor = Color.LIGHT_GRAY;
	private MainMenu menu;

	public Application() {

	}

	public void setup_application() {

		this.setTitle("Paint Application");
		this.setSize(1350, 710);
		this.setBackground(backgroundColor);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());

		set_draw_panel();
		set_west();
		set_south();
		set_north();

		this.setVisible(true);

	}

	public void set_draw_panel() {

		paint = new DrawPanel(1, backgroundColor);
		this.add(paint, BorderLayout.CENTER);

	}

	public void set_west() {

		button = new ButtonApplication();
		slider = new SliderApplication();
		current = new CurrentColor();

		buttonPanelWest = new JPanel(new GridLayout(2, 2));

		bpw1 = button.make_utility_button_panel(paint, true, this);
		bpw2 = button.make_utility_button_panel(paint, false, this);
		bpw3 = slider.make_slider_panel(paint, backgroundColor, true);
		bpw4 = slider.make_slider_panel(paint, backgroundColor, false);

		buttonPanelWest.add(bpw1);
		buttonPanelWest.add(bpw2);
		buttonPanelWest.add(bpw3);
		buttonPanelWest.add(bpw4);

		current.change_currentcolor(paint, bpw1, bpw2);

		this.add(buttonPanelWest, BorderLayout.WEST);

	}

	public void set_south() {

		buttonPanelSouth = new JPanel(new GridLayout(2, 5));

		for (int i = 0; i < 18; i++) {
			colorPanel = button.make_color_button_panel(0, 1, paint, this);
			buttonPanelSouth.add(colorPanel);
		}

		this.add(buttonPanelSouth, BorderLayout.SOUTH);
	}

	public JPanel get_bpw1() {
		return bpw1;
	}

	public JPanel get_bpw2() {
		return bpw2;
	}

	public void set_north() {

		menu = new MainMenu(paint);
		this.setJMenuBar(menu.set_mainmenu(this));

	}

}
