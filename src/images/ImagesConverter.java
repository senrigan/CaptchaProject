package images;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class ImagesConverter {
	public static void main(String[] args) {
		try {
			byte[] extractBytes = ImagesConverter.extractBytes("D:\\Users\\Senrigan\\Documents\\desarrollo\\CaptchaProject\\captcha\\end-of-captcha.jpg");
			System.out.println(Arrays.toString(extractBytes));
			
			FileOutputStream fos = new FileOutputStream("D:\\Users\\Senrigan\\Documents\\desarrollo\\CaptchaProject\\captcha\\prueba.jpg");
			fos.write(extractBytes);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static  byte[] extractBytes (String ImageName) throws IOException {
		 // open image
		 File imgPath = new File(ImageName);
		 BufferedImage bufferedImage = ImageIO.read(imgPath);

		 // get DataBufferBytes from Raster
		 WritableRaster raster = bufferedImage .getRaster();
		 DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

		 return ( data.getData() );
		}

}
