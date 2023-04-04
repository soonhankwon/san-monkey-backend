package com.e1i4.sanmonkeybackend.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Stamp {

    @Id
    @Column(name = "stamp_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stampId;

    @Column
    private String stampImageUrl;

    public Stamp(String stampImageUrl) {
        this.stampImageUrl = stampImageUrl;
    }

    public String getStampImageUrl() {
        return this.stampImageUrl;
    }
}
