import java.io.IOException;

import javax.swing.JFrame;

public class Main {
	static int width = 2000, height = 1000;
	public static void main(String[] args) {
		JFrame frame = new JFrame("MyImage");
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageCanvas canvas = new ImageCanvas(frame.getWidth(), frame.getHeight());
		frame.add(canvas);
		
		Thinning thinning = new Thinning(new ZhangSuenAlgorithm());
		BinaryImage image = null;
		
		try {
			image = new BinaryImage("bigImage.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		canvas.addImage(image);

		BinaryImage thinnedImage = null;
		try {
			thinnedImage = new BinaryImage(thinning.thin(image));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		canvas.addImage(thinnedImage);
		
		frame.setVisible(true);
	}
	
	
}