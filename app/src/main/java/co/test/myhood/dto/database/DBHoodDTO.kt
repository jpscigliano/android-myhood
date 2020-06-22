package co.test.myhood.dto.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DBHoodDTO(
    @PrimaryKey val id: String,
    val name: String,
    val imageURL: String
) {

}