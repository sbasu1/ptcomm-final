package org.launchcode.ptcommunications.repository;

import org.launchcode.ptcommunications.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}