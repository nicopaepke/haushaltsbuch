package npaepke.haushaltsbuch.models.timeseries;

import java.io.IOException;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import lombok.extern.slf4j.Slf4j;

@Converter
@Slf4j
public class TimeSeriesConverter implements AttributeConverter<List<TimeSeriesDataPoint>, String> {
    @Override
    public String convertToDatabaseColumn(List<TimeSeriesDataPoint> timeSeriesDataPoints) {
        try {
            return getObjectMapper().writeValueAsString(timeSeriesDataPoints);
        } catch (JsonProcessingException e) {
            log.error("Failed to set time series data, because of {}", e.getMessage(), e);
            return null;
        }
    }

    @Override
    public List<TimeSeriesDataPoint> convertToEntityAttribute(String dbData) {
        try {
            return (List<TimeSeriesDataPoint>)getObjectMapper().readValue(dbData, Object.class);
        } catch (IOException e) {
            log.error("Failed to read time series data, because of {}", e.getMessage(), e);
            return null;
        }
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        mapper.registerModule(new JodaModule());
        return mapper;
    }
}
