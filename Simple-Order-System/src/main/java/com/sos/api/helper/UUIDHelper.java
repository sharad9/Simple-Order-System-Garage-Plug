package com.sos.api.helper;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDHelper {

    public UUID generateId() {
        return UUID.randomUUID();
    }
}
