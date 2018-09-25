package com.ruiec.framework.web.captcha;

import java.awt.Font;

import org.springframework.core.io.ClassPathResource;

import com.octo.captcha.component.image.backgroundgenerator.FileReaderRandomBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.FontGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.textpaster.TextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.image.wordtoimage.WordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;

public class ImageEngine extends ListImageCaptchaEngine {

	@Override
	protected void buildInitialFactories() {
		  WordGenerator wgen = new RandomWordGenerator("ABCDEFGHIJKLMNOPQRST123456789");
	        RandomRangeColorGenerator cgen = new RandomRangeColorGenerator(
	             new int[] {0, 100},
	             new int[] {0, 100},
	             new int[] {0, 100});
	        //文字显示4个数
	        TextPaster textPaster = new RandomTextPaster(new Integer(4), new Integer(4), cgen, true);
	        //图片的大小
	        FileReaderRandomBackgroundGenerator backgroundGenerator = new FileReaderRandomBackgroundGenerator(new Integer(130), new Integer(50),new ClassPathResource("/com/ruiec/framework/web/captcha/picture/").getPath());

	        Font[] fontsList = new Font[] {
	            new Font("Arial", 0, 10),
	            new Font("Tahoma", 0, 10),
	            new Font("Verdana", 0, 10),
	         };
	     
	        FontGenerator fontGenerator = new RandomFontGenerator(new Integer(20), new Integer(30), fontsList);
	        WordToImage wordToImage = new ComposedWordToImage(fontGenerator, backgroundGenerator, textPaster);
	        this.addFactory(new GimpyFactory(wgen, wordToImage));

	}
}
