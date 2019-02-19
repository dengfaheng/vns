package representation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import model.Cluster;
import model.Point;
import model.Solution;
import model.point.PointMultiDimension;

public class ImageProcessor {

	private BufferedImage datasetImage;

	private OutputStream csvOutputStream;
	private int steps;

	final ResourceBundle rb = ResourceBundle.getBundle("config\\config");
    String datasetDir = rb.getString("datasetDir");
    String datasetName = rb.getString("datasetImage");
    String pixelsDataset = rb.getString("pixelsDataset");

    String test = datasetDir + datasetName;
	public ImageProcessor(int steps) {
		this.steps = steps;
		try {
			this.datasetImage = ImageIO.read(new File(Paths.get(datasetDir + datasetName).toAbsolutePath().toString()));
			this.csvOutputStream = new FileOutputStream(new File(Paths.get(datasetDir + pixelsDataset).toAbsolutePath().toString()));
			this.writeToCSV(this.readDatasetImage(), csvOutputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeImage(Solution solution, String imageName) {
		List<List<Pixel>> clusters = new ArrayList<>();
		for (Cluster cluster : solution.getClusters()) {
			List<Pixel> pixels = new ArrayList<>();
			for (Point point : cluster.getPoints()) {
				PointMultiDimension p = (PointMultiDimension) point;
				int x = new Double(p.getAttribute(0)).intValue();
				int y = new Double(p.getAttribute(1)).intValue();
				pixels.add(new Pixel(x, y));
			}
			clusters.add(pixels);
		}
		try {
			this.writeImage(clusters, imageName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeImage(List<List<Pixel>> clusters, String imageName) throws IOException {
		File file = new File(datasetDir, imageName + ".bmp");
		BufferedImage solutionImage = new BufferedImage(datasetImage.getWidth(), datasetImage.getHeight(),
				BufferedImage.TYPE_INT_RGB);

		int r = (255 << 16) & 0x00FF0000;
		int g = (255 << 8) & 0x0000FF00;
		int b = 255 & 0x000000FF;
		int white = 0xFF000000 | r | g | b;

		for (int i = 0; i < solutionImage.getWidth(); i++) {
			for (int j = 0; j < solutionImage.getHeight(); j++) {
				solutionImage.setRGB(i, j, white);
			}
		}

		
		
		List<int[]> colors = new ArrayList<>();
		for(int i = 0; i < 9; i++) {
			colors.add(new int[]{0, 0, 0});
		}
		int c = 255;
		int colorIndexInList = 0; 
		while(c > 0) {
			for(int i = 0; i < 3; i++, colorIndexInList++) {
				colors.get(colorIndexInList)[i] = c;
			}
			c -= 85;
		}
		
		colorIndexInList = 0;
		for (List<Pixel> pixels : clusters) {
			int red = colors.get(colorIndexInList)[0];
			int green = colors.get(colorIndexInList)[1];
			int blue = colors.get(colorIndexInList)[2];
			colorIndexInList++;

			int redB = (red << 16) & 0x00FF0000;
			int greenB = (green << 8) & 0x0000FF00;
			int blueB = blue & 0x000000FF;

			int colorB = 0xFF000000 | redB | greenB | blueB;
			for (Pixel pixel : pixels) {
				solutionImage.setRGB(pixel.getX(), pixel.getY(), colorB);
			}
		}

		ImageIO.write(solutionImage, "bmp", file);
	}

	private List<Pixel> readDatasetImage() throws IOException {
		List<Pixel> pixels = new ArrayList<Pixel>();
		// Getting pixel color by position x and y
		for (int i = 0; i < datasetImage.getWidth(); i += steps) {
			for (int j = 0; j < datasetImage.getHeight(); j += steps) {
				int clr = datasetImage.getRGB(i, j);
				int red = (clr & 0x00ff0000) >> 16;
				int green = (clr & 0x0000ff00) >> 8;
				int blue = clr & 0x000000ff;
				if (red < 240 || green < 240 || blue < 240) {
					pixels.add(new Pixel(i, j, red, green, blue));
				}
			}
		}
		return pixels;
	}

	private void writeToCSV(List<Pixel> pixels, OutputStream outputStream) throws IOException {
		StringBuilder stringBuilder = new StringBuilder();
		long seed = System.nanoTime();
		Collections.shuffle(pixels, new Random(seed));

		for (Pixel pixel : pixels) {
			stringBuilder.append(pixel.getX()).append(",").append(pixel.getY()).append("\n");
		}
		outputStream.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
	}
}
