package com.e1i4.sanmonkeybackend.repository;

import com.e1i4.sanmonkeybackend.domain.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StampRepository extends JpaRepository<Stamp, Long> {
}
