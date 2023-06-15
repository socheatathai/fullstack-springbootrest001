package code.withme.fullstackbankend.repsitory;

import code.withme.fullstackbankend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
