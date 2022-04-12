package com.xanaxiel.app.router;

import com.xanaxiel.app.model.Foo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@RestController
@ResponseBody
@Slf4j
public class FooController {

    @GetMapping(value = "/echo", produces = "text/event-stream")
    public Flux<?> echo() {
        return Flux.just(Foo.defaultFoo())
                .repeat()
                .delayElements(Duration.of(1, ChronoUnit.SECONDS))
                .doOnEach(f -> log.info("Sending: {}", f));
    }
}
