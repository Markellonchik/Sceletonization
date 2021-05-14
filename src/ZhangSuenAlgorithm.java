
public class ZhangSuenAlgorithm implements PrecalcAlgorithm {
	private int cnt = 0;
	private long lastExecutionTime = 0;
	
	private boolean[] precalcCheck1;
	private boolean[] precalcCheck2;
	
	public void precalc() {
		lastExecutionTime = System.nanoTime();

		precalcCheck1 = new boolean[1 << 8];
		precalcCheck2 = new boolean[1 << 8];
		for(int i = 0; i < (1 << 8); ++i) {
			byte p2 = (byte) (i & 1);
			byte p3 = (byte) ((i & 2) >> 1);
			byte p4 = (byte) ((i & 4) >> 2);
			byte p5 = (byte) ((i & 8) >> 3);
			byte p6 = (byte) ((i & 16) >> 4);
			byte p7 = (byte) ((i & 32) >> 5);
			byte p8 = (byte) ((i & 64) >> 6);
			byte p9 = (byte) ((i & 128) >> 7);
			precalcCheck1[i] = check1(p2, p3, p4, p5, p6, p7, p8, p9);
			precalcCheck2[i] = check2(p2, p3, p4, p5, p6, p7, p8, p9);
		}
		lastExecutionTime = System.nanoTime() - lastExecutionTime;
	}
	
	
	
	@Override
	public byte[][] thin(byte[][] image) {
		return precalcThin(image);
		
		/*lastExecutionTime = System.nanoTime();

		int width = image.length;
		int height = image[0].length;
		byte[][] copyImage = new byte[width][height];
		
		for(int i = 0; i < width; ++i) {
			for(int j = 0; j < height; ++j) {
				copyImage[i][j] = image[i][j];
			}
		}
		image = copyImage;
		copyImage = new byte[width][height];
		
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
		
		lastExecutionTime = System.nanoTime() - lastExecutionTime;
		return image;*/
	}
	
	public byte[][] precalcThin(byte[][] image) {
		if(precalcCheck1 == null) {
			precalc();
		}
		lastExecutionTime = System.nanoTime();

		int width = image.length;
		int height = image[0].length;
		byte[][] copyImage = new byte[width][height];
		
		for(int i = 0; i < width; ++i) {
			for(int j = 0; j < height; ++j) {
				copyImage[i][j] = image[i][j];
			}
		}
		image = copyImage;
		copyImage = new byte[width][height];

		while(true) {
			for(int i = 0; i < width; ++i) {
				precalcSolve1(i, image, copyImage);
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
				precalcSolve2(i, image, copyImage);
			}
			for(int i = 0; i < width; ++i) {
				for(int j = 0; j < height; ++j) {
					image[i][j] = copyImage[i][j];
				}
			}
			if(cnt == 0) break;
			cnt = 0;
		}
		
		lastExecutionTime = System.nanoTime() - lastExecutionTime;
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
	
	private void precalcSolve1(int row, byte[][] image, byte[][] copy) {
		int n = image.length;
		int m = image[row].length;
		for(int j = 0; j < image[row].length; ++j) {
			if(image[row][j] == 1 && 
					precalcCheck1
					[(row - 1 >= 0 ? image[row - 1][j] : 0) +
					(row - 1 >= 0 && j + 1 < m ? image[row - 1][j + 1] << 1 : 0) +
					(j + 1 < m ? image[row][j + 1] << 2 : 0) +
					(row + 1 < n && j + 1 < m ? image[row + 1][j + 1] << 3 : 0) +
					(row + 1 < n ? image[row + 1][j] << 4 : 0) +
					(row + 1 < n && j - 1 >= 0 ? image[row + 1][j - 1] << 5 : 0) +
					(j - 1 >= 0 ? image[row][j - 1] << 6 : 0) +
					(row - 1 >= 0 && j - 1 >= 0 ? image[row - 1][j - 1] << 7 : 0)
					]) {
				cnt++;
				copy[row][j] = 0;
			} else copy[row][j] = image[row][j];
		}
	}
	
	private void precalcSolve2(int row, byte[][] image, byte[][] copy) {
		int n = image.length;
		int m = image[row].length;
		for(int j = 0; j < image[row].length; ++j) {
			if(image[row][j] == 1 && 
					precalcCheck2
					[(row - 1 >= 0 ? image[row - 1][j] : 0) +
					(row - 1 >= 0 && j + 1 < m ? image[row - 1][j + 1] << 1 : 0) +
					(j + 1 < m ? image[row][j + 1] << 2 : 0) +
					(row + 1 < n && j + 1 < m ? image[row + 1][j + 1] << 3 : 0) +
					(row + 1 < n ? image[row + 1][j] << 4 : 0) +
					(row + 1 < n && j - 1 >= 0 ? image[row + 1][j - 1] << 5 : 0) +
					(j - 1 >= 0 ? image[row][j - 1] << 6 : 0) +
					(row - 1 >= 0 && j - 1 >= 0 ? image[row - 1][j - 1] << 7 : 0)
					]) {
				cnt++;
				copy[row][j] = 0;
			} else copy[row][j] = image[row][j];
		}
	}
	
	private boolean check(byte p2, byte p3, byte p4, byte p5, byte p6, byte p7, byte p8, byte p9) {
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
	
	private boolean check1(byte p2, byte p3, byte p4, byte p5, byte p6, byte p7, byte p8, byte p9) {
		if(!check(p2, p3, p4, p5, p6, p7, p8, p9)) return false;
		if(p2 == 1 && p4 == 1 && p6 == 1) return false;
		if(p4 == 1 && p6 == 1 && p8 == 1) return false;
		return true;
	}
	private boolean check2(byte p2, byte p3, byte p4, byte p5, byte p6, byte p7, byte p8, byte p9) {
		if(!check(p2, p3, p4, p5, p6, p7, p8, p9)) return false;
		if(p2 == 1 && p4 == 1 && p8 == 1) return false;
		if(p2 == 1 && p6 == 1 && p8 == 1) return false;
		return true;
	}

	@Override
	public long lastExecutionTime() {
		return lastExecutionTime;
	}
}
