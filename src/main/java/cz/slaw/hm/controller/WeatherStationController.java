package cz.slaw.hm.controller;

import cz.slaw.hm.domain.WeatherStationProbeEntity;
import cz.slaw.hm.dto.DataDto;
import cz.slaw.hm.service.WeatherStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/weather/")
public class    WeatherStationController {

    @Autowired
    WeatherStationService service;

    @RequestMapping(method = RequestMethod.GET, value = "/last")
    public WeatherStationProbeEntity last() {
        return service.getLast();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<WeatherStationProbeEntity> findAll() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/filter/{param}/{size}")
    public List<DataDto> filter(@PathVariable(required = true, value = "param") final String parameter, @PathVariable(value = "size") final Integer size) {
        return service.getDataForParam(parameter, size);
    }
}
