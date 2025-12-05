package com.Student.Hub.Entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Data
@Document (collection = "user")
public class User {
    @Id
    private ObjectId id;
    @NonNull
    private String userName;
    private String email;
    @NonNull
    private String password;

}
