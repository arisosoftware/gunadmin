package co.yiiu.core.util;

import com.google.gson.Gson;

/**
 * Created by tomoya at 2018/3/27
 */
public class JsonUtil {

	public final static Gson gson = new Gson();

	public static <T> T jsonToObject(String json, Class<T> object) {
		return JsonUtil.gson.fromJson(json, object);
	}

	public static String objectToJson(Object object) {
		return JsonUtil.gson.toJson(object);
	}

}
