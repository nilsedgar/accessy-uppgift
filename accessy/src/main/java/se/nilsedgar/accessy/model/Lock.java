package se.nilsedgar.accessy.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "lock")
public class Lock {

    @Id
    private UUID id;

    private String name;

    public Lock() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
