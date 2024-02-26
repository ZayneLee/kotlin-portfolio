package com.zayne.portfolio.admin.context.project.controller

import com.zayne.portfolio.admin.context.project.service.AdminProjectSkillService
import com.zayne.portfolio.admin.data.FormElementDTO
import com.zayne.portfolio.admin.data.SelectFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/admin/project/skill")
class AdminProjectSkillViewController(
        private val adminProjectSkillService: AdminProjectSkillService
) {
    @GetMapping
    fun projectSkill(model: Model): String {
        val projectList = adminProjectSkillService.getProjectList()
        val skillList = adminProjectSkillService.getSkillList()

        val elements = listOf<FormElementDTO>(
                SelectFormElementDTO("project", 8, projectList),
                SelectFormElementDTO("skill", 4, skillList),
        )
        model.addAttribute("elements", elements)

        val table = adminProjectSkillService.getProjectSkillTable()
        model.addAttribute("table", table)
        model.addAttribute("detailTable", null)

        val pageAttributes = mutableMapOf<String, Any>(
                Pair("menuName", "Projects"),
                Pair("pageName", table.name),
                Pair("editable", false),
                Pair("deletable", true),
                Pair("hasDetails", false)
        )
        model.addAllAttributes(pageAttributes)
        return "admin/page-table"
    }
}