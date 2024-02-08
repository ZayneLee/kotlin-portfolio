package com.zayne.portfolio.domain

import com.zayne.portfolio.domain.constant.SkillType
import com.zayne.portfolio.domain.entity.*
import com.zayne.portfolio.domain.repository.*
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
@Profile(value = ["default"])
class DataInitializer(
    private val achievementRepository: AchievementRepository,
    private val introductionRepository: IntroductionRepository,
    private val linkRepository: LinkRepository,
    private val skillRepository: SkillRepository,
    private val projectRepository: ProjectRepository,
    private val experienceRepository: ExperienceRepository
) {
    @PostConstruct
    fun initializeData() {

        val achievements = mutableListOf<Achievement>(
            Achievement(
                title = "Engineer Information Processing",
                description = "Information Processing Engineer is a certification in South Korea validating competency in various aspects of information technology, crucial for career advancement in the IT industry.",
                host = "HRD Korea",
                achievedDate = LocalDate.of(2020, 8, 28),
                isActive = true
            ),
            Achievement(
                title = "GTQ(Graphic Technology Qualification)",
                description = "GTQ is a certification that verifies competency in graphic design and technology skills",
                host = "Korea Productivity Center",
                achievedDate = LocalDate.of(2020, 6, 12),
                isActive = true
            ),
            Achievement(
                title = "SQL Developer",
                description = "SQLD is a certification that validates proficiency in SQL and database development skills",
                host = "Korea Data Agency",
                achievedDate = LocalDate.of(2019, 12, 31),
                isActive = true
            )
        )
        achievementRepository.saveAll(achievements)

        val introductions = mutableListOf<Introduction>(
            Introduction(
                content = "Specializing in creating and maintaining server-side logic, databases, and applications that power websites and web applications.",
                isActive = true
            ),
            Introduction(
                content = "Working with programming languages such as Java, Kotlin, JavaScript and Node.js, along with framework like Spring to build robust and scalable systems.",
                isActive = true
            ),
            Introduction(
                content = "Collaborating closely with front-end developers and other stakeholders to ensure seamless integration and functionality of web-based projects.",
                isActive = true
            )
        )
        introductionRepository.saveAll(introductions)

        val links = mutableListOf<Link>(
            Link(name = "Github", content = "https://github.com/ZayneLee", isActive = true),
            Link(name = "Linkedin", content = "https://www.linkedin.com/in/seongdo-lee-a86160278/", isActive = true),
        )
        linkRepository.saveAll(links)

        val experience1 = Experience(
            title = "Korea National Open University",
            description = "Computer Science",
            startYear = 2016,
            startMonth = 9,
            endYear = 2021,
            endMonth = 2,
            isActive = true,
        )
        experience1.addDetails(
            mutableListOf(
                ExperienceDetail(content = "GPA 3.8/4.5", isActive = true)
            )
        )

        val experience2 = Experience(
            title = "Ezfarm Inc.",
            description = "development team, Full-Stack developer",
            startYear = 2022,
            startMonth = 10,
            endYear = 2023,
            endMonth = 5,
            isActive = true,
        )

        experience2.addDetails(
            mutableListOf(
                ExperienceDetail(
                    content = "Full-stack development, specializing in backend infrastructure and RESTful API design",
                    isActive = true
                ),
                ExperienceDetail(
                    content = "Proficient in utilizing Spring Security for secure member registration processes",
                    isActive = true
                ),
                ExperienceDetail(
                    content = "Front-end development using React and TypeScript for a seamless user experience",
                    isActive = true
                ),
                ExperienceDetail(
                    content = "Strong communication skills, including fluent English proficiency",
                    isActive = true
                )
            )
        )

        val experience3 = Experience(
            title = "Soonchunhyang University Hospital",
            description = "IT Service team, Full-Stack developer",
            startYear = 2021,
            startMonth = 7,
            endYear = 2022,
            endMonth = 9,
            isActive = true,
        )

        experience3.addDetails(
            mutableListOf(
                ExperienceDetail(
                    content = "Extensive experience in troubleshooting application issues within Oracle databases, ensuring uninterrupted hospital operations",
                    isActive = true
                ),
                ExperienceDetail(
                    content = "In-depth knowledge of IT service management principles with a focus on optimizing healthcare IT infrastructure",
                    isActive = true
                ),
                ExperienceDetail(
                    content = "Strong analytical and problem-solving skills, consistently delivering timely and effective solutions to complex issues",
                    isActive = true
                )
            )
        )

        val experience4 = Experience(
            title = "Trinion",
            description = "development team, Full-Stack developer",
            startYear = 2020,
            startMonth = 6,
            endYear = 2021,
            endMonth = 6,
            isActive = true,
        )

        experience4.addDetails(
            mutableListOf(
                ExperienceDetail(
                    content = "Customer support and issue resolution with a commitment to timely and effective solutions",
                    isActive = true
                ),
                ExperienceDetail(
                    content = "Expertise in designing and implementing member management systems using the Spring Framework",
                    isActive = true
                ),
                ExperienceDetail(
                    content = "Strong analytical and problem-solving skills, consistently delivering timely and effective solutions to complex issues",
                    isActive = true
                )
            )
        )

        val experience5 = Experience(
            title = "Atec Group",
            description = "LG CNS team, Unix Linux System Administrator",
            startYear = 2019,
            startMonth = 10,
            endYear = 2020,
            endMonth = 1,
            isActive = true,
        )

        experience5.addDetails(
            mutableListOf(
                ExperienceDetail(
                    content = "Proficient in monitoring server traffic to optimize performance and identify potential issues",
                    isActive = true
                ),
                ExperienceDetail(
                    content = "Extensive experience in managing x86 servers, ensuring hardware integrity and operational efficiency",
                    isActive = true
                )
            )
        )

        experienceRepository.saveAll(mutableListOf(experience1, experience2, experience3, experience4, experience5))

        val java = Skill(name = "Java", type = SkillType.LANGUAGE.name, isActive = true)
        val kotlin = Skill(name = "Kotlin", type = SkillType.LANGUAGE.name, isActive = true)
        val react = Skill(name = "React", type = SkillType.LANGUAGE.name, isActive = true)
        val typescript = Skill(name = "TypeScript", type = SkillType.FRAMEWORK.name, isActive = true)
        val spring = Skill(name = "Spring", type = SkillType.FRAMEWORK.name, isActive = true)
        val oracle = Skill(name = "Oracle", type = SkillType.DATABASE.name, isActive = true)
        val mysql = Skill(name = "MySQL", type = SkillType.DATABASE.name, isActive = true)
        val docker = Skill(name = "Docker", type = SkillType.DATABASE.name, isActive = true)
        skillRepository.saveAll(
            mutableListOf(
                java, kotlin, react, typescript, spring, oracle, mysql, docker
            )
        )

        val project1 = Project(
            name = "Plant Quarantine Information Management System Project",
            description = "Participated in Sri Lanka agricultural export and import quarantine project jointly conducted by UN-affiliated UNOPS",
            startYear = 2022,
            startMonth = 10,
            endYear = 2023,
            endMonth = 5,
            isActive = true
        )
        project1.addDetails(
            mutableListOf(
                ProjectDetail(
                    content = "Specializing in backend infrastructure and RESTful API design",
                    url = null,
                    isActive = true
                ),
                ProjectDetail(
                    content = "Front-end development using React and TypeScript for a seamless user experience",
                    url = null,
                    isActive = true
                )
            )
        )
        project1.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project1, skill = java),
                ProjectSkill(project = project1, skill = spring),
                ProjectSkill(project = project1, skill = react),
                ProjectSkill(project = project1, skill = typescript),
                ProjectSkill(project = project1, skill = mysql),
                ProjectSkill(project = project1, skill = docker)
            )
        )

        val project2 = Project(
            name = "Zayne's Portfolio",
            description = "Crafted my own portfolio using React",
            startYear = 2022,
            startMonth = 12,
            endYear = null,
            endMonth = null,
            isActive = true
        )

        project2.addDetails(
            mutableListOf(
                ProjectDetail(
                    content = "Github Repository",
                    url = "https://github.com/ZayneLee/react-portfolio",
                    isActive = true
                )
            )
        )

        project2.skills.addAll(
            mutableListOf(
                ProjectSkill(project = project2, skill = react)
            )
        )
        projectRepository.saveAll(mutableListOf(project1, project2))
    }
}