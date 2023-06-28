package com.icicaf.apiuserdata.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

@Data
@Builder
public class User {
    @NonNull
    private UUID id;
    @NonNull
    private String name;
    @NonNull
    private String lastName;
}
