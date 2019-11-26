package xyz.tincat.host.feast.mvc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @ Date       ：Created in 13:47 2019/11/25
 * @ Modified By：
 * @ Version:     0.1
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("def")
    public DeferredResult<String> getTest() {
        DeferredResult<String> deferredResult = new DeferredResult<>();
//        deferredResult.setResult("sss");
        deferredResult.setErrorResult(new Exception("eee"));
        return deferredResult;
    }

    @GetMapping("e")
    public DeferredResult<ResponseEntity> getTest2() {
        DeferredResult<ResponseEntity> deferredResult = new DeferredResult<>();
//        deferredResult.setResult("sss");
        deferredResult.setErrorResult(new Exception("eee"));
        return deferredResult;
    }
}