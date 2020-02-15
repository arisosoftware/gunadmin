package cn.enilu.guns.admin.core.util;

import java.util.ArrayList;
import java.util.List;


public class SqlUtil {

	/**
	 * @Description 根据集合的大小，输出相应个数"?"
	 * @author fengshuonan
	 */
	public static String parse(List<?> list) {
		String str = "";
		if (list != null && list.size() > 0) {
			str = str + "?";
			for (int i = 1; i < list.size(); i++) {
				str = str + ",?";
			}
		}
		return str;
	}

	public static void main(String[] args) {
		ArrayList<Object> arrayList = new ArrayList<>();
		arrayList.add(2);
		arrayList.add(2);
		System.out.println(parse(arrayList));
	}
}
