package com.zayne.portfolio.domain.entity

import jakarta.persistence.*

@Entity
class Introduction(
    content: String,
    isActive: Boolean
) : BaseEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instruction_id")

    var id: Long? = null

    var content: String = content

    var isActive: Boolean = isActive
}