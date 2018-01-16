package buttons;

import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import drawpanel.DrawPanel;

public class Slider {

	int value;
	private JSlider brushThicknessSlider, transparencySlider;
	private int majorTickSpacing = 50;
	private int transparencyValue = 0;

	public Slider(DrawPanel paint) {
	}

	public JSlider make_size_slider(DrawPanel paint) {
		
		brushThicknessSlider = new JSlider(JSlider.VERTICAL, 0, 100, 0);
		brushThicknessSlider.setToolTipText("Brush size slider - change the size of the brush");
		brushThicknessSlider.setLabelTable(brushThicknessSlider.createStandardLabels(50));
		brushThicknessSlider.setPaintLabels(true);
		brushThicknessSlider.setMajorTickSpacing(majorTickSpacing);
		brushThicknessSlider.setPaintTicks(true);
		brushThicknessSlider.setBackground(paint.get_backgroundColor());
		brushThicknessSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent event) {

				value = brushThicknessSlider.getValue();
				paint.set_thickness(value);

			}

		});

		return brushThicknessSlider;
	}

	public JSlider make_transparency_slider(DrawPanel paint) {

		transparencySlider = new JSlider(JSlider.VERTICAL, 0, 255, 255);
		transparencySlider.setToolTipText("Transparency slider - set the transparency of the paint");
		transparencySlider.setLabelTable(transparencySlider.createStandardLabels(50));
		transparencySlider.setPaintLabels(true);
		transparencySlider.setMajorTickSpacing(majorTickSpacing);
		transparencySlider.setPaintTicks(true);
		transparencySlider.setBackground(paint.get_backgroundColor());
		transparencySlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent event) {

				if (event.getSource() == transparencySlider) {

					transparencyValue = transparencySlider.getValue();
					paint.set_transparency(transparencyValue);

				}

			}

		});

		return transparencySlider;
	}
}
