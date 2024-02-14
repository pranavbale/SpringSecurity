package com.pranavbale.security.respository;

import com.pranavbale.security.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, UUID> {
    UserInfo findByUserName(String userName);
}
