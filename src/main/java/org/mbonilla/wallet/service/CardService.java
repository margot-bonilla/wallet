package org.mbonilla.wallet.service;

import org.mbonilla.wallet.model.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CardService {
    void save(Card user);

    List<Card> findByNumberAndUser(Long number, Long ownerId);

    List<Card> findAllByNumber(Long number);

    Card findByNumber(Long number);

    void delete(Long id);
}
