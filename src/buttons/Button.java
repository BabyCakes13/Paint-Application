package buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import drawpanel.DrawPanel;
import main.Application;

public class Button {

	Dimension colorButtonDimension = new Dimension(60, 40);
	Dimension utilityButtonDimension = new Dimension(60, 50);
	private int r = 0, g = 0, b = 0, t = 255;
	private Color buttonColor;
	private CurrentColor current;

	public Button() {

		buttonColor = Color.BLACK;
		current = new CurrentColor();

	}

	public JButton set_color_button(DrawPanel paint, Application app) {

		Random random = new Random();

		r = random.nextInt(255);
		g = random.nextInt(255);
		b = random.nextInt(255);

		buttonColor = new Color(r, g, b, t);
		JButton button = new JButton();
		button.setToolTipText("Color - set paint to this color");
		button.setMargin(null);
		button.setFocusPainted(false);
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.setBackground(buttonColor);
		button.setPreferredSize(colorButtonDimension);

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == button) {

					JButton newbutton = (JButton) e.getSource();

					r = newbutton.getBackground().getRed();
					g = newbutton.getBackground().getGreen();
					b = newbutton.getBackground().getBlue();
					t = paint.get_transparency();

					paint.set_paint(r, g, b, t);

					current.change_currentcolor(paint, app.get_bpw1(), app.get_bpw2());

				}

			}

		});
		return button;

	}

	public JButton set_color_chooser_button(DrawPanel paint, Application app) {

		ImageIcon icon = new ImageIcon(Button.class.getResource("colorchooser.png"));
		JButton button = new JButton(icon);
		button.setToolTipText("Color Chooser - choose any color you want");
		button.setMargin(null);
		button.setFocusPainted(false);
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.setPreferredSize(utilityButtonDimension);
		button.setBackground(paint.get_backgroundColor());

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == button) {

					paint.button_color_chooser();
					current.change_currentcolor(paint, app.get_bpw1(), app.get_bpw2());

				}

			}

		});

		return button;

	}

	public JButton set_clear_button(DrawPanel paint, Application app) {

		ImageIcon icon = new ImageIcon(Button.class.getResource("clear.png"));
		JButton button = new JButton(icon);
		button.setToolTipText("Clear - clears everything out from the canvas");
		button.setMargin(null);
		button.setFocusPainted(false);
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.setPreferredSize(utilityButtonDimension);
		button.setBackground(paint.get_backgroundColor());
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == button) {

					paint.button_clear();
					current.change_currentcolor(paint, app.get_bpw1(), app.get_bpw2());
					paint.button_shape(1);

				}

			}

		});

		return button;

	}

	public JButton set_eraser_button(DrawPanel paint, Application app) {

		ImageIcon icon = new ImageIcon(Button.class.getResource("eraser.png"));
		JButton button = new JButton(icon);
		button.setToolTipText("Eraser - erase from the canvas");
		button.setMargin(null);
		button.setFocusPainted(false);
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.setPreferredSize(utilityButtonDimension);
		button.setBackground(paint.get_backgroundColor());
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == button) {

					paint.button_eraser();
					current.change_currentcolor(paint, app.get_bpw1(), app.get_bpw2());
					paint.button_shape(1);

				}

			}

		});

		return button;

	}

	public JButton set_fill_button(DrawPanel paint) {

		ImageIcon icon = new ImageIcon(Button.class.getResource("fill.png"));
		JButton button = new JButton(icon);
		button.setToolTipText("Fill - set tool to fill the canvas with the selected color");
		button.setMargin(null);
		button.setFocusPainted(false);
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.setPreferredSize(utilityButtonDimension);
		button.setBackground(paint.get_backgroundColor());
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == button) {

					paint.button_fill(paint.get_currentColor());
					paint.button_shape(1);

				}

			}

		});

		return button;

	}

	public JButton set_rectangle_button(DrawPanel paint) {

		ImageIcon icon = new ImageIcon(Button.class.getResource("rectangle.png"));
		JButton button = new JButton(icon);
		button.setToolTipText("Rectangle - set tool to draw a rectangle on the canvas");
		button.setMargin(null);
		button.setFocusPainted(false);
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.setPreferredSize(utilityButtonDimension);
		button.setBackground(paint.get_backgroundColor());
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == button) {

					paint.button_shape(3);

				}

			}

		});

		return button;

	}

	public JButton set_oval_button(DrawPanel paint) {

		ImageIcon icon = new ImageIcon(Button.class.getResource("oval.png"));
		JButton button = new JButton(icon);
		button.setToolTipText("Oval - set tool to draw an oval to the canvas");
		button.setMargin(null);
		button.setFocusPainted(false);
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.setPreferredSize(utilityButtonDimension);
		button.setBackground(paint.get_backgroundColor());
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == button) {

					paint.button_shape(4);

				}

			}

		});

		return button;

	}

	public JButton set_line_button(DrawPanel paint) {

		ImageIcon icon = new ImageIcon(Button.class.getResource("line.png"));
		JButton button = new JButton(icon);
		button.setToolTipText("Line - set tool to draw a line on the canvas");
		button.setMargin(null);
		button.setFocusPainted(false);
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.setPreferredSize(utilityButtonDimension);
		button.setBackground(paint.get_backgroundColor());
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == button) {

					paint.button_shape(2);

				}

			}

		});

		return button;

	}

	public JButton set_brush_button(DrawPanel paint) {

		ImageIcon icon = new ImageIcon(Button.class.getResource("brush.png"));
		JButton button = new JButton(icon);
		button.setToolTipText("Brush - set tool to brush");
		button.setMargin(null);
		button.setFocusPainted(false);
		button.setBorder(new EmptyBorder(0, 0, 0, 0));
		button.setPreferredSize(utilityButtonDimension);
		button.setBackground(paint.get_backgroundColor());
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == button) {

					paint.button_shape(1);

				}

			}

		});

		return button;

	}

	public JButton set_currentcolor_button(DrawPanel paint) {

		JButton currentColorButton = new JButton();
		currentColorButton.setMargin(null);
		currentColorButton.setFocusPainted(false);
		currentColorButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		currentColorButton.setBackground(Color.GRAY);
		currentColorButton.setPreferredSize(colorButtonDimension);
		currentColorButton.setToolTipText("Current Color - shows the current color");
	
		return currentColorButton;
	}

	public JButton set_previous_button(DrawPanel paint) {

		JButton previousColorButton = new JButton();
		previousColorButton.setMargin(null);
		previousColorButton.setFocusPainted(false);
		previousColorButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		previousColorButton.setBackground(Color.GRAY);
		previousColorButton.setPreferredSize(colorButtonDimension);
		previousColorButton.setToolTipText("Previous Color - shows the previous color");
		
		return previousColorButton;

	}

}
