package com.fenghuangzhujia.eshop.utils.web;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

public class ImageResponseUtil {

	/**
	 * 输出图片
	 * @param response
	 * @param image 图片
	 * @param sufix 图片后缀
	 */
	public static void writeBufferedImage
		(HttpServletResponse response,BufferedImage image, String sufix) {
		response.setHeader("Cache-Control", "private,no-cache,no-store");
        response.setContentType("image/"+sufix);
        try {
            ImageIO.write(image, sufix, response.getOutputStream());
            response.getOutputStream().close();
        } catch (IOException e) {
            throw new ErrorCodeException(SystemErrorCodes.OTHER, e);
        }
	}
}
