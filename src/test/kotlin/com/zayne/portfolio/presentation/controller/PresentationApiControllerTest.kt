package com.zayne.portfolio.presentation.controller

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.json.JSONArray
import org.json.JSONObject
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import java.nio.charset.StandardCharsets

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("[API Controller Test]")
class PresentationApiControllerTest(
    @Autowired private val mockMvc: MockMvc
) {

    @Test
    @DisplayName("Search Introductions")
    fun testGetIntroductions() {
        // given
        val url = "/api/v1/introductions"

        // when
        val mvcResult = performGet(url)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonArray = JSONArray(contentAsString)

        // then
        assertThat(jsonArray.length()).isPositive()
    }

    @Test
    @DisplayName("Search Links")
    fun testGetLinks() {
        // given
        val url = "/api/v1/links"

        // when
        val mvcResult = performGet(url)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonArray = JSONArray(contentAsString)

        // then
        assertThat(jsonArray.length()).isPositive()
    }

    @Test
    @DisplayName("Search Resume")
    fun testGetResume() {
        // given
        val url = "/api/v1/resume"

        // when
        val mvcResult = performGet(url)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonObject = JSONObject(contentAsString)

        // then
        assertThat(jsonObject.optJSONArray("experiences").length()).isPositive()
        assertThat(jsonObject.optJSONArray("achievements").length()).isPositive()
        assertThat(jsonObject.optJSONArray("skills").length()).isPositive()
    }

    @Test
    @DisplayName("Search Projects")
    fun testGetProjects() {
        // given
        val url = "/api/v1/projects"

        // when
        val mvcResult = performGet(url)
        val contentAsString = mvcResult.response.getContentAsString(StandardCharsets.UTF_8)
        val jsonArray = JSONArray(contentAsString)

        // then
        assertThat(jsonArray.length()).isPositive()
    }


    private fun performGet(url: String): MvcResult {
        return mockMvc
            .perform(MockMvcRequestBuilders.get(url))
            .andDo(MockMvcResultHandlers.print())
            .andReturn()
    }
}