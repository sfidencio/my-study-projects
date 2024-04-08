package com.github.sfidencio.demo.application;

import dev.openfeature.sdk.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyFeatureController {
    private final OpenFeatureAPI openFeatureAPI;

    @GetMapping("/my-feature/{nameFeature}")
    public Object getMyFeature(@PathVariable String nameFeature) {
        final Client client = this.openFeatureAPI.getClient();
        List<Value> values = List.of(new Value(3201), new Value(3202));
        EvaluationContext context = new MutableContext().add("Agency",values);
        return client.getBooleanDetails(nameFeature, false,context);
    }
}
