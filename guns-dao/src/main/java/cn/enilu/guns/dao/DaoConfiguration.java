package cn.enilu.guns.dao;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories("cn.enilu.guns.dao")
public class DaoConfiguration {
}
