package cn.enilu.guns.dao.cache;

import cn.enilu.guns.bean.entity.system.Dict;

import java.util.List;


public interface DictCache extends Cache {

	List<Dict> getDictsByPname(String dictName);

	String getDict(Long dictId);
}
