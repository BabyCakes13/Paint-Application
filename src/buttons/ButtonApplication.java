package buttons;

import main.Application;

import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import drawpanel.DrawPanel;

public class ButtonApplication extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel buttonPanel, utilityPanel;
	private JButton rectangle, oval, line, brush, eraser, clear, colorchooser, currentcolor, fill, previous;
	public Graphics2D graphics;

	public JPanel make_color_button_panel(int start, int end, DrawPanel paint, Application app) {

		buttonPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(55, 50);
			}
		};

		buttonPanel.setBackground(paint.get_backgroundColor());
		buttonPanel.setOpaque(true);

		for (int i = start; i <= end; i++) {

			Button buttonObject = new Button();
			JButton newButton = buttonObject.set_color_button(paint, app);
			buttonPanel.add(newButton);

		}

		buttonPanel.setBorder(null);

		return buttonPanel;

	}

	public JPanel make_utility_button_panel(DrawPanel paint, boolean shapes, Application app) {

		utilityPanel = new JPanel() {

			private static final long serialVersionUID = 1L;

			@Override
			public Dimension getPreferredSize() {
				return new Dimension(70, 60);
			}
		};

		utilityPanel.setBackground(paint.get_backgroundColor());
		utilityPanel.setOpaque(true);
		utilityPanel.setBorder(null);

		if (shapes == true)
			handle_utility_button(paint, true, app);
		else
			handle_utility_button(paint, false, app);

		return utilityPanel;

	}

	public void handle_utility_button(DrawPanel paint, boolean shape, Application app) {

		Button buttonObject = new Button();

		if (shape == true) {

			rectangle = buttonObject.set_rectangle_button(paint);
			utilityPanel.add(rectangle);

			oval = buttonObject.set_oval_button(paint);
			utilityPanel.add(oval);

			line = buttonObject.set_line_button(paint);
			utilityPanel.add(line);

			brush = buttonObject.set_brush_button(paint);
			utilityPanel.add(brush);

			previous = buttonObject.set_previous_button(paint);
			utilityPanel.add(previous);

		} else {

			colorchooser = buttonObject.set_color_chooser_button(paint, app);
			utilityPanel.add(colorchooser);

			clear = buttonObject.set_clear_button(paint, app);
			utilityPanel.add(clear);

			eraser = buttonObject.set_eraser_button(paint, app);
			utilityPanel.add(eraser);

			fill = buttonObject.set_fill_button(paint);
			utilityPanel.add(fill);

			currentcolor = buttonObject.set_currentcolor_button(paint);
			utilityPanel.add(currentcolor);

		}

	}

}
