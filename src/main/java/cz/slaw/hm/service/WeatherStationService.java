package cz.slaw.hm.service;

import cz.slaw.hm.domain.WeatherStationProbeEntity;
import cz.slaw.hm.dto.DataDto;

import java.util.List;

public interface WeatherStationService {

	WeatherStationProbeEntity getLast();

	List<DataDto> getDataForParam(String parameter, Integer size);

}
