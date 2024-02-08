package com.zayne.portfolio.domain.repository

import com.zayne.portfolio.domain.entity.Experience
import com.zayne.portfolio.domain.entity.ExperienceDetail
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExperienceRepositoryTest(
    @Autowired val experienceRepository: ExperienceRepository
) {
    val DATA_SIZE = 10

    private fun createExperience(n: Int): Experience {
        val experience = Experience(
            title = "${n}",
            description = "test description ${n}",
            startYear = 2023,
            startMonth = 9,
            endYear = 2023,
            endMonth = 9,
            isActive = true
        )

        val details = mutableListOf<ExperienceDetail>()
        for (i in 1..n) {
            val experienceDetail = ExperienceDetail(content = "test ${i}", isActive = true)
            details.add(experienceDetail)
        }
        experience.addDetails(details)

        return experience
    }

    @BeforeAll
    fun beforeALL() {
        println("----- Before initializing test data start -----")
        var beforeInitialize = experienceRepository.findAll()
        assertThat(beforeInitialize).hasSize(0)
        println("----- Before initializing test data end -----")

        println("----- initializing test data start -----")
        val experiences = mutableListOf<Experience>()
        for (i in 1..DATA_SIZE) {
            val experience = createExperience(i)
            experiences.add(experience)
        }
        experienceRepository.saveAll(experiences)
        println("----- initializing test data end -----")
    }

    @Test
    fun testFindAll() {
        println("----- FindAll test start -----")
        val experiences = experienceRepository.findAll()
        assertThat(experiences).hasSize(DATA_SIZE)

        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.title.toInt())
        }
        println("----- FindAll test end -----")
    }

    @Test
    fun testFindAllByIsActive() {
        println("----- FindAllByIsActive test start -----")
        val experiences = experienceRepository.findAllByIsActive(true)
        assertThat(experiences).hasSize(DATA_SIZE)

        for (experience in experiences) {
            assertThat(experience.details).hasSize(experience.title.toInt())
        }
        println("----- FindAllByIsActive test end -----")
    }

}