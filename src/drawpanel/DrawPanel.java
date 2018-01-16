package drawpanel;

import javax.imageio.ImageIO;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

import java.awt.AWTException;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class DrawPanel extends JComponent implements MouseListener, MouseMotionListener {

	private int action, transparency = 255;
	private static final long serialVersionUID = 1L;
	private Color backgroundColor, currentColor, previousColor;
	private Graphics2D graphics;
	private Image image;
	private Stack<BufferedImage> undo, redo;
	private static int startX = -1, startY = -1, stopX = -1, stopY = -1;

	public DrawPanel(int action, Color backgroundColor) {

		this.backgroundColor = backgroundColor;
		this.action = action;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setBackground(backgroundColor);

	}

	public void set_default() {

		graphics.setPaint(Color.WHITE);
		graphics.fillRect(0, 0, getSize().width, getSize().height);
		repaint();
		graphics.setPaint(Color.BLACK);
		currentColor = Color.BLACK;

	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if (action == 1) {

			startX = e.getX();
			startY = e.getY();
			if (graphics != null) {
				// if there is no button in box button checked
				graphics.drawLine(stopX, stopY, startX, startY);
				// refresh draw area
				repaint();
				stopX = startX;
				stopY = startY;

			}

		}

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		// System.out.println("mouse clicked.");
		if (action == 5) {

			// System.out.println("action 5");
			try {

				Robot robot = new Robot();
				currentColor = robot.getPixelColor(e.getX(), e.getY());
				graphics.setPaint(currentColor);

			} catch (AWTException r) {

				r.printStackTrace();

			}
			System.out.println(currentColor);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (action == 1) {

			stopX = e.getX();
			stopY = e.getY();

		} else {

			startX = e.getX();
			startY = e.getY();
			stopX = startX;
			stopY = startY;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {

		if (action != 1) {

			if (action == 2) {

				graphics.drawLine(startX, startY, e.getX(), e.getY());
				repaint();

			} else if (action == 3) {

				stopX = e.getX();
				stopY = e.getY();

				int width = Math.max(startX, stopX) - Math.min(startX, stopX);
				int height = Math.max(startY, stopY) - Math.min(startY, stopY);

				if ((startX < stopX) && (startY < stopY)) {

					graphics.drawRect(startX, startY, width, height);

				} else if ((startX > stopX) && (startY < stopY)) {

					graphics.drawRect(stopX, startY, width, height);

				} else if ((startX < stopX) && (startY > stopY)) {

					graphics.drawRect(startX, stopY, width, height);

				} else if ((startX > stopX) && (startY > stopY)) {

					graphics.drawRect(stopX, stopY, width, height);

				}

				repaint();

			} else if (action == 4) {

				stopX = e.getX();
				stopY = e.getY();

				int width = Math.max(startX, stopX) - Math.min(startX, stopX);
				int height = Math.max(startY, stopY) - Math.min(startY, stopY);

				if ((startX < stopX) && (startY < stopY)) {

					graphics.drawOval(startX, startY, width, height);

				} else if ((startX > stopX) && (startY < stopY)) {

					graphics.drawOval(stopX, startY, width, height);

				} else if ((startX < stopX) && (startY > stopY)) {

					graphics.drawOval(startX, stopY, width, height);

				} else if ((startX > stopX) && (startY > stopY)) {

					graphics.drawOval(stopX, stopY, width, height);

				}

				repaint();

			}

		}

	}

	public void paintComponent(Graphics g) {

		if (image == null) {

			image = createImage(getSize().width, getSize().height);
			graphics = (Graphics2D) image.getGraphics();
			graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			set_default();

		}

		g.drawImage(image, 0, 0, null);

	}

	public Graphics2D get_graphics() {

		return graphics;

	}

	public void set_paint(int r, int g, int b, int t) {

		previousColor = currentColor;
		this.transparency = t;
		Color newColor = new Color(r, g, b, transparency);
		graphics.setPaint(newColor);
		currentColor = newColor;

	}

	public void button_color_chooser() {

		previousColor = currentColor;
		Color color = Color.BLACK;
		Color newColor = JColorChooser.showDialog(null, "Pick your color", color);
		graphics.setPaint(newColor);
		currentColor = newColor;

	}

	public void button_clear() {

		previousColor = currentColor;
		graphics.setPaint(Color.WHITE);
		graphics.fillRect(0, 0, getSize().width, getSize().height);
		graphics.setPaint(Color.BLACK);
		currentColor = Color.BLACK;
		repaint();

	}

	public void button_fill(Color color) {

		previousColor = currentColor;
		graphics.setPaint(color);
		graphics.fillRect(0, 0, getSize().width, getSize().height);
		graphics.setPaint(Color.BLACK);
		currentColor = Color.BLACK;
		repaint();

	}

	public void button_eraser() {

		previousColor = currentColor;
		graphics.setPaint(Color.WHITE);
		currentColor = Color.WHITE;

	}

	public void button_shape(int shape) {

		this.action = shape;

	}

	public void set_thickness(int value) {

		graphics.setStroke(new BasicStroke(value));

	}

	public void set_transparency(int t) {

		this.transparency = t;
		Color newColor = new Color(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), transparency);
		graphics.setPaint(newColor);
		currentColor = newColor;

	}

	public Color get_backgroundColor() {
		return backgroundColor;
	}

	public int get_transparency() {
		return transparency;
	}

	public Color get_currentColor() {
		return currentColor;
	}

	public Color get_previousColor() {
		return previousColor;
	}

	public Image get_image() {

		return image;

	}

	public void save(String path, boolean newSuccess) {

		if (image != null) {

			if (newSuccess == true) {

				try {

					BufferedImage newImage = (BufferedImage) image;
					ImageIO.write(newImage, "png", new File(path));

				} catch (IOException e) {

					e.printStackTrace();

				}

			} else {

				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("C:/Users/maria"));
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {

					try {
						BufferedImage newImage = (BufferedImage) image;

						String selectedFile = chooser.getSelectedFile().getAbsolutePath();
						ImageIO.write(newImage, "png", new File(selectedFile));
					}

					catch (IOException e) {
						e.printStackTrace();
					}

				}

			}
		}
	}

	public void load(String path) throws IOException {

		final BufferedImage newImage = ImageIO.read(new File(path));

		int originalWidth = newImage.getWidth();
		int originalHeight = newImage.getHeight();
		int canvasWidth = this.getWidth();
		int canvasHeight = this.getHeight();
		int newWidth = originalWidth;
		int newHeight = originalHeight;

		if (originalWidth > canvasWidth) {

			newWidth = canvasWidth;
			newHeight = (newWidth * originalHeight) / originalWidth;

		}

		if (newHeight > canvasHeight) {

			newHeight = canvasHeight;
			newWidth = (newHeight * originalWidth) / originalHeight;

		}

		super.paintComponent(graphics);
		graphics.drawImage(newImage, 0, 0, newWidth, newHeight, null);

	}

}
