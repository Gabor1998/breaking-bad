package hu.bme.aut.breakingbad.datasource.database

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class Converters {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @TypeConverter
    fun jsonToList(json: String): List<String> = moshi.adapter<List<String>>(Types.newParameterizedType(List::class.java, String::class.javaObjectType)).fromJson(json).orEmpty()

    @TypeConverter
    fun listToJson(list: List<String>): String = moshi.toJson(list)

    @TypeConverter
    fun jsonToListInt(json: String): List<Int> = moshi.adapter<List<Int>>(Types.newParameterizedType(List::class.java, Int::class.javaObjectType)).fromJson(json).orEmpty()

    @TypeConverter
    fun intListToJson(list: List<Int>): String = moshi.toJson(list)
}

inline fun <reified T> Moshi.toJson(value: T): String = this.adapter(T::class.java).toJson(value)