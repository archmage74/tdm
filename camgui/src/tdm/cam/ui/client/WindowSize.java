package tdm.cam.ui.client;

import com.google.gwt.user.client.Window;

public class WindowSize {

	private static WindowSize windowSize;
	
	private int height;
	private int width;
	
	public static WindowSize getInstance() {
		if (windowSize == null) {
			windowSize = new WindowSize();
		}
		return windowSize;
	}

	private WindowSize() {
		height = Window.getClientHeight();
		width = Window.getClientWidth();
	}
	
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
}
