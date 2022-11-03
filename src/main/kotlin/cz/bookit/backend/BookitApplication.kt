package cz.bookit.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookitApplication

fun main(args: Array<String>) {
	runApplication<BookitApplication>(*args)
}
