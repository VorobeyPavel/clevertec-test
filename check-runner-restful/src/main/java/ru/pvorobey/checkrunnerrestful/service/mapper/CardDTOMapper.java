package ru.pvorobey.checkrunnerrestful.service.mapper;

import ru.pvorobey.checkrunnerrestful.dto.CardDTO;
import ru.pvorobey.checkrunnerrestful.entity.Card;

import java.util.List;

public interface CardDTOMapper {

    CardDTO toCardDTO(Card card);

    List<CardDTO> toListCardDTO(List<Card> cards);
}
