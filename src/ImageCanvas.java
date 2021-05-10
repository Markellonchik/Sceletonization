import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ImageCanvas extends Canvas {
	ArrayList<BufferedImage> images;
	int width, height;
	
	public ImageCanvas(int width, int height) {
		this.width = width;
		this.height = height;
		images = new ArrayList<>();
	}
	
	public void addImage(BufferedImage image) {
		images.add(image);
	}
	public void paint(Graphics g) {
		int x = 0;
		int y = 0;
		int maxHeight = 0;
		g.setColor(Color.BLACK);
		for(BufferedImage image : images) {
			maxHeight = Math.max(maxHeight, image.getHeight());
			g.drawImage(image, x, y, null);
			g.drawRect(x, y, image.getWidth(), image.getHeight());
			
			x += image.getWidth();
			if(x > width) {
				y += maxHeight;
				maxHeight = 0;
				x = 0;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
