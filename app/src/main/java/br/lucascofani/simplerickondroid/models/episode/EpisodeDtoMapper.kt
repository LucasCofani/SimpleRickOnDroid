package br.lucascofani.simplerickondroid.models.episode

import br.lucascofani.simplerickondroid.util.DomainMapper
import br.lucascofani.simplerickondroid.util.DateUtil

class EpisodeDtoMapper : DomainMapper<EpisodeDto, Episode> {
    override fun mapToDomainModel(model: EpisodeDto): Episode {
        return Episode(
            name = model.name,
            air_date = DateUtil.stringToDate(model.air_date),
            episode = model.episode,
            characters = model.characters,
            url = model.url,
            created = DateUtil.stringToDate(model.created),
            id = model.id
        )
    }

    override fun mapFromDomainModel(domainModel: Episode): EpisodeDto {
        return EpisodeDto(
            name = domainModel.name,
            air_date = DateUtil.dateToString(domainModel.air_date),
            episode = domainModel.episode,
            characters = domainModel.characters,
            url = domainModel.url,
            created = DateUtil.dateToString(domainModel.created),
            id = domainModel.id
        )
    }

    override fun toDomainList(initial: List<EpisodeDto>): List<Episode> {
        return initial.map { mapToDomainModel(it) }
    }

    override fun fromDomainList(initial: List<Episode>): List<EpisodeDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}