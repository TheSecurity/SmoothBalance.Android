package sirsecurity.smoothbalance.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_money_item.view.*
import sirsecurity.smoothbalance.MainActivity
import sirsecurity.smoothbalance.models.MoneyItemModel
import sirsecurity.smoothbalance.R
import sirsecurity.smoothbalance.activities.EditMoneyItemActivity

class MoneyItemAdapter(val context: Context) : RecyclerView.Adapter<MoneyItemAdapter.MoneyItemViewHolder>()
{
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var moneyItems = emptyList<MoneyItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoneyItemViewHolder {
        val itemView = inflater.inflate(R.layout.row_money_item, parent, false)
        return MoneyItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return moneyItems.count()
    }

    override fun onBindViewHolder(holder: MoneyItemViewHolder, position: Int) {
        val current = moneyItems[position]
        holder.moneyItemName.text = current.name
        holder.moneyItemPrice.text = current.price.toString()

        if(current.state == "Outcome"){
            holder.moneyItemPrice.setText("- ${current.price}")
            holder.moneyItemPrice.setTextColor(Color.RED)
        }
        else{
            holder.moneyItemPrice.setText("+ ${current.price}")
            holder.moneyItemPrice.setTextColor(Color.GREEN)
        }


        holder.moneyItemName.setOnClickListener {
            if(context is MainActivity)
                context.updateItem(current)
        }
        holder.moneyItemPrice.setOnClickListener {
            if(context is MainActivity)
                context.updateItem(current)
        }
        holder.btnDeleteItem.setOnClickListener {
            if(context is MainActivity)
                context.deleteItem(current)
        }

    }

    fun setMoneyItems(moneyItems: List<MoneyItemModel>)
    {
        this.moneyItems = moneyItems
        notifyDataSetChanged()
    }

    class MoneyItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val moneyItemName: TextView = itemView.txtName
        val moneyItemPrice: TextView = itemView.txtPrice
        val btnDeleteItem : ImageView = itemView.btnDeleteItem
    }
}