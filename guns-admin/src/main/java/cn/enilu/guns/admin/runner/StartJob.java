package cn.enilu.guns.admin.runner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import cn.enilu.guns.bean.vo.QuartzJob;
import cn.enilu.guns.service.task.JobService;

@Component
public class StartJob implements ApplicationRunner {

	@Autowired
	private JobService jobService;

	private Logger log = LoggerFactory.getLogger(getClass());

	@Override
	public void run(ApplicationArguments applicationArguments) throws Exception {
		log.info("start Job >>>>>>>>>>>>>>>>>>>>>>>");
		List<QuartzJob> list = jobService.getTaskList();
		for (QuartzJob quartzJob : list) {
			jobService.addJob(quartzJob);
		}
	}
}
