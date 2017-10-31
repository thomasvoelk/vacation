package org.voelk.vacation

import org.springframework.boot.*
import org.springframework.boot.autoconfigure.*


@SpringBootApplication
open class VacationApplication

fun main(args: Array<String>) {
    SpringApplication.run(VacationApplication::class.java, *args)
}
