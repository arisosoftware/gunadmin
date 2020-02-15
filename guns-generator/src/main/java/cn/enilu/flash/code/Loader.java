package cn.enilu.flash.code;

import org.nutz.ioc.Ioc;

import java.util.Map;


public abstract class Loader {

    public abstract Map<String, TableDescriptor> loadTables(Ioc ioc,
                                                            String basePackageName, String baseUri,
                                                            String servPackageName,
                                                            String repositoryPackageName,
                                                            String modPackageName) throws Exception;


    }
