package sirsecurity.smoothbalance

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import sirsecurity.smoothbalance.activities.EditMoneyItemActivity
import sirsecurity.smoothbalance.adapters.MoneyItemAdapter
import sirsecurity.smoothbalance.models.MoneyItemModel
import sirsecurity.smoothbalance.viewmodels.MoneyItemViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var moneyItemViewModel: MoneyItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val adapter = MoneyItemAdapter(this)
        rvMoneyItems.adapter = adapter
        rvMoneyItems.layoutManager = LinearLayoutManager(this)

        //View manager creation
        moneyItemViewModel = ViewModelProvider(this).get(MoneyItemViewModel::class.java)
        moneyItemViewModel.allMoneyItems.observe(this, Observer { moneyItems ->
            moneyItems?.let {
                adapter.setMoneyItems(it)
            }
        })

        btnAdd.setOnClickListener {
            val intent = Intent(this, EditMoneyItemActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val moneyItem: MoneyItemModel = data!!.getSerializableExtra("MONEY_ITEM") as MoneyItemModel

            when (requestCode) {
                100 -> {
                    moneyItemViewModel.insert(moneyItem)
                }
                200 -> {
                    moneyItemViewModel.update(moneyItem)
                }
                else -> return
            }
        }
    }

    fun deleteItem(moneyItem: MoneyItemModel)
    {
        moneyItemViewModel.delete(moneyItem)
    }

    fun updateItem(moneyItemModel: MoneyItemModel){
        val intent = Intent(this, EditMoneyItemActivity::class.java)
        intent.putExtra("MONEY_ITEM", moneyItemModel)
        startActivityForResult(intent, 200)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
