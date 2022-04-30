package hu.bme.aut.breakingbad.util

suspend fun <T> safeApiCall(apiCall: suspend () -> T): Result<T> {
    return try {
        Result.success(apiCall())
    } catch (ex: Exception) {
        Result.failure(ex)
    }
}
