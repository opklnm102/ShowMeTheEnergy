package me.dong.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import me.dong.domain.Lank;
import me.dong.domain.MonthUsage;

@RestController
@RequestMapping("api/monthusage")
public class MonthUsageController {

	ArrayList<Lank> lankList = new ArrayList<>();

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<MonthUsage> postMonthUsage(@RequestHeader String uuid, @RequestBody List<MonthUsage> monthUsages,
			UriComponentsBuilder uriBuilder) {

		int size = monthUsages.size();

		MonthUsage beforeData = monthUsages.get(size - 2);
		MonthUsage currentData = monthUsages.get(size - 1);
		
		Long point = (((beforeData.getUnitPeriodUsage() - currentData.getUnitPeriodUsage()) * 10));
		
		Lank lank = new Lank();
		lank.setUuid(uuid);
		lank.setPoint(point);
		
		lankList.add(lank);
		
		//point순으로 오름차순 정렬
		
		Collections.sort(lankList, new Comparator<Lank>() {

					@Override
					public int compare(Lank o1, Lank o2) {
						return (o1.getPoint() < o2.getPoint()) ? -1 : (o1.getPoint() > o2.getPoint()) ? 1 : 0;
					}
		});
		
		return new ResponseEntity<>(HttpStatus.CREATED);		
	}

	@RequestMapping(method = RequestMethod.GET)
	List<Lank> getMonthUsage() {
		return lankList;
	}
}
