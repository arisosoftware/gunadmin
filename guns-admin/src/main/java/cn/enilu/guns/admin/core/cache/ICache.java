
package cn.enilu.guns.admin.core.cache;

import java.util.List;

public interface ICache {

	void put(String cacheName, Object key, Object value);

	<T> T get(String cacheName, Object key);

	@SuppressWarnings("rawtypes")
	List getKeys(String cacheName);

	void remove(String cacheName, Object key);

	void removeAll(String cacheName);

	<T> T get(String cacheName, Object key, ILoader iLoader);

	<T> T get(String cacheName, Object key, Class<? extends ILoader> iLoaderClass);

}
