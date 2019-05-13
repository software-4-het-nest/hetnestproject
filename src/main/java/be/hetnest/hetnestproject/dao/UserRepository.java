package be.hetnest.hetnestproject.dao;

import be.hetnest.hetnestproject.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}