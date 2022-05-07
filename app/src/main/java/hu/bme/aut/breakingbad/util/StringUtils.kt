package hu.bme.aut.breakingbad.util

object StringUtils {

    fun comaSeparate(list: List<Int>?) = list.orEmpty().joinToString(", ") { it.toString() }

    fun lineSeparate(list: List<String>?) = list.orEmpty().joinToString("\n")
}