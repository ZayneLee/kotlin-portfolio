package com.zayne.portfolio.admin.context.introduction.form

import com.zayne.portfolio.domain.entity.Introduction
import jakarta.validation.constraints.NotBlank

data class IntroductionForm(

        @field:NotBlank(message = "Not Blank")
        val content: String,

        val isActive: Boolean
) {
    fun toEntity(): Introduction {
        return Introduction(
                content = this.content,
                isActive = this.isActive
        )
    }

    fun toEntity(id: Long): Introduction {
        val introduction = this.toEntity()
        introduction.id = id

        return introduction
    }
}