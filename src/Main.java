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
		
		ZhangSuenAlgorithm zsAlg = new ZhangSuenAlgorithm();
		zsAlg.precalc();
		System.out.println("Zhang-Suen algorithm precalc: " + zsAlg.lastExecutionTime() / 1000000.0);
		
		Thinning thinning = new Thinning(zsAlg);
		BinaryImage image = null;
		
		try {
			image = new BinaryImage("mushroom.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		canvas.addImage(image);

		System.out.println(image.getWidth() + "x" + image.getHeight());
		
		BinaryImage thinnedImage = null;
		try {
			thinnedImage = new BinaryImage(thinning.thin(image));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(thinning.algorithm.lastExecutionTime() / 1000000.0);
		
		canvas.addImage(thinnedImage);
		
		frame.setVisible(true);
	}
	
	
}
