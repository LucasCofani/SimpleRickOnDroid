package br.lucascofani.simplerickondroid.models.location

import br.lucascofani.simplerickondroid.util.DomainMapper
import br.lucascofani.simplerickondroid.util.DateUtil

class LocationDtoMapper: DomainMapper<LocationDto, Location> {
    override fun mapToDomainModel(model: LocationDto): Location {
        return Location(
        name = model.name,
        type = model.type,
        dimension = model.dimension,
        residents = model.residents,
        url = model.url,
        created = DateUtil.stringToDate(model.created),
        id = model.id
        )
    }

    override fun mapFromDomainModel(domainModel: Location): LocationDto {
        return LocationDto(
            name = domainModel.name,
            type = domainModel.type,
            dimension = domainModel.dimension,
            residents = domainModel.residents,
            url = domainModel.url,
            created = DateUtil.dateToString(domainModel.created),
            id = domainModel.id
        )
    }

    override fun toDomainList(initial: List<LocationDto>): List<Location> {
        return initial.map { mapToDomainModel(it) }
    }

    override fun fromDomainList(initial: List<Location>): List<LocationDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}