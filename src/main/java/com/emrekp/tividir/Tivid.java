package com.emrekp.tividir;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tivids")
public record Tivid(@Id String id, String text) {

}
