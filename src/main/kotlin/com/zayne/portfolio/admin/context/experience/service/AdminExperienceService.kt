package com.zayne.portfolio.admin.context.experience.service

import com.zayne.portfolio.admin.data.TableDTO
import com.zayne.portfolio.admin.exception.AdminBadRequestException
import com.zayne.portfolio.domain.entity.Experience
import com.zayne.portfolio.domain.entity.ExperienceDetail
import com.zayne.portfolio.domain.repository.ExperienceRepository
import org.springframework.stereotype.Service

@Service
class AdminExperienceService (
    private val experienceRepository: ExperienceRepository
){
    fun getExperienceTable():TableDTO {
        val classInfo = Experience::class
        val entities = experienceRepository.findAll()

        return TableDTO.from(classInfo, entities, "details")
    }

    fun getExperienceDetailTable(id: Long?): TableDTO {
        val classInfo = ExperienceDetail::class
        val entities = if (id != null) experienceRepository.findById(id)
            .orElseThrow {throw AdminBadRequestException("no such as ${id}")}
            .details else emptyList()

        return TableDTO.from(classInfo, entities)
    }
}