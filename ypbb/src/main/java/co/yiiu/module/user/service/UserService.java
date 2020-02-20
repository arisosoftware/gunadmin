package co.yiiu.module.user.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import co.yiiu.core.bean.Page;
import co.yiiu.core.util.JsonUtil;
import co.yiiu.module.collect.service.CollectService;
import co.yiiu.module.comment.service.CommentService;
import co.yiiu.module.log.service.LogService;
import co.yiiu.module.notification.service.NotificationService;
import co.yiiu.module.topic.service.TopicService;
import co.yiiu.module.user.mapper.UserMapper;
import co.yiiu.module.user.pojo.User;

/**
 * Created by tomoya. Copyright (c) 2016, All Rights Reserved. https://yiiu.co
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private CollectService collectService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private LogService logService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private TopicService topicService;
	@Autowired
	private UserMapper userMapper;

	/**
	 * 禁用/解禁用户
	 *
	 * @param id
	 */
	public void blockUser(Integer id) {
		User user = findById(id);
		user.setBlock(!user.getBlock());
		update(user);
		this.refreshCache(user);
	}

	public User createUser(String username, String password, String email, String avatar, String url, String bio) {
		if (!StringUtils.isEmpty(email) && email.equals("null")) {
			email = null;
		}
		User user = new User();
		user.setEmail(email);
		user.setUsername(username);
		user.setPassword(new BCryptPasswordEncoder().encode(password));
		user.setInTime(new Date());
		user.setBlock(false);
		user.setToken(UUID.randomUUID().toString());
		user.setAvatar(avatar);
		user.setUrl(url);
		user.setBio(bio);
		user.setReputation(0);
		// 默认邮件打开
		user.setCommentEmail(true);
		user.setReplyEmail(true);
		return this.save(user);
	}

	// 删除所有后台用户存在redis里的数据
	public void deleteAllRedisUser() {
		List<User> users = userMapper.findAll(null, null, null);
		users.forEach(user -> stringRedisTemplate.delete(user.getToken()));
	}

	public void deleteById(Integer id) {
		// 删除用户的日志
		logService.deleteByUserId(id);
		// 删除用户的通知
		notificationService.deleteByTargetUser(id);
		// 删除用户的收藏
		collectService.deleteByUserId(id);
		// 删除用户的评论
		commentService.deleteByUserId(id);
		// 删除用户的话题
		topicService.deleteByUserId(id);
		// 删除用户
		userMapper.deleteByPrimaryKey(id);
	}

	public User findByEmail(String email) {
		return userMapper.findUser(null, null, email);
	}

	public User findById(int id) {
		return userMapper.selectByPrimaryKey(id);
	}

	/**
	 * search user by log desc
	 *
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<User> findByReputation(Integer pageNo, Integer pageSize) {
		List<User> list = userMapper.findAll((pageNo - 1) * pageSize, pageSize, "reputation desc");
		int count = userMapper.count();
		return new Page<>(pageNo, pageSize, count, list);
	}

	/**
	 * 根据令牌查询用户
	 *
	 * @param token
	 * @return
	 */
	public User findByToken(String token) {
		return userMapper.findUser(token, null, null);
	}

	/**
	 * 根据用户名判断是否存在
	 *
	 * @param username
	 * @return
	 */
	public User findByUsername(String username) {
		return userMapper.findUser(null, username, null);
	}

	/**
	 * 分页查询用户列表
	 *
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page<User> pageUser(Integer pageNo, Integer pageSize) {
		List<User> list = userMapper.findAll((pageNo - 1) * pageSize, pageSize, "id desc");
		int count = userMapper.count();
		return new Page<>(pageNo, pageSize, count, list);
	}

	public void refreshCache(User user) {
		// 更新redis里的数据
		ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
		stringStringValueOperations.set(user.getToken(), JsonUtil.objectToJson(user));
	}

	// 刷新用户token
	public User refreshToken(User user) {
		user.setToken(UUID.randomUUID().toString());
		return this.update(user);
	}

	public User save(User user) {
		userMapper.insertSelective(user);
		this.refreshCache(user);
		return user;
	}

	public User update(User user) {
		userMapper.updateByPrimaryKeySelective(user);
		user = userMapper.selectByPrimaryKey(user.getId());
		this.refreshCache(user);
		return user;
	}
}
