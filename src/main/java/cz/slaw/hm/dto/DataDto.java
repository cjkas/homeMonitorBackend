package cz.slaw.hm.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class DataDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private LocalDateTime date;
    private Object value;


}
