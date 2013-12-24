/*
 * The MIT License
 * 
 * Copyright (c) 2008-, Daniel Schulz 
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.deesceha.rq.samples;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

import de.deesceha.ipo.core.IpoOperator;
import de.deesceha.ipo.core.IpoSource;
import de.deesceha.ipo.core.annotation.IpoBean;
import de.deesceha.ipo.core.annotation.IpoOp;
import de.deesceha.ipo.core.annotation.IpoParam;
import de.deesceha.ipo.processing.PlanExecutionContext;

/**
 * @author ds
 * 
 *         dot matrix display parameters
 * 
 *         dot dw - width dh - height dmx - x margin dmy - y margin col_a -
 *         color of activated dot col_b - color of margin area col_d - color of
 *         deactivated dot
 * 
 *         dot matrix cols - number of columns rows - number of rows
 * 
 *         display
 * 
 *         content - string specifying active and inactive dots with X and -.
 *         One sequence starting at (1,1) row wise to (#cols, #rows).
 */
@IpoOp(maxSrc = 0, minSrc = 0, srcParams = {})
@IpoBean(author = "nobody", customizable = true, description = "DotMatrixDisplay", name = "DotMatrixOp", releaseDate = "2013-11-27", version = "1")
public class DotMatrixOp implements IpoOperator {

	/*
	 * dw - width dh - height dmx - x margin dmy - y margin col_a - color of
	 * activated dot col_b - color of margin area col_d - color of deactivated
	 * dot
	 */
	private int dw, dh, dmx, dmy, rows, cols;
	private Color cola, colb, cold;
	private String content;

	public BufferedImage process() {

		// image width : cols * (dw + 2*dmx)
		// image height: rows * (dh + 2*dmy)

		BufferedImage bi = new BufferedImage(cols * (dw + 2 * dmx), rows
				* (dh + 2 * dmy), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = (Graphics2D) bi.getGraphics();

		// fill whole image with background color
		g2d.setPaint(getColb());
		g2d.fillRect(0, 0, bi.getWidth(), bi.getHeight());

		char dot = 'x';

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < cols; x++) {

				int contentIdx = y * cols + x;

				if (content.length() > contentIdx) {

					int rectX = x * (dw + 2 * dmx) + dmx;
					int rectY = y * (dh + 2 * dmy) + dmy;

					if (content.charAt(contentIdx) == dot) {
						g2d.setPaint(getCola());
						// g2d.fillRect(rectX, rectY, dw, dh);
					} else {
						g2d.setPaint(getCold());
					}

					g2d.fillRect(rectX, rectY, dw, dh);
				}

			}
		}

		return bi;
	}

	public void setCtx(PlanExecutionContext ctx) {
		// TODO Auto-generated method stub

	}

	public void setSources(List<IpoSource> sources) {
		// TODO Auto-generated method stub

	}

	public int getDw() {
		return dw;
	}

	public int getDh() {
		return dh;
	}

	public int getDmx() {
		return dmx;
	}

	public int getDmy() {
		return dmy;
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public Color getCola() {
		return cola;
	}

	public Color getColb() {
		return colb;
	}

	public Color getCold() {
		return cold;
	}

	public String getContent() {
		return content;
	}

	@IpoParam(position = 10, defaultValue = "20")
	public void setDw(int dw) {
		this.dw = dw;
	}

	@IpoParam(position = 20, defaultValue = "20")
	public void setDh(int dh) {
		this.dh = dh;
	}

	@IpoParam(position = 30, defaultValue = "2")
	public void setDmx(int dmx) {
		this.dmx = dmx;
	}

	@IpoParam(position = 40, defaultValue = "2")
	public void setDmy(int dmy) {
		this.dmy = dmy;
	}

	@IpoParam(position = 50, defaultValue = "5")
	public void setRows(int rows) {
		this.rows = rows;
	}

	@IpoParam(position = 60, defaultValue = "4")
	public void setCols(int cols) {
		this.cols = cols;
	}

	@IpoParam(position = 70, defaultValue = "black")
	public void setCola(Color cola) {
		this.cola = cola;
	}

	@IpoParam(position = 80, defaultValue = "yellow")
	public void setColb(Color colb) {
		this.colb = colb;
	}

	@IpoParam(position = 90, defaultValue = "#00ffff22")
	public void setCold(Color cold) {
		this.cold = cold;
	}

	@IpoParam(position = 100, defaultValue = "xxxx" + "x---" + "xxx-" + "x---"
			+ "xxxx")
	public void setContent(String content) {
		this.content = content;
	}
}
