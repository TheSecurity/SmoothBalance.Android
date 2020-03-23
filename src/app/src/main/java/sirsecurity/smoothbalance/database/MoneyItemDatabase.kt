package sirsecurity.smoothbalance.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import sirsecurity.smoothbalance.models.MoneyItemModel

@Database(entities = arrayOf(MoneyItemModel::class), version = 3, exportSchema = false)
abstract class MoneyItemDatabase : RoomDatabase() {

    abstract fun recipeDao() : MoneyItemDao

    companion object{

        private var INSTANCE : MoneyItemDatabase? = null

        fun getDatabase(context: Context) : MoneyItemDatabase
        {
            val tmpInstance = INSTANCE

            if(tmpInstance != null)
                return tmpInstance

            synchronized(this){

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MoneyItemDatabase::class.java,
                    "money_items_database")
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }

    }

}