package com.zayne.portfolio.admin.context.project.controller

import com.zayne.portfolio.admin.data.FormElementDTO
import com.zayne.portfolio.admin.context.project.service.AdminProjectService
import com.zayne.portfolio.admin.data.SelectFormElementDTO
import com.zayne.portfolio.admin.data.TextFormElementDTO
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/admin/project")
class AdminProjectViewController(
        private val adminProjectService: AdminProjectService
) {
    @GetMapping
    fun project(model: Model): String {
        val elements = listOf<FormElementDTO>(
                TextFormElementDTO("name", 4),
                TextFormElementDTO("description", 8),
                SelectFormElementDTO("startYear", 3, (2010..2030).toList()),
                SelectFormElementDTO("startMonth", 2, (1..12).toList()),
                SelectFormElementDTO("endYear", 3, (2010..2030).toList()),
                SelectFormElementDTO("endMonth", 2, (1..12).toList()),
                SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString()))
        )
        model.addAttribute("elements", elements)

        val detailElements = listOf<FormElementDTO>(
                TextFormElementDTO("content", 4),
                TextFormElementDTO("url", 6),
                SelectFormElementDTO("isActive", 2, listOf(true.toString(), false.toString()))
        )
        model.addAttribute("detailElements", detailElements)

        val table = adminProjectService.getProjectTable()
        model.addAttribute("table", table)

        val detailTable = adminProjectService.getProjectDetailTable(null)
        model.addAttribute("detailTable", detailTable)

        val pageAttributes = mutableMapOf<String, Any>(
                Pair("menuName", "Projects"),
                Pair("pageName", table.name),
                Pair("editable", true),
                Pair("deletable", false),
                Pair("hasDetails", true)
        )
        model.addAllAttributes(pageAttributes)

        return "admin/page-table"
    }
}