package ru.pvorobey.checkrunnerrestful.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.pvorobey.checkrunnerrestful.entity.Card;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByNumber(String number);
}
