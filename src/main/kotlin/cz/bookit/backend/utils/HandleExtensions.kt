package cz.bookit.backend.utils

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import java.util.Optional

fun <T> Optional<T>.takeIfFound(className: String) : T {
    takeIf {
        it.isPresent
    } ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND,
        className.substringAfterLast(".") + " was not found."
    )
    return get()
}