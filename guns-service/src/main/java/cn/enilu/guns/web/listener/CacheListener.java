package cn.enilu.guns.web.listener;

import cn.enilu.guns.dao.cache.ConfigCache;
import cn.enilu.guns.dao.cache.DictCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class CacheListener implements CommandLineRunner {

	@Autowired
	private ConfigCache configCache;
	@Autowired
	private DictCache dictCache;

	private Logger logger = LoggerFactory.getLogger(CacheListener.class);

	public void loadCache() {
		configCache.cache();
		dictCache.cache();
	}

	@Override
	public void run(String... strings) throws Exception {
		logger.info(".....................cache listener........................");

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				loadCache();
			}
		});
		thread.start();
	}
}
