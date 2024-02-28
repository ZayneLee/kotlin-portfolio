package com.zayne.portfolio.admin.context.project.form

import jakarta.validation.constraints.NotBlank

data class ProjectSkillForm(

        @field:NotBlank(message = "Not Blank")
        val project: String,

        @field:NotBlank(message = "Not Blank")
        val skill: String,

        )