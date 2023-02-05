package com.emrekp.tividir;

import java.time.ZonedDateTime;
import java.util.Date;
import org.springframework.core.convert.converter.Converter;

public class MongoTimeWriteConverter implements Converter<ZonedDateTime, Date> {

  @Override
  public Date convert(ZonedDateTime zonedDateTime) {
    return Date.from(zonedDateTime.toInstant());
  }
}
