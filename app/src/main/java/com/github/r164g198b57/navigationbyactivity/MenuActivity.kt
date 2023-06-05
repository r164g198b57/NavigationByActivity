package com.github.r164g198b57.navigationbyactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.github.r164g198b57.navigationbyactivity.databinding.ActivityMenuBinding
import com.github.r164g198b57.navigationbyactivity.model.Options

class MenuActivity : BaseActivity() {

//    https://www.youtube.com/watch?v=Q7ZZOIvQCFA&t=1038s&ab_channel=RomanAndrushchenko
    private lateinit var binding: ActivityMenuBinding
    private lateinit var options: Options

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater).also { setContentView(it.root) }

        binding.openBoxButton.setOnClickListener { onOpenBoxPressed() }
        binding.optionsButton.setOnClickListener { onOptionsPressed() }
        binding.aboutButton.setOnClickListener { onAboutPressed() }
        binding.exitButton.setOnClickListener {  finish() }

        options = savedInstanceState?.getParcelable(KEY_OPTIONS) ?: Options.DEFAULT     // android 7(
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(KEY_OPTIONS, options)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == OPTIONS_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            options = data?.getParcelableExtra(OptionsActivity.EXTRA_OPTIONS) ?:
                throw IllegalArgumentException("Can't get the updated data from options activity")
        }
    }

    private fun onOpenBoxPressed() {
        val intent = Intent(this, BoxSelectionActivity::class.java)
        intent.putExtra(BoxSelectionActivity.EXTRA_OPTIONS, options)
        startActivity(intent)
    }

    private fun onOptionsPressed() {
        val intent = Intent(this, OptionsActivity::class.java)
        intent.putExtra(OptionsActivity.EXTRA_OPTIONS, options)
        startActivityForResult(intent, OPTIONS_REQUEST_CODE)
    }

    private fun onAboutPressed() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }



    private companion object {
        const val KEY_OPTIONS = "OPTIONS"
        const val OPTIONS_REQUEST_CODE = 1
    }
}