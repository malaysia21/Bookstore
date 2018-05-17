package com.puzia.bookstore.db.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(
        name = "tb_status"
)
public class Status {

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

}
