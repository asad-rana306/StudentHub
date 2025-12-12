package com.Student.Hub.Entity;

import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Collection;
import java.util.List;

@Data
@Document (collection = "user")
public class User {
    @Id
    private ObjectId id;
    private String email;
    @NonNull
    private String password;
    private List<String> roles;
    @NonNull
    @Indexed(unique = true)
    private String userName;
}
