package com.zayne.portfolio.domain.repository

import com.zayne.portfolio.domain.entity.Instruction
import org.springframework.data.jpa.repository.JpaRepository

interface IntroductionRepository : JpaRepository<Instruction, Long>