package cz.slaw.hm.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

public class DataDto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Getter
    @Setter
    private Date date;
    @Getter
    @Setter
    private Object value;


}
