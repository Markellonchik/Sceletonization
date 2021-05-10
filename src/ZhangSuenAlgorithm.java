
public class ZhangSuenAlgorithm implements Algorithm {
	int cnt = 0;
	@Override
	public byte[][] thin(byte[][] image) {
		int width = image.length;
		int height = image[0].length;
		byte[][] copyImage = new byte[width][height];
		int cnt1 = 0;
		
		while(true) {
			for(int i = 0; i < width; ++i) {
				solve1(i, image, copyImage);
			}
			for(int i = 0; i < width; ++i) {
				for(int j = 0; j < height; ++j) {
					image[i][j] = copyImage[i][j];
				}
			}
			if(cnt == 0) break;
			cnt = 0;
			//Second iteration
			for(int i = 0; i < width; ++i) {
				solve2(i, image, copyImage);
			}
			for(int i = 0; i < width; ++i) {
				for(int j = 0; j < height; ++j) {
					image[i][j] = copyImage[i][j];
				}
			}
			if(cnt == 0) break;
			cnt = 0;
		}
		
		return image;
	}
	
	private void solve1(int row, byte[][] image, byte[][] copy) {
		int n = image.length;
		int m = image[row].length;
		for(int j = 0; j < image[row].length; ++j) {
			if (image[row][j] == 1 && 
					check1(
					row - 1 >= 0 ? image[row - 1][j] : 0,
					row - 1 >= 0 && j + 1 < m ? image[row - 1][j + 1] : 0,
					j + 1 < m ? image[row][j + 1] : 0,
					row + 1 < n && j + 1 < m ? image[row + 1][j + 1] : 0,
					row + 1 < n ? image[row + 1][j] : 0,
					row + 1 < n && j - 1 >= 0 ? image[row + 1][j - 1] : 0,
					j - 1 >= 0 ? image[row][j - 1] : 0,
					row - 1 >= 0 && j - 1 >= 0 ? image[row - 1][j - 1] : 0
					)) {
				cnt++;
				copy[row][j] = 0;
			} else copy[row][j] = image[row][j];
		}
	}
	
	private void solve2(int row, byte[][] image, byte[][] copy) {
		int n = image.length;
		int m = image[row].length;
		for(int j = 0; j < image[row].length; ++j) {
			if (image[row][j] == 1 && 
					check2(
					row - 1 >= 0 ? image[row - 1][j] : 0,
					row - 1 >= 0 && j + 1 < m ? image[row - 1][j + 1] : 0,
					j + 1 < m ? image[row][j + 1] : 0,
					row + 1 < n && j + 1 < m ? image[row + 1][j + 1] : 0,
					row + 1 < n ? image[row + 1][j] : 0,
					row + 1 < n && j - 1 >= 0 ? image[row + 1][j - 1] : 0,
					j - 1 >= 0 ? image[row][j - 1] : 0,
					row - 1 >= 0 && j - 1 >= 0 ? image[row - 1][j - 1] : 0
					)) {
				cnt++;
				copy[row][j] = 0;
			} else copy[row][j] = image[row][j];
		}
	}
	
	boolean check(byte p2, byte p3, byte p4, byte p5, byte p6, byte p7, byte p8, byte p9) {
		int b = p2 + p3 + p4 + p5 + p6 + p7 + p8 + p9;
		if(b < 2 || b > 6) return false;
		int a = 0;
		byte[] ar = {p2, p3, p4, p5, p6, p7, p8, p9, p2};
		for(int i = 0; i + 1 < ar.length; ++i) {
			if(ar[i] == 0 && ar[i + 1] == 1) a++;
		}
		if(a != 1) return false;
		return true;
	}
	
	boolean check1(byte p2, byte p3, byte p4, byte p5, byte p6, byte p7, byte p8, byte p9) {
		if(!check(p2, p3, p4, p5, p6, p7, p8, p9)) return false;
		if(p2 == 1 && p4 == 1 && p6 == 1) return false;
		if(p4 == 1 && p6 == 1 && p8 == 1) return false;
		return true;
	}
	boolean check2(byte p2, byte p3, byte p4, byte p5, byte p6, byte p7, byte p8, byte p9) {
		if(!check(p2, p3, p4, p5, p6, p7, p8, p9)) return false;
		if(p2 == 1 && p4 == 1 && p8 == 1) return false;
		if(p2 == 1 && p6 == 1 && p8 == 1) return false;
		return true;
	}
}
