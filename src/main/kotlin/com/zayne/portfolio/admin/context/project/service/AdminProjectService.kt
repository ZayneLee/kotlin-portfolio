package com.zayne.portfolio.admin.context.project.service

import com.zayne.portfolio.admin.data.TableDTO
import com.zayne.portfolio.admin.exception.AdminBadRequestException
import com.zayne.portfolio.domain.entity.Project
import com.zayne.portfolio.domain.entity.ProjectDetail
import com.zayne.portfolio.domain.repository.ProjectRepository
import org.springframework.stereotype.Service

@Service
class AdminProjectService(
        private val projectRepository: ProjectRepository
) {
    fun getProjectTable(): TableDTO {
        val classInfo = Project::class
        val entities = projectRepository.findAll()

        return TableDTO.from(classInfo, entities, "details", "skills")
    }

    fun getProjectDetailTable(id: Long?): TableDTO {
        val classInfo = ProjectDetail::class
        val entities = if (id != null) projectRepository.findById(id)
                .orElseThrow{throw AdminBadRequestException("No such as ${id} data")}
                .details else emptyList()

        return TableDTO.from(classInfo, entities)
    }
}