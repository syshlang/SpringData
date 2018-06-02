package com.syshlang;

import com.syshlang.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepsotory  extends JpaRepository<User,Long> {
    User getByLastName(String aa);
}
