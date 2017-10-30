package org.voelk.vacation

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class VacationApplication

fun main(args: Array<String>) {
    SpringApplication.run(VacationApplication::class.java, *args)
}
