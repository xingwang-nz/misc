package nz.co.xingsoft.jsf.bean.helloworld;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "messageBean", eager = true)
@RequestScoped
public class MessageBean {

    public String getMessage() {
        return "You entered: ";
    }

}
