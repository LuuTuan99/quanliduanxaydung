package com.fpt.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Date estimatedDate;
    private String contractors;
    private String description;
    private int status;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "construction_id")
    private Construction construction;

    public enum Status {
        ACTIVE(1), DEACTIVE(0),DELETED(-1);

        private int value;

        Status(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
