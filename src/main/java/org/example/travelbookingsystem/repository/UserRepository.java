package org.example.travelbookingsystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.travelbookingsystem.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User getUserById(long id);
}
