package com.itheima.controller;

import com.itheima.dao.UserDao;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;


/**
 *  文件上传下载
 * @author WangKangSheng
 * @date 2022-06-05 18:07
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private UserDao userDao ;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 获得默认gridFS bucket，
     *  指定上传文件的库为 'def_gfs'
     *   这样做的好处是，多个系统使用不同的数据库连接，可以通过指定统一的数据库来创建GridFSBucket
     * @param mongoTemplate MongoTemplate
     * @return GridFSBucket
     */
    private GridFSBucket defaultGridFsBucket(MongoTemplate mongoTemplate){
        if (null == mongoTemplate){
            throw new NullPointerException("获取默认的GridFSBucket时mongoTemplate不能为空");
        }
        // 通过mongoTemplate来获取默认的数据库连接
        MongoDatabaseFactory mongoDatabaseFactory = mongoTemplate.getMongoDatabaseFactory();
        MongoDatabase db = mongoDatabaseFactory.getMongoDatabase("critics");

        // 创建GridFSBucket 并指定文件系统使用的bucket
        return GridFSBuckets.create(db,"def_bucket");
    }

    /**
     * 上传文件到gfs
     * @param file MultipartFile 文件
     * @return String
     * @throws IOException 读取输入流时的异常
     */
    @PostMapping("/upload")
    public String upload(MultipartFile file ,@RequestParam String username) throws IOException {


        // 拿到gfs的bucket
        GridFSBucket gridFsBucket = defaultGridFsBucket(this.mongoTemplate);

        // 读取文件名
        String filename = file.getOriginalFilename();

        // 上传文件到gfs  并得到返回的object id
        ObjectId objectId = gridFsBucket.uploadFromStream(filename, file.getInputStream());


        userDao.saveUserHeader(username, String.valueOf(objectId));

        return "文件上传成功!文件名为："+filename+"文件id为："+objectId.toHexString();
    }

    /**
     * 下载、预览文件
     * @param fileId String 文件的objectId
     * @param response HttpServletResponse
     */
    @RequestMapping("/download/{fid}")
    public void download(@PathVariable("fid") String fileId, HttpServletResponse response) throws IOException {
        if (null == fileId || fileId.isEmpty() || !ObjectId.isValid(fileId)){
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.getWriter().println("File id is empty or not object id.");
            return;
        }

        // 拿到bucket
        GridFSBucket gridFsBucket = defaultGridFsBucket(this.mongoTemplate);

        ObjectId objectId = new ObjectId(fileId);
        // 查询文件
        GridFSFile file = gridFsBucket.find(new Document("_id", objectId)).first();

        // 没有查询到文件
        if (null == file){
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.getWriter().println("File not found.");
            return;
        }


        response.setContentType("application/octet-stream");
        // 下载文件能正常显示中文
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getFilename(), "UTF-8"));

        // 写出到response
        gridFsBucket.downloadToStream(objectId,response.getOutputStream());

    }



}
