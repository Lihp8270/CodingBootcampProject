package util;

/**
 * Used by the PerformanceSelector to store and set column widths
 * @author JS
 *
 */
public class SelectorColumn {
	private String label;
	private int width;
	
	public SelectorColumn(String label) {
		this.label = label;
		this.width = label.length();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setMinWidth(int newWidth) {
		
		setWidth(Math.max(width, newWidth+1));
	}
}
