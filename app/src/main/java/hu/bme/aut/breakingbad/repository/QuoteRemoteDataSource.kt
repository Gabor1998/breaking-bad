package hu.bme.aut.breakingbad.repository

import hu.bme.aut.breakingbad.datasource.network.BreakingBadApi
import hu.bme.aut.breakingbad.model.toQuote
import hu.bme.aut.breakingbad.util.safeApiCall
import javax.inject.Inject

class QuoteRemoteDataSource @Inject constructor(
    private val breakingBadApi: BreakingBadApi
) {

    suspend fun getRandomQuoteByAuthor(author: String?) = safeApiCall {
        breakingBadApi.getRandomQuoteByAuthor(author).map { it.toQuote() }
    }
}