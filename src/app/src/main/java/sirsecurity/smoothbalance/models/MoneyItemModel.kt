package sirsecurity.smoothbalance.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "moneyItems")
class MoneyItemModel(
    @ColumnInfo(name = "name")
    @NotNull
    var name : String,

    @ColumnInfo(name = "price")
    @NotNull
    var price : Double,

    @ColumnInfo(name = "eventDate")
    @NotNull
    var eventDate : String,

    @ColumnInfo(name = "state")
    @NotNull
    var state: String,

    @ColumnInfo(name = "description")
    var description : String

): Serializable
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id : Int = 0
}