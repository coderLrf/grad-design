package com.example.designtopicselectionsystem.service;

import com.example.designtopicselectionsystem.mapper.FileMapper;
import com.example.designtopicselectionsystem.response.ResponseJson;
import com.example.designtopicselectionsystem.response.ResponseJsonUtil;
import com.example.designtopicselectionsystem.utils.Commons;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.Future;

@Service
@Transactional
public class FileService {

    @Autowired
    private FileMapper fileMapper;

    // 文件的上传
    public String uploadFile(MultipartFile fileUpload) {
        // 获取文件名以及后缀名
        String fileName = fileUpload.getOriginalFilename();
        // 重新生成新的文件名（根据具体情况生成对应文件名）
        fileName = UUID.randomUUID() + "_" + fileName;
        File file;
        try {
            // 指定上传文件本地存储目录（存储到静态资源目录下）
            String dirPath = ResourceUtils.getFile("classpath:").getPath() + "\\static\\upload\\file\\";
            // 根据目录创建一个file对象
            File dir = new File(dirPath);
            if(!dir.exists()) {
                dir.mkdirs(); // 创建
            }
            // 创建一个需要上传的file
            file = new File(dir.getAbsolutePath() + "\\" + fileName);
            fileUpload.transferTo(file); // 上传文件
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return fileName;
    }

    // 根据课题id删除文件
    void deleteFileByTopicId(Integer topicId) {
        fileMapper.deleteFile(topicId);
    }

    // 保存文件到数据库
    void uploadFile(Integer topicId, String fileName) {
        fileMapper.uploadFile(topicId, fileName);
    }

    // 文件的下载
    public ResponseEntity<byte[]> fileDownload(HttpServletRequest request, String fileId) {
        // 指定要下载的文件根路径
        try {
            String dirPath = ResourceUtils.getFile("classpath:").getPath() + "\\static\\upload\\file\\";
            // 创建该文件对象
            File file = new File(dirPath + File.separator + fileId);
            // 下载文件
            return this.fileDownload(request, file).get();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage().getBytes(), HttpStatus.EXPECTATION_FAILED);
        }
    }

    // 根据课题的id获取对应的任务书
    public ResponseJson selectFilename(Integer topicId) {
        com.example.designtopicselectionsystem.domain.File file = fileMapper.selectFilename(topicId);
        if(file == null) {
            return ResponseJsonUtil.error(-1, "任务书还未存在，快快通知指导老师上传吧~");
        }
        // 根据filename继续拆分一个新的文件名（原文件名称去除uuid）
        String filename = file.getFile_id().substring(file.getFile_id().indexOf("_") + 1);
        // 重新设置filename
        file.setFilename(filename);
        // 添加任务书路径
        String path = "http://localhost:9527/static/upload/file/" + file.getFile_id();
        file.setFilePath(path);
        return ResponseJsonUtil.successData(file, "文件获取成功.");
    }

    // 文件下载（这个方法可以进行异步请求）
    @Async
    public Future<ResponseEntity<byte[]>> fileDownload(HttpServletRequest request, File file) throws Exception {
        ResponseEntity<byte[]> responseEntity = null;
        // 根据uuid组成的文件名称需要重新分割成为新的文件名称
        String filename = file.getName().substring(file.getName().indexOf("_") + 1);
        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        // 通知浏览器以下载方式打开（下载前对文件名进行转码）
        filename = getFilename(request, filename);
        headers.setContentDispositionFormData("attachment", filename);
        // 定义以流的形式下载返回文件数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        responseEntity = new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
        return new AsyncResult<ResponseEntity<byte[]>>(responseEntity);
    }

    // 根据浏览器的不同进行编码设置，返回编码后的文件名
    private String getFilename(HttpServletRequest request, String filename) throws IOException {
        // ie浏览器不同版本user-agent中出现的关键词
        String[] IEBrowserKeyWords = {"MSIE", "Trident", "Edge"};
        // 获取请求头代理信息
        String userAgent = request.getHeader("User-Agent");
        for (String keyWord : IEBrowserKeyWords) {
            if(userAgent.contains(keyWord)) {
                // 如果是ie浏览器，统一为utf-8编码显示，并对转换的 + 进行更正
                return URLEncoder.encode(filename, "UTF-8").replace("+", " ");
            }
        }
        // 其他浏览器统一为ISO-8859-1编码显示
        return new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
    }

}
