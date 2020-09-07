/**
 * @Author: nott
 * @Description:
 * @Date: created in 2020/9/3 9:29
 * @Modified By:
 */
package com.nott.poi.poiexcel.controller;

import com.nott.poi.poiexcel.service.FormConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/poi")
public class PoiController {

    @Autowired
    private FormConvert formConvert;

    @PostMapping("/formUpload")
    @ResponseBody
    public String formUpload(@RequestParam MultipartFile file) {
        formConvert.convert(file);
        return "all done";
    }

}
