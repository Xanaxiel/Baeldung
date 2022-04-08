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

import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@ResponseBody
@Slf4j
public class FooController {

    @GetMapping(value = "/echo", produces = "text/event-stream")
    public Flux<?> echo() {
        final Foo foo = Foo.defaultFoo();
        return Flux.push(emitter -> {
            while (!emitter.isCancelled()) {
                log.info("Emitting: {}", foo);
                emitter.next(ok(foo));
            }
            emitter.onCancel(() -> {
                log.info("Cancelled");
                emitter.complete();
            });
        }).delaySubscription(Duration.of(2L, ChronoUnit.SECONDS));
    }
}
