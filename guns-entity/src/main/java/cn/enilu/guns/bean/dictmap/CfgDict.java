package cn.enilu.guns.bean.dictmap;

import cn.enilu.guns.bean.dictmap.base.AbstractDictMap;


public class CfgDict extends AbstractDictMap {

    @Override
    public void init() {
        put("cfgId","参数id");
        put("cfgName","参数名称");
    }

    @Override
    protected void initBeWrapped() {

    }
}
