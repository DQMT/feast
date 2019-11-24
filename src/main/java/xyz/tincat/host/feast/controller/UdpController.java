package xyz.tincat.host.feast.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.tincat.host.feast.model.SendUdpDTO;

@RestController
@RequestMapping("/")
@Slf4j
public class UdpController {
    @RequestMapping(value = "sendUdp", method = RequestMethod.POST)
    public String sendUdp(@RequestBody SendUdpDTO sendUdpDTO) {
        log.info("send = {}", sendUdpDTO);
        return "sendUdpdone";
    }
}
