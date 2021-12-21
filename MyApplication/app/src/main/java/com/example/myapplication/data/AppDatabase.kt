package com.example.myapplication.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [TextEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun textDao(): TextDao

    private val mIsDatabaseCreated = MutableLiveData<Boolean>()

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "myapplication_db"

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppDatabase{
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.textDao())
                }
            }
        }

        suspend fun populateDatabase(textDao: TextDao){
            textDao.insert(TextEntity(null, "Init"))
        }
    }

    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated()
        }
    }

    private fun setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true)
    }

    open fun getDatabaseCreated(): LiveData<Boolean> {
        return mIsDatabaseCreated
    }
}