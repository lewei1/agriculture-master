package org.zcy.agriculture.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
//import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码生成和读的工具类
 *
 */
public class QrCodeCreateUtil {
	public static Logger logger = LogManager.getLogger(QrCodeCreateUtil.class);
	public static final int qrCodeSize = 500;// 默认二维码图片大小

	/**
	 * 生成包含字符串信息的二维码图片
	 * 
	 * @param outputStream 文件输出流路径
	 * @param content      二维码携带信息
	 * @param qrCodeSize   二维码图片大小
	 * @param imageFormat  二维码的格式
	 * @throws WriterException
	 * @throws IOException
	 */
	private static boolean createQrCode(OutputStream outputStream, String content, int qrCodeSize, String imageFormat) throws WriterException, IOException {
		// 设置二维码纠错级别ＭＡＰ
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L); // 矫错级别
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		// 创建比特矩阵(位矩阵)的QR码编码的字符串
		BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
		// 使BufferedImage勾画QRCode (matrixWidth 是行二维码像素点)
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();
		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// 使用比特矩阵画并保存图像
		graphics.setColor(Color.BLACK);
		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRect(i, j, 1, 1);
				}
			}
		}
		return ImageIO.write(image, imageFormat, outputStream);
	}

	/**
	 * 创建二维码
	 * 
	 * @param fileUrl
	 * @param address
	 */
	public static void createQrCode(String fileUrl, String address) {
		try {
			String dirUrl = fileUrl.substring(0, fileUrl.lastIndexOf("/"));
			File dir = new File(dirUrl);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			createQrCode(new FileOutputStream(new File(fileUrl)), address, qrCodeSize, "JPEG");
		} catch (Exception e) {
			logger.error("创建二维码异常！", e);
		}
	}

	/**
	 * 读二维码并输出携带的信息
	 */
//    public static void readQrCode(InputStream inputStream) throws IOException{  
//        //从输入流中获取字符串信息
//        BufferedImage image = ImageIO.read(inputStream);  
//        //将图像转换为二进制位图源
//        LuminanceSource source = new BufferedImageLuminanceSource(image);  
//        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));  
//        QRCodeReader reader = new QRCodeReader();  
//        Result result = null ;  
//        try {
//         result = reader.decode(bitmap);  
//        } catch (ReaderException e) {
//            e.printStackTrace();  
//        }
//        System.out.println(result.getText());  
//    }
	/**
	 * 测试代码
	 * 
	 * @throws WriterException
	 */
	public static void main(String[] args) throws IOException, WriterException {

		createQrCode("d:\\qrcode.jpg", "http://172.16.6.158:8090/api/system/tbSaleTemplet/getTempletInfo?saleTempletId=1");
//        readQrCode(new FileInputStream(new File("d:\\qrcode.jpg")));  
	}

}