package org.voelk.vacation


import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.util.UriComponentsBuilder

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
        val result = testRestTemplate.getForEntity(uri, Double::class.java)

        assertEquals(HttpStatus.OK, result?.statusCode)
        assertEquals(0.0, result?.body)
    }
}