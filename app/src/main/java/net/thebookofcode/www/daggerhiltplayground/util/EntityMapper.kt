package net.thebookofcode.www.daggerhiltplayground.util

interface EntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity):DomainModel

    fun mapToEntity(domainModel: DomainModel):Entity
}