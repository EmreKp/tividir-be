package com.emrekp.tividir;

import java.time.ZonedDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tivids")
public class Tivid {
  @Id
  private String id;

  private String text;
  private ZonedDateTime sentAt;

  public String getId() {
    return id;
  }

  public Tivid setId(String id) {
    this.id = id;

    return this;
  }

  public String getText() {
    return text;
  }

  public Tivid setText(String text) {
    this.text = text;

    return this;
  }

  public ZonedDateTime getSentAt() {
    return sentAt;
  }

  public Tivid setSentAt(ZonedDateTime sentAt) {
    this.sentAt = sentAt;

    return this;
  }
}
