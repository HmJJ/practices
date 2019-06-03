package com.nott.io.code.UpAndDown.controller;

import com.nott.io.code.UpAndDown.service.UpAndDownService;
import com.nott.io.code.UpAndDown.vo.DocumentFileVo;
import com.nott.io.code.common.contract.ResultVo;
import com.nott.io.code.common.exception.ServiceException;
import com.nott.io.code.mongodb.MongoQueryService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/6/3 10:41
 * @Modified By:
 **/
@RestController
@RequestMapping("/io/upAndDown")
public class UpAndDownController {

    @Autowired
    private UpAndDownService service;
    @Autowired
    private MongoQueryService mongoQueryService;

    @PostMapping(value = "/storeFile")
    public ResultVo storeFile(@RequestParam("files") MultipartFile[] files){
        try {

            ResultVo resultVo = new ResultVo();
            List<DocumentFileVo> vos = new ArrayList<>();

            /**
             * 保存多个文件，并返回documentFileRecord列表
             */
            for (MultipartFile file : files) {

                String storeId = service.storeFile(file);

                DocumentFileVo vo = new DocumentFileVo();
                vo.setStore(storeId);
                String originalFilename = file.getOriginalFilename();
                vo.setFileName(originalFilename);
                vo.setFileSize((int)file.getSize());
                vo.setFileSuffix(originalFilename.substring(originalFilename.lastIndexOf(".")+1, originalFilename.length()));
                LocalDateTime now = LocalDateTime.now();
                vo.setFileTime(now);

                vos.add(vo);

            }

            resultVo.setData(vos);
            return resultVo;
        }catch (ServiceException se){
            throw se;
        }catch (Exception e){
            String message = String.format("controller:%s,method:%s,message:%s","UpAndDownController","storeFile",e.getMessage());
            throw new ServiceException("2000",message);
        }
    }

    @PostMapping(value = "/deleteFile")
    public ResultVo deleteFile(@RequestBody DocumentFileVo vo){
        try {

            ResultVo resultVo = new ResultVo();

            if(vo.getStore() == null) {
                resultVo.setMessage("文件id为必传项");
                resultVo.setCode(ResultVo.API_ERROR);
                return  resultVo;
            }

            Boolean isDelete = service.deleteFile(vo.getStore());

            resultVo.setData(isDelete);
            return resultVo;
        }catch (ServiceException se){
            throw se;
        }catch (Exception e){
            String message = String.format("controller:%s,method:%s,message:%s","UpAndDownController","deleteFile",e.getMessage());
            throw new ServiceException("2000",message);
        }
    }

    @GetMapping(value = "/downloadFile")
    public void downloadFile(HttpServletRequest request, HttpServletResponse response) {
        try {

            response.setContentType("application/octet-stream");
            String store = request.getParameter("store");
            byte[] bytes = new byte[1024];
            bytes = mongoQueryService.find(store);
//            String fileName = queryService.findByStore(store).getFileName();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
            String fileName = sdf.format(new Date());
            response.setHeader("Content-disposition", "attachment;filename=\"" + fileName + "\"");
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);

            IOUtils.copy(inputStream, response.getOutputStream());
            response.getOutputStream().flush();

            IOUtils.closeQuietly(inputStream);
        }catch (ServiceException se){
            throw se;
        }catch (Exception e){
            String message = String.format("controller:%s,method:%s,message:%s","UpAndDownController","downloadFile",e.getMessage());
            throw new ServiceException("2000",message);
        }

    }

}
