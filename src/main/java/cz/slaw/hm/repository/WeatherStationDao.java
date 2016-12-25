package cz.slaw.hm.repository;

import cz.slaw.hm.domain.WeatherStationProbeEntity;

import java.util.List;

public interface WeatherStationDao {

	List<WeatherStationProbeEntity> findAll(int start);

	WeatherStationProbeEntity getLast();

	Long count();
}
