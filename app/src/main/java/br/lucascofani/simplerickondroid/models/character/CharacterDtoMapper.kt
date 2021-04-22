package br.lucascofani.simplerickondroid.models.character

import br.lucascofani.simplerickondroid.util.DomainMapper
import br.lucascofani.simplerickondroid.util.DateUtil

class CharacterDtoMapper : DomainMapper<CharacterDto, Character> {
    override fun mapToDomainModel(model: CharacterDto): Character {
        return Character(
            name = model.name,
            status = model.status,
            species = model.species,
            type = model.type,
            gender = model.gender,
            origin = model.origin,
            location = model.location,
            image = model.image,
            episode = model.episode,
            url = model.url,
            created = DateUtil.stringToDate(model.created),
            id = model.id,
        )
    }

    override fun mapFromDomainModel(domainModel: Character): CharacterDto {
        return CharacterDto(
            name = domainModel.name,
            status = domainModel.status,
            species = domainModel.species,
            type = domainModel.type,
            gender = domainModel.gender,
            origin = domainModel.origin,
            location = domainModel.location,
            image = domainModel.image,
            episode = domainModel.episode,
            url = domainModel.url,
            created = DateUtil.dateToString(domainModel.created),
            id = domainModel.id,
        )
    }

    override fun toDomainList(initial: List<CharacterDto>): List<Character> {
        return initial.map { mapToDomainModel(it) }
    }

    override fun fromDomainList(initial: List<Character>): List<CharacterDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}