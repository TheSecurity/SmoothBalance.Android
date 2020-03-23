package sirsecurity.smoothbalance.activities

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import sirsecurity.smoothbalance.R
import kotlinx.android.synthetic.main.activity_edit_money_item.*
import kotlinx.android.synthetic.main.content_edit_money_item.*
import kotlinx.android.synthetic.main.content_edit_money_item.view.*
import sirsecurity.smoothbalance.models.MoneyItemModel
import java.text.SimpleDateFormat
import java.util.*

class EditMoneyItemActivity : AppCompatActivity() {

    var itemStates = listOf("Income", "Outcome")
    private var moneyItem: MoneyItemModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_money_item)
        setSupportActionBar(toolbar)

        val adapter : ArrayAdapter<String> = ArrayAdapter(this, R.layout.spinner_money_item, itemStates)

        spinnerPaymentType.adapter = adapter

        moneyItem = intent.getSerializableExtra("MONEY_ITEM") as? MoneyItemModel
        moneyItem?.let {
            txtActivityName.setText("UPDATE ITEM")
            btnAdd.setText("UPDATE")
            txtDate.setText(it.eventDate)
            txtDescription.setText(it.description)
            txtName.setText(it.name)
            txtPrice.setText(it.price.toString())
            spinnerPaymentType.setSelection(itemStates.indexOf(it.state))
        } ?: run {
            txtActivityName.setText("ADD ITEM")
            btnAdd.setText("ADD")
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH) + 1
            val day = c.get(Calendar.DAY_OF_MONTH)

            txtDate.setText("$day. $month. $year")
        }

        txtDate.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus){
                showDatePickerDialog()
            }
            v.clearFocus()
        }
    }

    fun btnClick(view: View){
        moneyItem?.let{
            it.name = txtName.text.toString()
            it.price = txtPrice.text.toString().toDouble()
            it.description = txtDescription.text.toString()
            it.eventDate = txtDate.text.toString()
            it.state = spinnerPaymentType.selectedItem.toString()
        } ?: run{
           moneyItem = MoneyItemModel(name = txtName.text.toString(), price = txtPrice.text.toString().toDouble(), description = txtDescription.text.toString(), eventDate = txtDate.text.toString(), state = spinnerPaymentType.selectedItem.toString())
        }

        val intent = Intent()
        intent.putExtra("MONEY_ITEM", moneyItem)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun showDatePickerDialog(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

            val monthCount = monthOfYear + 1
            txtDate.setText("$dayOfMonth. $monthCount. $year")

        }, year, month, day)

        dpd.show()
    }

}
