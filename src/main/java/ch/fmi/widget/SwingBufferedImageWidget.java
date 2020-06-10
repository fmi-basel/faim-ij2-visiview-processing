/*-
 * #%L
 * ImageJ plugins for processing VisiView datasets
 * %%
 * Copyright (C) 2019 - 2020 Friedrich Miescher Institute for Biomedical
 * 			Research, Basel
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */

package ch.fmi.widget;

import java.awt.image.BufferedImage;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.scijava.plugin.Plugin;
import org.scijava.ui.swing.widget.SwingInputWidget;
import org.scijava.widget.InputWidget;
import org.scijava.widget.WidgetModel;

@Plugin(type = InputWidget.class)
public class SwingBufferedImageWidget extends SwingInputWidget<BufferedImage>
	implements BufferedImageWidget<JPanel>
{

	private BufferedImage image;
	private JLabel preview;

	@Override
	public boolean supports(final WidgetModel model) {
		return super.supports(model) && model.isType(BufferedImage.class);
	}

	@Override
	public void set(final WidgetModel model) {
		super.set(model);

		getComponent().setLayout(new BoxLayout(getComponent(), BoxLayout.X_AXIS));

		preview = new JLabel();
		setToolTip(preview);
		getComponent().add(preview);

		refreshWidget();
	}

	@Override
	public BufferedImage getValue() {
		// Not intended to store any value
		return image;
	}

	@Override
	protected void doRefresh() {
		image = (BufferedImage) get().getValue();
		final ImageIcon icon = new ImageIcon(image);
		preview.setIcon(icon);
	}

}
