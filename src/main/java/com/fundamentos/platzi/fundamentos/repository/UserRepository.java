package com.fundamentos.platzi.fundamentos.repository;

import com.fundamentos.platzi.fundamentos.dto.UserDTO;
import com.fundamentos.platzi.fundamentos.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.email =?1")
    Optional<User> findByUserEmail(String email);

    @Query("SELECT u from User u where u.name like ?1%")
    List<User> findAndSort(String name, Sort sort);

    List<User> findByName(String name);

    Optional<User> findByEmailAndName(String email, String name);

    List<User> findByNameLike(String name);

    List<User> findByNameOrEmail(String name, String email);

    List<User> findByBirthDateBetween(LocalDate start, LocalDate end);

    List<User> findByNameLikeOrderByIdDesc(String name);

    @Query("SELECT new com.fundamentos.platzi.fundamentos.dto.UserDTO(u.id, u.name, u.birthDate) " +
            "FROM User u " +
            "where u.birthDate=:birthDateParam " +
            "and u.email=:emailParam")
    Optional<UserDTO> getAllByBirthDateAndEmail(@Param("birthDateParam") LocalDate birthDate,
                                                @Param("emailParam") String email);
}
