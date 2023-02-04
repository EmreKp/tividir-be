package com.emrekp.tividir;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TividRepository extends MongoRepository<Tivid, String> {

}
