package com.nott.scStream.code.capture;

import com.nott.scStream.code.capture.vo.CaptureVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: wangjun
 * @Description:
 * @Date: created in 2019/8/30 16:43
 * @Modified By:
 **/
@RestController
@RequestMapping("/capture")
public class CaptureController {

    private static Logger log = LoggerFactory.getLogger(CaptureController.class);

    @Autowired
    private CaptureService captureService;

    @PostMapping("/getInfo")
    public String getInfoXlsx(@RequestBody CaptureVo captureVo) {

        captureService.getInfoXlsx(captureVo);

        log.info("Capture-getInfo: All Done!");

        return "All Done!";

    }

    /**
     * 通过excel批量爬取网址数据
     * @param captureVo
     * @return
     */
    @PostMapping("/getInfoFromExcel")
    public String getInfoFromExcel(@RequestBody CaptureVo captureVo) {

        String result = captureService.getInfoFromExcel(captureVo);

        return result;

    }

}
