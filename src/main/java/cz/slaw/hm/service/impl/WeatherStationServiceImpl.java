package cz.slaw.hm.service.impl;

import cz.slaw.hm.domain.WeatherStationProbeEntity;
import cz.slaw.hm.dto.DataDto;
import cz.slaw.hm.repository.WeatherStationDao;
import cz.slaw.hm.service.WeatherStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherStationServiceImpl implements WeatherStationService {

    @Autowired
    WeatherStationDao repository;

    @Override
    @Transactional(readOnly = true)
    public WeatherStationProbeEntity getLast() {
        return repository.getLast();
    }

    @Override
    @Transactional(readOnly = true)
    public List<DataDto> getDataForParam(final String parameter, Integer pageSize) {
        List<DataDto> res = new ArrayList<>();
        Long count = repository.count();
        int start = 0;
        if (count > pageSize) {
            start = count.intValue() - pageSize;
        }
        for (WeatherStationProbeEntity entity : repository.findAll(start)) {
            DataDto dto = new DataDto();
            dto.setDate(entity.getCreated());
            switch (parameter) {
                case "tempExt":
                    dto.setValue(entity.getTempExternal());
                    break;
                case "pressure":
                    dto.setValue(entity.getPressure());
                    break;
                case "wind":
                    dto.setValue(entity.getWindSpeed());
                    break;
                case "humidity":
                    dto.setValue(entity.getHumidity());
                    break;
                case "tempBat":
                    dto.setValue(entity.getTempBattery());
                    break;
                case "batVol":
                    dto.setValue(entity.getBatteryVoltage());
                    break;
                default:
                    break;
            }
            res.add(dto);
        }
        return res;
    }
}
