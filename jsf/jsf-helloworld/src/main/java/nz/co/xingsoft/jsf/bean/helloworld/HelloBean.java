package nz.co.xingsoft.jsf.bean.helloworld;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import nz.co.xingsoft.jsf.service.MessageService;

@ManagedBean(name = "helloBean")
// @SessionScoped
@RequestScoped
public class HelloBean
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    @ManagedProperty(value = "#{messageBean}")
    private MessageBean messageBean;

    @ManagedProperty(value = "#{messageService}")
    private MessageService messageService;

    public String getName() {
        return messageService.getMessage() + " " + name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setMessageBean(final MessageBean messageBean) {
        this.messageBean = messageBean;
    }

    public void setMessageService(final MessageService messageService) {
        this.messageService = messageService;
    }

}