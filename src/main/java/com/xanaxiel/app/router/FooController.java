package com.xanaxiel.app.router;

import com.xanaxiel.app.model.Foo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@RestController
@Slf4j
public class FooController {

    @GetMapping("/echo")
    public Flux<?> echo() {
        final Foo foo = Foo.defaultFoo();
        return Flux.push(emitter -> {
            while (!emitter.isCancelled()) {
                log.info("Emitting: {}", foo);
                emitter.next(foo);
            }
            emitter.onCancel(() -> {
                log.info("Cancelled");
                emitter.complete();
            });
        }).delaySubscription(Duration.of(2L, ChronoUnit.SECONDS));
    }
}
