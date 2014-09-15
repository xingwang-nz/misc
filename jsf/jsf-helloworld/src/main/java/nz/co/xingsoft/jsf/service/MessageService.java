package nz.co.xingsoft.jsf.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class MessageService {

    private String name = "";

    @PostConstruct
    public void init() {
        name = "service";
    }

    public String getMessage() {
        return name;
    }
}
