package com.novandi.cookingmama

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.novandi.cookingmama.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val foodData = ArrayList<Food>()

    companion object {
        const val EXTRA_FOOD = "extra_food"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        title = "Recipe Detail"

        val food = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FOOD, Food::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FOOD)
        }

        if (food != null) {
            foodData.add(food)

            binding.tvDetailPhoto.setImageResource(food.photo)
            binding.tvDetailName.text = food.name
            binding.tvDetailDescription.text = food.description
            binding.tvDetailIngredient.text = food.ingredient
            binding.tvDetailStep.text = food.step
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.action_share -> {
            shareRecipe()
            // Toast.makeText(this, foodData[0].name, Toast.LENGTH_SHORT).show()
            true
        }
        else -> { super.onOptionsItemSelected(item) }
    }

    private fun shareRecipe() {
        val sendRecipe: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Cara Membuat ${foodData[0].name}\n\nBahan :\n${foodData[0].ingredient}\n\nStep by step :\n${foodData[0].step}")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendRecipe, null)
        startActivity(shareIntent)
    }
}