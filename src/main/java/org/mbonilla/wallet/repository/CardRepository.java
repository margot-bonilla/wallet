package org.mbonilla.wallet.repository;

import org.mbonilla.wallet.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {

    @Query("SELECT c from Card c WHERE (:number IS NULL OR c.number LIKE CONCAT('%',:number,'%')) AND c.owner.id=:ownerId")
    List<Card> findByNumberAndOwner(@Param("number") Long number, @Param("ownerId") Long ownerId);

    @Query("SELECT c from Card c WHERE :number IS NULL OR c.number LIKE CONCAT('%',:number,'%')")
    List<Card> findAllByNumber(@Param("number") Long number);

    Card findByNumber(@Param("number") Long number);
}
