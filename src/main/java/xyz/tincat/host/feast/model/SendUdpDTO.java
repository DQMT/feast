package xyz.tincat.host.feast.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class SendUdpDTO {
    private String serverHost;
    private Integer serverPort;
    private String content;
}
