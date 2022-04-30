package hu.bme.aut.breakingbad.domain

import hu.bme.aut.breakingbad.repository.QuoteRepository
import javax.inject.Inject

class GetRandomQuoteByAuthor @Inject constructor(
    private val quoteRepository: QuoteRepository
) {

    suspend operator fun invoke(author: String?) = quoteRepository.getRandomQuoteByAuthor(author)
}