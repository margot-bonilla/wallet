package org.mbonilla.wallet.service;

import org.mbonilla.wallet.model.Card;
import org.mbonilla.wallet.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    private CardRepository cardRepository;

    @Override
    public void save(Card card) {
        cardRepository.save(card);
    }

    @Override
    public List<Card> findByNumberAndUser(Long number, Long ownerId) {
        return cardRepository.findByNumberAndOwner(number, ownerId);
    }

    @Override
    public List<Card> findAllByNumber(Long number) {
        return cardRepository.findAllByNumber(number);
    }

    @Override
    public Card findByNumber(Long number) {
        return cardRepository.findByNumber(number);
    }

    @Override
    public void delete(Long id) {
        cardRepository.deleteById(id);
    }
}
