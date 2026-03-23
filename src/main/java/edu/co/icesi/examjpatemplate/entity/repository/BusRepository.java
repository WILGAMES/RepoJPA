package edu.co.icesi.examjpatemplate.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.co.icesi.examjpatemplate.entity.Bus;


@Repository
public interface BusRepository extends JpaRepository<Bus, Integer>{

    List<Bus> findAllByRoute_RouteName(String routeName);

    List<Bus> findAllByRoute_Type(String type);
}
