package com.finadv.entities;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    private Timestamp modifiedAt;
    private Timestamp createdAt;
}
