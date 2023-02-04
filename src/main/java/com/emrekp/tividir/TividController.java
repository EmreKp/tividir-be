package com.emrekp.tividir;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TividController {

  private final TividRepository repository;

  public TividController(TividRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/list")
  public ResponseEntity<List<Tivid>> list() {
    List<Tivid> tivids = repository.findAll(Sort.by(Direction.DESC, "id"));

    return ResponseEntity.ok(tivids);
  }

  @PostMapping("/send")
  public ResponseEntity<Success> send(@RequestBody Tivid tivid) {
    repository.save(tivid);

    return ResponseEntity.created(null).body(new Success(true));
  }
}
