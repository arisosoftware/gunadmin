package co.yiiu.core.util.identicon.generator;

import java.awt.Color;

/**
 * Author: Bryant Hang Date: 15/1/10 Time: 下午2:43
 */
public interface IBaseGenartor {
	/**
	 * 获取图片背景色
	 *
	 * @return
	 */
	Color getBackgroundColor();

	/**
	 * 将hash字符串转换为bool二维6*5数组
	 *
	 * @param hash
	 * @return
	 */
	boolean[][] getBooleanValueArray(String hash);

	/**
	 * 获取图案前景色
	 *
	 * @return
	 */
	Color getForegroundColor();
}
