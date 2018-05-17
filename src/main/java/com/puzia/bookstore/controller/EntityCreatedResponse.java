package com.puzia.bookstore.controller;

import lombok.Value;

@Value(staticConstructor = "fromId")
public class EntityCreatedResponse {

    int entityId;

}
