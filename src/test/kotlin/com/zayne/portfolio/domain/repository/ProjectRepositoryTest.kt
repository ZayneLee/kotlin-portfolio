package com.zayne.portfolio.domain.repository

import com.zayne.portfolio.domain.constant.SkillType
import com.zayne.portfolio.domain.entity.Project
import com.zayne.portfolio.domain.entity.ProjectDetail
import com.zayne.portfolio.domain.entity.ProjectSkill
import com.zayne.portfolio.domain.entity.Skill
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectRepositoryTest(
    @Autowired val projectRepository: ProjectRepository,
    @Autowired val skillRepository: SkillRepository
) {

    val DATA_SIZE = 2

    private fun createProject(n: Int): Project {
        val project = Project(
            name = "${n}",
            description = "테스트 설명 {n}", startYear = 2023,
            startMonth = 9,
            endYear = 2023,
            endMonth = 9,
            isActive = true
        )

        val details = mutableListOf<ProjectDetail>()
        for (i in 1..n) {
            val projectDetail = ProjectDetail(content = "test ${i}", url = null, isActive = true)
            details.add(projectDetail)
        }
        project.addDetails(details)

        val skills = skillRepository.findAll()
        val skillsUsedInProject = skills.subList(0, n)
        for (skill in skillsUsedInProject) {
            val projectSkill = ProjectSkill(project = project, skill = skill)
            project.skills.add(projectSkill)
        }
        return project
    }

    @BeforeAll
    fun beforeAll() {
        println("----- Skill Data initialization start -----")
        val skills = mutableListOf<Skill>()
        for (i in 1..DATA_SIZE) {
            val skillTypes = SkillType.values()
            val skill = Skill(name = "test ${i}", type = skillTypes[i % skillTypes.size].name, isActive = true)
            skills.add(skill)
        }
        skillRepository.saveAll(skills)
        println("----- Skill Data initialization end -----")

        println("----- before data initialization start -----")
        val beforeInsert = projectRepository.findAll()
        assertThat(beforeInsert).hasSize(0)
        println("----- before data initialization end -----")

        println("----- test data initialization start -----")
        val projects = mutableListOf<Project>()
        for (i in 1..DATA_SIZE) {
            val project = createProject(i)
            projects.add(project)
        }
        projectRepository.saveAll(projects)
        println("----- test data initialization end -----")
    }

    @Test
    fun testFindAll() {
        println("----- findAll test start -----")
        val projects = projectRepository.findAll()
        assertThat(projects).hasSize(DATA_SIZE)

        for (project in projects) {
            assertThat(project.details).hasSize(project.name.toInt())
            assertThat(project.skills).hasSize(project.name.toInt())
        }
        println("----- findAll test end -----")
    }

    @Test
    fun testFindAllByIsActive() {
        println("----- FindAllByIsActive test start -----")
        val projects = projectRepository.findAllByIsActive(true)
        assertThat(projects).hasSize(DATA_SIZE)
        for (project in projects) {
            assertThat(project.details).hasSize(project.name.toInt())
            println("project.details.size: ${project.details.size}")
            assertThat(project.skills).hasSize(project.name.toInt())
            println("project.skills.size: ${project.skills.size}")
        }
        println("----- FindAllByIsActive test end -----")
    }
}