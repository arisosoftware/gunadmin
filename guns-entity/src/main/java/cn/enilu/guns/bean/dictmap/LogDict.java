package cn.enilu.guns.bean.dictmap;

import cn.enilu.guns.bean.dictmap.base.AbstractDictMap;


public class LogDict extends AbstractDictMap {

    @Override
    public void init() {
        put("tips","备注");
    }

    @Override
    protected void initBeWrapped() {

    }
}
