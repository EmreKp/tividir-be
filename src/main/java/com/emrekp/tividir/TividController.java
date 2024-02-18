package com.emrekp.tividir;

import jakarta.servlet.http.HttpSession;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "${FRONTEND_URL}", allowCredentials = "true")
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
        tivid.setSentAt(ZonedDateTime.now(ZoneOffset.UTC));
        repository.save(tivid);

        return ResponseEntity.created(null).body(new Success(true));
    }

    @PostMapping("/session")
    public ResponseEntity<Success> startSession(@RequestBody OAuthUser user, HttpSession session) {
        session.setAttribute("username", user.username());

        return ResponseEntity.ok(new Success(true));
    }

    @GetMapping("/session")
    public ResponseEntity<OAuthUser> getUser(HttpSession session) {
        Object username = session.getAttribute("username");

        if (username == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new OAuthUser(username.toString()));
    }

    @DeleteMapping("/session")
    public ResponseEntity<Void> stopSession(HttpSession session) {
        session.invalidate();

        return ResponseEntity.noContent().build();
    }
}
