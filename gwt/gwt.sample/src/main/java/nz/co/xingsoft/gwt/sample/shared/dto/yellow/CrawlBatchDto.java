package nz.co.xingsoft.gwt.sample.shared.dto.yellow;

import java.io.Serializable;
import java.util.Date;

public class CrawlBatchDto implements Serializable {

    private Long id;

    private Date createdDateTime;

    private String status;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(final Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(final String status) {
        this.status = status;
    }

}
