package com.ruiec.web.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public abstract class ImageUtils {
	// 等比缩放空白部分使用白色填充
	public static void zoomByFill(File source, File dest, int destWidth, int destHeight) {
		try {
			int width = destWidth;// 目标宽度
			int height = destHeight;// 目标高度
			int srcWidth;// 图片原始宽度
			int srcHeight;// 图片原始高度
			int index_x;// 起始绘制x坐标
			int index_y;// 起始绘制y坐标

			BufferedImage sourceImage = ImageIO.read(source);
			BufferedImage destImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
			srcWidth = sourceImage.getWidth();
			srcHeight = sourceImage.getHeight();
			if (srcWidth * 1.0 / width > srcHeight * 1.0 / height) {
				srcHeight = (int) Math.round((width * 1.0 / srcWidth) * srcHeight);
				srcWidth = width;
				index_x = 0;
				//缩放后的源图片永远处于 目标图片大小的框内，所以源宽高永远小于等于目标宽高
				index_y = (height - srcHeight) / 2;//总是大于等于0
			} else {
				srcWidth = (int) Math.round((height * 1.0 / srcHeight) * srcWidth);
				srcHeight = height;
				index_x =(width - srcWidth) / 2;
				index_y = 0;
			}
			Image srcImage = sourceImage.getScaledInstance(srcWidth, srcHeight, Image.SCALE_SMOOTH);
			Graphics2D g = destImage.createGraphics();
			g.setBackground(Color.white);
			g.clearRect(0, 0, width, height);
			g.drawImage(srcImage, index_x, index_y, null);
			g.dispose();
			ImageIO.write(destImage, "JPEG", dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 等比缩放溢出部分剪裁掉
	public static void zoomByCrop(File source, File dest, int destWidth, int destHeight) {

		int width = destWidth;// 目标宽度
		int height = destHeight;// 目标高度
		int srcWidth;// 图片原始宽度
		int srcHeight;// 图片原始高度
		int index_x;// 起始绘制x坐标
		int index_y;// 起始绘制y坐标

		ByteArrayOutputStream bo = null;
		ImageInputStream iis = null;

		try {
			BufferedImage sourceImage = ImageIO.read(source);
			//BufferedImage destImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

			srcWidth = sourceImage.getWidth();
			srcHeight = sourceImage.getHeight();

			if (srcWidth * 1.0 / width > srcHeight * 1.0 / height) {
				srcWidth = (int) Math.round((height * 1.0 / srcHeight) * srcWidth);
				srcHeight = height;
				index_x = Math.abs((srcWidth - width) / 2);
				index_y = 0;
			} else {
				srcHeight = (int) Math.round((width * 1.0 / srcWidth) * srcHeight);
				srcWidth = width;
				index_x = 0;
				index_y = Math.abs((srcHeight - height) / 2);
			}
			//先等比缩放源图片
			Image srcImage = sourceImage.getScaledInstance(srcWidth, srcHeight, Image.SCALE_SMOOTH);
			bo = new ByteArrayOutputStream();
			//=============================
			//将image对象转换为bufferedImage对象
			BufferedImage tempImage = new BufferedImage(srcWidth, srcHeight, BufferedImage.TYPE_INT_BGR);
			Graphics2D g = tempImage.createGraphics();
			g.setBackground(Color.white);
			g.drawImage(srcImage, 0, 0, null);
			g.dispose();
			//=============================
			//将bufferedImage写入字节流中备用
			ImageIO.write(tempImage, "JPEG", bo);
			// 获取图片输入流
			iis = ImageIO.createImageInputStream(new ByteArrayInputStream(bo.toByteArray()));
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName("JPEG");
			ImageReader reader = it.next();
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			// 定义一个矩形
			Rectangle rect = new Rectangle(index_x, index_y, width, height);
			// 指定读取图片的区域为传入的矩形所在区域
			param.setSourceRegion(rect);
			//获取指定区域图像
			BufferedImage bi = reader.read(0, param);
			// 保存新图片
			ImageIO.write(bi, "JPEG", dest);

		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bo!=null){
				try {
					bo.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if(iis!=null){
				try {
					iis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
