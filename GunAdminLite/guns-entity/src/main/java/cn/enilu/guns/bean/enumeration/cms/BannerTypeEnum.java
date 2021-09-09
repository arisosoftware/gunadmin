package cn.enilu.guns.bean.enumeration.cms;

public enum  BannerTypeEnum {

    INDEX("index"),
    NEWS("news"),
    CASE ("case"),
    PRODUCT("product"),
    SOLUTION("solution");

    private String value;

    BannerTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
