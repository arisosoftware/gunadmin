package net.chenlin.dp.common.utils;

import net.chenlin.dp.common.constant.GenConstant;
import net.chenlin.dp.common.entity.ColumnEntity;
import net.chenlin.dp.common.entity.TableEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.*;

/**
 * 代码生成工具类（使用jdbc生成本地代码）
 * @author ZhouChenglin
 * @date 2018/1/7
 */
public class GenUtils {

    public static void main(String[] args) throws Exception {

        String jdbcDriver = "com.mysql.jdbc.Driver";
        String jdbcUrl = "jdbc:mysql://192.168.180.130:3306/dp-lte?useUnicode=true&characterEncoding=utf-8";
        String jdbcUsername = "root";
        String jdbcPassword = "root";

        String tablePrefix = "gen_";

        String javaProject = "dp-base";
        String webProject = "dp-web";
        String javaModule = "base";
        String webModule = "base";

        generatorCode(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword, tablePrefix, javaProject, webProject, javaModule, webModule);


    }

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<>();
        templates.add("template/Entity.java.vm");
        templates.add("template/Mapper.java.vm");
        templates.add("template/Mapper.xml.vm");
        templates.add("template/Manager.java.vm");
        templates.add("template/ManagerImpl.java.vm");
        templates.add("template/Service.java.vm");
        templates.add("template/ServiceImpl.java.vm");
        templates.add("template/Controller.java.vm");
        templates.add("template/list.html.vm");
        templates.add("template/list.js.vm");
        templates.add("template/add.html.vm");
        templates.add("template/add.js.vm");
        templates.add("template/edit.html.vm");
        templates.add("template/edit.js.vm");
        templates.add("template/menu.sql.vm");
        return templates;
    }

    public static void generatorCode(String jdbcDriver,
                                     String jdbcUrl,
                                     String jdbcUsername,
                                     String jdbcPassword,
                                     String tablePrefix,
                                     String javaProject,
                                     String webProject,
                                     String javaModule,
                                     String webModule) throws Exception {
        String rootPath = "";
        String osName = "os.name";
        String osWindows = "win";
        if(!System.getProperty(osName).toLowerCase().startsWith(osWindows)) {
            rootPath = "/";
        }

        String tableSql = "SELECT table_name, table_comment FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = (SELECT DATABASE()) AND table_name LIKE '" + tablePrefix + "_%';";

        JdbcUtils jdbcUtils = new JdbcUtils(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);
        List<Map> tableList = jdbcUtils.selectByParams(tableSql, null);

        TableEntity table = null;
        List<TableEntity> tables = new ArrayList<>();

        Iterator<Map> tableIterator = tableList.iterator();
        while(tableIterator.hasNext()) {
            Map<String, String> currTable = tableIterator.next();
            table = new TableEntity();
            String tableName = currTable.get("TABLE_NAME");
            String className = GenUtils.tableToJava(tableName, "");
            table.setTableName(tableName);
            table.setClassName(className);
            table.setObjName(StringUtils.uncapitalize(className));
            table.setTableComment(currTable.get("TABLE_COMMENT"));

            String columnSql = "SELECT column_name,data_type,column_comment,column_key,extra FROM information_schema.columns WHERE TABLE_NAME = '"+ tableName + "' AND table_schema = (SELECT DATABASE()) ORDER BY ordinal_position";
            ColumnEntity columnEntity = null;
            List<Map> columnList = jdbcUtils.selectByParams(columnSql,null);
            Iterator<Map> columnIterator = columnList.iterator();
            while(columnIterator.hasNext()){
                Map<String, String> currColumn = columnIterator.next();
                columnEntity = new ColumnEntity();
                columnEntity.setExtra(currColumn.get("EXTRA"));

                String columnName = currColumn.get("COLUMN_NAME");
                String methodName = GenUtils.columnToJava(columnName);
                columnEntity.setColumnName(columnName);
                columnEntity.setFieldName(StringUtils.uncapitalize(methodName));
                columnEntity.setMethodName(methodName);

                columnEntity.setColumnKey(currColumn.get("COLUMN_KEY"));
                columnEntity.setDataType(currColumn.get("DATA_TYPE"));
                columnEntity.setColumnComment(currColumn.get("COLUMN_COMMENT"));

                // 属性类型
                columnEntity.setFieldType(PropertiesUtils.getInstance("generator").get(columnEntity.getDataType()));

                // 主键判断
                if ("PRI".equals(columnEntity.getColumnKey()) && table.getPk() == null) {
                    table.setPk(columnEntity);
                }

                table.addColumn(columnEntity);
            }
            tables.add(table);
        }

        // 没主键，则第一个字段为主键
        if (table.getPk() == null) {
            table.setPk(table.getColumns().get(0));
        }

        String projectPath = getProjectPath(javaProject);
        System.out.println("===>>>java generation path:" + rootPath + projectPath);
        System.out.println("===>>>web generation path:" + rootPath + getProjectPath(webProject) + "\n");
        Map<String, Object> map = null;
        for (TableEntity tableEntity : tables) {
            // 封装模板数据
            map = new HashMap<>(16);
            map.put("tableName", tableEntity.getTableName());
            map.put("comments", tableEntity.getTableComment());
            map.put("pk", tableEntity.getPk());
            map.put("className", tableEntity.getClassName());
            map.put("objName", tableEntity.getObjName());
            map.put("functionCode", webModule);
            map.put("requestMapping", tableEntity.getTableName().replace("_","/"));
            map.put("viewPath", webModule + "/" + tableEntity.getClassName().toLowerCase());
            map.put("authKey", GenUtils.urlToAuthKey(tableEntity.getTableName().replace("_","/")));
            map.put("columns", tableEntity.getColumns());
            map.put("hasDecimal", tableEntity.buildHasDecimal().getHasDecimal());
            map.put("package", PropertiesUtils.getInstance("generator").get("package"));
            map.put("module", javaModule);
            map.put("author", PropertiesUtils.getInstance("generator").get("author"));
            map.put("email", PropertiesUtils.getInstance("generator").get("email"));
            VelocityContext context = new VelocityContext(map);

            System.out.println("============ start table: " + tableEntity.getTableName() + " ================");

            for (String template : GenUtils.getTemplates()) {
                String filePath = getFileName(template, javaProject, webProject, javaModule, webModule, tableEntity.getClassName(), rootPath);
                String templatePath = rootPath + GenUtils.class.getResource("/"+template).getPath().replaceFirst("/", "");
                File dstDir = new File(VelocityUtils.getPath(filePath));
                //文件夹不存在创建文件夹
                if(!dstDir.exists()){
                    dstDir.mkdirs();
                }
                File dstFile = new File(filePath);
                //文件不存在则创建
                if(!dstFile.exists()){
                    VelocityUtils.generate(templatePath, filePath, context);
                    System.out.println(filePath + "===>>>创建成功！");
                } else {
                    System.out.println(filePath + "===>>>文件已存在，未重新生成！");
                }
            }
            System.out.println("============ finish table: " + tableEntity.getTableName() + " ================\n");
        }
    }


    public static String getFileName(String template, String javaProject, String webProject, String javaModule, String webModule, String className, String rootPath) {
        String packagePath = rootPath + getProjectPath(javaProject) + "/src/main/java/" + PropertiesUtils.getInstance("generator").get("package").replace(".","/") + "/" + javaModule + "/";
        String resourcePath = rootPath + getProjectPath(javaProject) + "/src/main/resources/" + PropertiesUtils.getInstance("generator").get("package").replace(".","/") + "/" + javaModule + "/";
        String webPath = rootPath + getProjectPath(webProject) + "/src/main/webapp/";
        if (template.contains(GenConstant.JAVA_ENTITY)) {
            return packagePath + "entity/" + className + "Entity.java";
        }

        if (template.contains(GenConstant.JAVA_MAPPER)) {
            return packagePath + "dao/" + className + "Mapper.java";
        }

        if (template.contains(GenConstant.XML_MAPPER)) {
            return resourcePath + "dao/" + className + "Mapper.xml";
        }

        if (template.contains(GenConstant.JAVA_MANAGER)) {
            return packagePath + "manager/" + className + "Manager.java";
        }

        if (template.contains(GenConstant.JAVA_MANAGER_IMPL)) {
            return packagePath + "manager/impl/" + className + "ManagerImpl.java";
        }

        if (template.contains(GenConstant.JAVA_SERVICE)) {
            return packagePath + "service/" + className + "Service.java";
        }

        if (template.contains(GenConstant.JAVA_SERVICE_IMPL)) {
            return packagePath + "service/impl/" + className + "ServiceImpl.java";
        }

        if (template.contains(GenConstant.JAVA_CONTROLLER)) {
            return packagePath + "controller/" + className + "Controller.java";
        }

        if (template.contains(GenConstant.HTML_LIST)) {
            return webPath + "WEB-INF/view/" + webModule + "/" + className.toLowerCase() + "/list.html";
        }

        if (template.contains(GenConstant.HTML_ADD)) {
            return webPath + "WEB-INF/view/" + webModule + "/" + className.toLowerCase() + "/add.html";
        }

        if (template.contains(GenConstant.HTML_EDIT)) {
            return webPath + "WEB-INF/view/" + webModule + "/" + className.toLowerCase() + "/edit.html";
        }

        if (template.contains(GenConstant.JS_LIST)) {
            return webPath + "statics/js/" + webModule + "/" + className.toLowerCase() + "/list.js";
        }

        if (template.contains(GenConstant.JS_ADD)) {
            return webPath + "statics/js/" + webModule + "/" + className.toLowerCase()  + "/add.js";
        }

        if (template.contains(GenConstant.JS_EDIT)) {
            return webPath + "statics/js/" + webModule + "/" + className.toLowerCase()  + "/edit.js";
        }

        if (template.contains(GenConstant.SQL_MENU)) {
            return rootPath + getProjectPath(javaProject) + "/src/main/resources/sqls/" + className.toLowerCase() + "_menu.sql";
        }

        return null;
    }

    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[] { '_' }).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * 权限标识
     *
     * @param url
     * @return
     */
    public static String urlToAuthKey(String url) {
        return url.replace("/", ":");
    }

    /**
     * 项目路径
     *
     * @param targetProject
     * @return
     */
    public static String getProjectPath(String targetProject) {
        String basePath = JdbcUtils.class.getResource("/").getPath().replace("/target/classes/", "").replace(targetProject, "").replaceFirst("/", "");
        String projectPath = VelocityUtils.getPath(basePath) + targetProject;
        return projectPath;
    }

}
