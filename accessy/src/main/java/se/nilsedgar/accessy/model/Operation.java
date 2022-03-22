package se.nilsedgar.accessy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "operation")
public class Operation {

    @Id
    private UUID id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lock_id")
    @JsonIgnore
    private Lock lock;

    public Operation() {
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

    public Lock getLock() {
        return lock;
    }

    public void setLock(Lock lock) {
        this.lock = lock;
    }
}
