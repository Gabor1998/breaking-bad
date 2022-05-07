package hu.bme.aut.breakingbad.repository

import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val quoteRemoteDataSource: QuoteRemoteDataSource
) {

    suspend fun getRandomQuoteByAuthor(author: String?) = quoteRemoteDataSource.getRandomQuoteByAuthor(author).map { it.firstOrNull() }
}