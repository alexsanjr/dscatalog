package com.alexsanjr.dscatalog.repositories;

import com.alexsanjr.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
