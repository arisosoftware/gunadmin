package net.chenlin.dp.common.utils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * 文件上传工具类
 * @author ZhouChenglin
 * @date 2018/1/29
 */
public class UploadUtils {

    private static Logger LOG = LoggerFactory.getLogger(UploadUtils.class);

    /** 上传文件缓存大小限制 */
    private static int MAX_UPLOAD_SIZE = 1024 * 1024 * 1;
    /** 上传文件临时目录 */
    private static final String DEFAULT_UPLOAD_DIR = "/upload/";

    /** 获取所有文本域 */
    public static final List<?> getFileItemList(HttpServletRequest request, File saveDir) throws FileUploadException {
        if (!saveDir.isDirectory()) {
            saveDir.mkdir();
        }
        List<?> fileItems = null;
        RequestContext requestContext = new ServletRequestContext(request);
        if (FileUpload.isMultipartContent(requestContext)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setRepository(saveDir);
            factory.setSizeThreshold(MAX_UPLOAD_SIZE);
            ServletFileUpload upload = new ServletFileUpload(factory);
            fileItems = upload.parseRequest(request);
        }
        return fileItems;
    }

    /** 获取文本域 */
    public static final FileItem[] getFileItem(HttpServletRequest request, File saveDir, String... fieldName)
            throws FileUploadException {
        if (fieldName == null || saveDir == null) {
            return null;
        }
        List<?> fileItemList = getFileItemList(request, saveDir);
        FileItem fileItem = null;
        FileItem[] fileItems = new FileItem[fieldName.length];
        for (int i = 0; i < fieldName.length; i++) {
            for (Iterator<?> iterator = fileItemList.iterator(); iterator.hasNext();) {
                fileItem = (FileItem) iterator.next();
                // 根据名字获得文本域

                if (fieldName[i] != null && fieldName[i].equals(fileItem.getFieldName())) {
                    fileItems[i] = fileItem;
                    break;
                }
            }
        }
        return fileItems;
    }

    /** 上传文件处理(支持批量) */
    public static List<String> uploadFile(HttpServletRequest request, String path) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        List<String> fileNames = new ArrayList<>();
        if (multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            String pathDir = getUploadDir(request, path);
            File dirFile = new File(pathDir);
            if (!dirFile.isDirectory()) {
                dirFile.mkdirs();
            }
            for (Iterator<String> iterator = multiRequest.getFileNames(); iterator.hasNext();) {
                String key = iterator.next();
                MultipartFile multipartFile = multiRequest.getFile(key);
                if (multipartFile != null) {
                    String name = multipartFile.getOriginalFilename();
                    String uuid = UUID.randomUUID().toString();
                    String postFix = name.substring(name.lastIndexOf(".")).toLowerCase();
                    String fileName = uuid + postFix;
                    String filePath = pathDir + File.separator + fileName;
                    File file = new File(filePath);
                    file.setWritable(true, false);
                    try {
                        multipartFile.transferTo(file);
                        fileNames.add(path.concat(fileName));
                    } catch (Exception e) {
                        LOG.error(name + "保存失败", e);
                    }
                }
            }
        }
        return fileNames;
    }

    /**
     * 单个文件上传
     * @param request
     * @param filePath
     * @param fileName
     * @return
     */
    public static String uploadFile(HttpServletRequest request, String filePath, String fileName) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (multipartResolver.isMultipart(request)) {
        MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
        String pathDir = getUploadDir(request, filePath);
        File dirFile = new File(pathDir);
        if (!dirFile.isDirectory()) {
            dirFile.mkdirs();
        }
        MultipartFile multipartFile = multiRequest.getFile("file");
        if (multipartFile != null) {
            String name = multipartFile.getOriginalFilename();
            String postFix = name.substring(name.lastIndexOf(".")).toLowerCase();
            String saveName = fileName + postFix;
            String savePath = pathDir + File.separator + saveName;
            File file = new File(savePath);
            if(file.exists()) {
                file.delete();
            }
            file.setWritable(true, false);
            try {
                multipartFile.transferTo(file);
                return saveName;
            } catch (Exception e) {
                LOG.error(name + "保存失败", e);
            }
        }}
        return null;
    }

    public static List<String> uploadImageData(HttpServletRequest request) {
        List<String> fileNames = new ArrayList<>();
        Enumeration<String> params = request.getParameterNames();
        String pathDir = getUploadDir(request);
        File dir = new File(pathDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        while (params.hasMoreElements()) {
            String key = params.nextElement();
            String fileStr = request.getParameter(key);
            if (fileStr != null && !"".equals(fileStr)) {
                int index = fileStr.indexOf("base64");
                if (index > 0) {
                    try {
                        String fileName = UUID.randomUUID().toString();
                        String preStr = fileStr.substring(0, index + 7);
                        String prefix = preStr.substring(preStr.indexOf("/") + 1, preStr.indexOf(";")).toLowerCase();
                        fileStr = fileStr.substring(fileStr.indexOf(",") + 1);
                        byte[] bb = Base64.getDecoder().decode(fileStr);
                        for (int j = 0; j < bb.length; ++j) {
                            // 调整异常数据
                            if (bb[j] < 0) {
                                bb[j] += 256;
                            }
                        }
                        String distPath = pathDir + fileName + "." + prefix;
                        OutputStream out = new FileOutputStream(distPath);
                        out.write(bb);
                        out.flush();
                        out.close();
                        fileNames.add(fileName + "." + prefix);
                    } catch (Exception e) {
                        LOG.error("上传文件异常：", e);
                    }
                }
            }
        }
        return fileNames;
    }

    /** 获取上传文件临时目录 */
    public static String getUploadDir(HttpServletRequest request) {
        return request.getServletContext().getRealPath(DEFAULT_UPLOAD_DIR);
    }

    /** 获取上传文件临时目录 */
    public static String getUploadDir(HttpServletRequest request, String path) {
        return request.getServletContext().getRealPath(path);
    }

}
