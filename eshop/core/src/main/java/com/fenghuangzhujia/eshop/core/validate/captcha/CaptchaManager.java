package com.fenghuangzhujia.eshop.core.validate.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import nl.captcha.Captcha;
import nl.captcha.backgrounds.GradiatedBackgroundProducer;
import nl.captcha.text.renderer.ColoredEdgesWordRenderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.validate.BasicValidater;
import com.fenghuangzhujia.eshop.core.validate.captcha.dao.CaptchaValidaterService;
import com.fenghuangzhujia.eshop.core.validate.core.ValidateManager;
import com.fenghuangzhujia.eshop.core.validate.core.Validater;
import com.fenghuangzhujia.eshop.core.validate.core.exception.NotFoundException;
import com.fenghuangzhujia.eshop.core.validate.core.exception.ValidateException;

@Component
public class CaptchaManager implements ValidateManager {
	
	private static final List<Color> COLORS = new ArrayList<Color>(2);
    private static final List<Font> FONTS = new ArrayList<Font>(3);
    
    static {
        COLORS.add(Color.BLACK);
        COLORS.add(Color.BLUE);

        FONTS.add(new Font("Geneva", Font.ITALIC, 48));
        FONTS.add(new Font("Courier", Font.BOLD, 48));
        FONTS.add(new Font("Arial", Font.BOLD, 48));
    }
    
    private int width = 150;
    private int height = 75;
    private int expireMinutes=1;
	
	@Autowired
	private CaptchaValidaterService validaterService;

	@Override
	public void validate(String id, String code) throws ValidateException {
		Validater validater=validaterService.get(id);
		if(validater==null){
			throw new NotFoundException();
		}
		validaterService.delete(id);
		validater.validate(id, code);
	}

	@Override
	public BufferedImage create(String id) {
		ColoredEdgesWordRenderer wordRenderer = new ColoredEdgesWordRenderer(COLORS, FONTS);
        Captcha captcha = new Captcha.Builder(width, height).addText(wordRenderer)
                .gimp()
                .addNoise()
                .addBackground(new GradiatedBackgroundProducer())
                .build();
        String code=captcha.getAnswer();
        BasicValidater validater=new BasicValidater(id, code, expireMinutes);
        validaterService.add(validater);
        return captcha.getImage();        
	}

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getExpireMinutes() {
		return expireMinutes;
	}
	public void setExpireMinutes(int expireMinutes) {
		this.expireMinutes = expireMinutes;
	}
}
