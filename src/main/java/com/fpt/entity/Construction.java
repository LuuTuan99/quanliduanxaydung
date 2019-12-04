package com.fpt.entity;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Construction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Date estimatedDate;
    private String reason;
    private int status;

    @OneToMany(mappedBy = "construction", cascade = CascadeType.ALL)
    private Set<Category> categories = new HashSet<>();

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
