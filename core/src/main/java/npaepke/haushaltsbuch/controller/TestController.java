package npaepke.haushaltsbuch.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import npaepke.haushaltsbuch.models.timeseries.TimeSeries;
import npaepke.haushaltsbuch.models.timeseries.TimeSeriesDataPoint;
import npaepke.haushaltsbuch.repos.TimeSeriesRepository;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TimeSeriesRepository timeSeriesRepository;

    @GetMapping
    public ResponseEntity<List<TimeSeries>> getAccounts(@RequestHeader HttpHeaders httpHeaders) {
        List<TimeSeries> timeSeriesList = timeSeriesRepository.findAll();
        return ResponseEntity.ok(timeSeriesList);
    }

    @PostMapping
    public ResponseEntity<TimeSeries> test(@RequestHeader HttpHeaders httpHeaders) {
        TimeSeries timeSeries = new TimeSeries();
        timeSeries.setReferenceUUID(UUID.randomUUID());
        List<TimeSeriesDataPoint> dataPoints = new ArrayList<>();
        dataPoints.add(new TimeSeriesDataPoint(new DateTime("2020-01-01T00:00:00"), 1.0));
        dataPoints.add(new TimeSeriesDataPoint(new DateTime("2020-01-02T00:00:00"), 1.0));
        dataPoints.add(new TimeSeriesDataPoint(new DateTime("2020-01-03T00:00:00"), 1.0));
        dataPoints.add(new TimeSeriesDataPoint(new DateTime("2020-01-04T00:00:00"), 1.0));
        timeSeries.setDataPoints(dataPoints);
        TimeSeries createdTimeSeries = timeSeriesRepository.save(timeSeries);
        return ResponseEntity.ok(createdTimeSeries);
    }
}
