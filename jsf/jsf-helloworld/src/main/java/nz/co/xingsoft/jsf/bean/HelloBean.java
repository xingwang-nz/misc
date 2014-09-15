package nz.co.xingsoft.jsf.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean
// @SessionScoped
@RequestScoped
public class HelloBean
        implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    @ManagedProperty(value = "#{messageBean}")
    private MessageBean messageBean;

    public String getName() {
        return messageBean.getMessage() + " " + name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setMessageBean(final MessageBean messageBean) {
        this.messageBean = messageBean;
    }

}