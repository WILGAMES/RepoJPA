package edu.co.icesi.examjpatemplate.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.co.icesi.examjpatemplate.entity.GeoPoint;

import java.util.List;

@Repository
public interface GeoPointRepository extends JpaRepository<GeoPoint, Integer>{
    List<GeoPoint> findAllByBus_LicensePlateOrderByTimestampAsc(String licensePlate);
    GeoPoint findFirstByBusLicensePlateOrderByTimestampDesc(String licensePlate);
    List<GeoPoint> findAllByBus_Route_RouteName(String routeName);
}
