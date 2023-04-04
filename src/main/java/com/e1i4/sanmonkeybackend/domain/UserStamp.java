package com.e1i4.sanmonkeybackend.domain;

import com.e1i4.sanmonkeybackend.utils.BaseTimeEntity;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class UserStamp extends BaseTimeEntity {
    @Id
    @Column(name = "user_stamp_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stamp_id")
    private Stamp stamp;

    public UserStamp(User user, Stamp stamp) {
        super();
        this.user = user;
        this.stamp = stamp;
    }
}
