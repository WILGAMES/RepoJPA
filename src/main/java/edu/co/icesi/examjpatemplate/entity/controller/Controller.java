package edu.co.icesi.examjpatemplate.entity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.co.icesi.examjpatemplate.entity.Bus;
import edu.co.icesi.examjpatemplate.entity.Route;
import edu.co.icesi.examjpatemplate.entity.repository.BusRepository;
import edu.co.icesi.examjpatemplate.entity.repository.GeoPointRepository;
import edu.co.icesi.examjpatemplate.entity.GeoPoint;


@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private BusRepository busRepository;
    @Autowired
    private GeoPointRepository geoPointRepository;

    @GetMapping("/{routeName}")
    public List<Bus> getAllBusesByRoute(@PathVariable String routeName) {
        return busRepository.findAllByRoute_RouteName(routeName);
    }

    @GetMapping("/geopoints/{licensePlate}")
    public List<GeoPoint> getAllGeoPointsByBus(@PathVariable String licensePlate) {
        return geoPointRepository.findAllByBus_LicensePlateOrderByTimestampAsc(licensePlate);
    }

    @GetMapping("/geopoint/{licensePlate}")
    public GeoPoint getLastGeopPointByLicense(@PathVariable String licensePlate){
        return geoPointRepository.findFirstByBusLicensePlateOrderByTimestampDesc(licensePlate);
    }

    @GetMapping("/buses/{type}")
    public List<Bus> getAllBusesByType(@PathVariable String type) {
        return busRepository.findAllByRoute_Type(type);
    }

    @GetMapping("/geopoints/buses/route/{routeName}")
    public List<GeoPoint> getAllGeoPointsByRoute(@PathVariable String routeName) {
        return geoPointRepository.findAllByBus_Route_RouteName(routeName);
    }
}
