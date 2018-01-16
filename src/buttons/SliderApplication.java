package buttons;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JSlider;

import drawpanel.DrawPanel;

public class SliderApplication {

	private JPanel sliderPanel, transparencyPanel;
	private JSlider brushThicknessSlider, transparencySlider;

	public SliderApplication() {
	}

	public JPanel make_slider_panel(DrawPanel paint, Color backgroundColor, boolean value) {

		Slider sliderObject = new Slider(paint);

		if (value == true) {

			// create thickness slider
			sliderPanel = new JPanel() {

				@Override
				public Dimension getPreferredSize() {
					return new Dimension(55, 50);
				}

			};

			sliderPanel.setBackground(backgroundColor);
			sliderPanel.setOpaque(true);

			brushThicknessSlider = new JSlider();
			brushThicknessSlider = sliderObject.make_size_slider(paint);

			sliderPanel.add(brushThicknessSlider);

			return sliderPanel;

		} else {

			transparencyPanel = new JPanel() {

				@Override
				public Dimension getPreferredSize() {
					return new Dimension(55, 50);
				}

			};

			transparencyPanel.setBackground(backgroundColor);
			transparencyPanel.setOpaque(true);

			transparencySlider = new JSlider();
			transparencySlider = sliderObject.make_transparency_slider(paint);

			transparencyPanel.add(transparencySlider);

			return transparencyPanel;

		}
	}
}
