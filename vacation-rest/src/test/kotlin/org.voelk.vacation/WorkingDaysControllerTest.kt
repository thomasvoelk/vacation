package org.voelk.vacation


import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.*
import org.skyscreamer.jsonassert.*
import org.springframework.beans.factory.annotation.*
import org.springframework.boot.test.context.*
import org.springframework.boot.test.web.client.*
import org.springframework.http.*
import org.springframework.test.context.junit.jupiter.*
import org.springframework.web.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = arrayOf(VacationApplication::class),
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WorkingDaysControllerTest {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @Test
    fun whenWorkingDaysCalled_shouldReturnResult() {
        val uri = UriComponentsBuilder.fromPath("/days/working")
                .queryParam("start", "2017-01-01")
                .queryParam("end", "2017-01-01")
                .build().toUri()
        val result = testRestTemplate.getForEntity(uri, String::class.java)

        assertThat(result.statusCode, `is`(HttpStatus.OK))
        JSONAssert.assertEquals("{\"days\":[],\"count\":0.0}", result.body, false)
    }
}