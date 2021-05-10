import java.awt.image.Raster;

public class Thinning {
	Algorithm algorithm;
	
	public Thinning(Algorithm algo) {
		this.algorithm = algo;
	}
	
	public byte[][] thin(BinaryImage image) {
		return thin(image.getData());
	}
	public byte[][] thin(Raster raster) {
		byte[][] ar = new byte[raster.getWidth()][raster.getHeight()];
		for(int i = 0; i < raster.getWidth(); ++i) {
			for(int j = 0; j < raster.getHeight(); ++j) {
				if(raster.getPixel(i, j, (int[])null)[0] == 0) {
					ar[i][j] = 1;
				} else {
					ar[i][j] = 0;
				}
			}
		}
		return thin(ar);
	}
	
	public byte[][] thin(byte[][] image) {
		return algorithm.thin(image);
	}
	
}
