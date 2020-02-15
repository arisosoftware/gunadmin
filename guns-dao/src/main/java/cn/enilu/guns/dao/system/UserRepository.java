package cn.enilu.guns.dao.system;

import cn.enilu.guns.bean.entity.system.User;
import cn.enilu.guns.dao.BaseRepository;

import java.util.List;


public interface UserRepository extends BaseRepository<User, Long> {
	User findByAccount(String account);

	List<User> findByRoleid(String valueOf);
}
