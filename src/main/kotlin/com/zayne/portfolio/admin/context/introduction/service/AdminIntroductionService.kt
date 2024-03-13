package com.zayne.portfolio.admin.context.introduction.service


import com.zayne.portfolio.admin.context.introduction.form.IntroductionForm
import com.zayne.portfolio.admin.data.TableDTO
import com.zayne.portfolio.domain.entity.Introduction
import com.zayne.portfolio.domain.repository.IntroductionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AdminIntroductionService (
        private val introductionRepository: IntroductionRepository
){
    fun getIntroductionTable(): TableDTO {
        val classInfo = Introduction::class
        val entities = introductionRepository.findAll()
        return TableDTO.from(classInfo, entities)
    }
    @Transactional
    fun save(form: IntroductionForm) {
        val introduction = form.toEntity()
        introductionRepository.save(introduction)
    }
    @Transactional
    fun update(id: Long, form: IntroductionForm) {
        val introduction = form.toEntity(id)
        introductionRepository.save(introduction)
    }
}