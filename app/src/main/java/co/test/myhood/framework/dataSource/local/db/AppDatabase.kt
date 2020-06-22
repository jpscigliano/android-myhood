package co.test.myhood.framework.dataSource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import co.test.myhood.dto.database.DBHoodDTO

@Database(entities = [DBHoodDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun hoodsDao(): HoodsDao
}