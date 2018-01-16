package buttons;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import drawpanel.DrawPanel;

public class CurrentColor {

	private JButton previousButton, currentButton;
	private static Color currentColor;
	private static Color previousColor;

	public void change_currentcolor(DrawPanel paint, JPanel buttons1, JPanel buttons2) {

		currentColor = paint.get_currentColor();
		
		Component[] components1 = buttons1.getComponents();
		Component[] components2 = buttons2.getComponents();

		previousButton = (JButton) components1[4];
		currentButton = (JButton) components2[4];
		
		previousButton.setBackground(previousColor);
		currentButton.setBackground(currentColor);

		previousColor = currentColor;

	}
	public static Color getCurrentColor() {
		return currentColor;
	}
	public static void setCurrentColor(Color currentColor) {
		CurrentColor.currentColor = currentColor;
	}
	public static Color getPreviousColor() {
		return previousColor;
	}
	public static void setPreviousColor(Color previousColor) {
		CurrentColor.previousColor = previousColor;
	}

}
