package cz.slaw.hm.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "ws")
public class WeatherStationProbeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Getter
	@Setter
	private Long id;

	@Column(name = "temp_ext")
	@Getter
	@Setter
	private Float tempExternal;

	@Column(name = "temp_battery")
	@Getter
	@Setter
	private Float tempBattery;

	@Column
	@Getter
	@Setter
	private Integer pressure;

	@Column
	@Getter
	@Setter
	private Integer humidity;

	@Column(name = "wind_speed")
	@Getter
	@Setter
	private Float windSpeed;

	@Column(name = "bat_voltage")
	@Getter
	@Setter
	private Float batteryVoltage;

	@Getter
	@Setter
	private LocalDateTime created;
	

}
