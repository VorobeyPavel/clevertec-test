package ru.pvorobey.checkrunnerrestful.service.mapper.impl;

import org.springframework.stereotype.Component;
import ru.pvorobey.checkrunnerrestful.dto.CardDTO;
import ru.pvorobey.checkrunnerrestful.entity.Card;
import ru.pvorobey.checkrunnerrestful.service.mapper.CardDTOMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardDTOMapperImpl implements CardDTOMapper {

    @Override
    public CardDTO toCardDTO(Card card) {
        return CardDTO.builder()
                .id(card.getId())
                .number(card.getNumber())
                .discount(card.getDiscount())
                .build();
    }

    @Override
    public List<CardDTO> toListCardDTO(List<Card> cards) {
        return cards.stream().map(this::toCardDTO).collect(Collectors.toList());
    }
}
