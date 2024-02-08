package com.zayne.portfolio.domain.repository

import com.zayne.portfolio.domain.constant.SkillType
import com.zayne.portfolio.domain.entity.Link
import com.zayne.portfolio.domain.entity.Skill
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface SkillRepository : JpaRepository<Skill, Long> {
    // select * from skill where is_active = :isActive
    fun findAllByIsActive(isActive: Boolean) : List<Skill>

    // select * from skill where lower(name) = lower(:name) and skill_type = :type
    fun findByNameIgnoreCaseAndType(name: String, type: SkillType) : Optional<Skill>
}