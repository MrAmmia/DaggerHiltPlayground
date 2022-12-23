package net.thebookofcode.www.daggerhiltplayground.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import net.thebookofcode.www.daggerhiltplayground.R
import net.thebookofcode.www.daggerhiltplayground.databinding.ActivityMainBinding
import net.thebookofcode.www.daggerhiltplayground.entities.Blog
import net.thebookofcode.www.daggerhiltplayground.model.MainStateEvent
import net.thebookofcode.www.daggerhiltplayground.model.MainViewModel
import net.thebookofcode.www.daggerhiltplayground.util.DataState

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "AppDebug"
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeObservers()
        viewModel.setStateEvent(MainStateEvent.GetBlogEvents)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    appendBlogTitle(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun displayError(message: String?) = with(binding) {
        if (message != null) {
            text.text = message
        } else {
            text.text = "An Unknown Error Occurred!!!"
        }
    }

    private fun displayProgressBar(isDisplayed: Boolean) = with(binding) {
        progressBar.visibility = if (isDisplayed) View.VISIBLE else View.GONE
    }

    private fun appendBlogTitle(blogs: List<Blog>) = with(binding) {
        val sb = StringBuilder()
        for (blog in blogs) {
            sb.append(blog.title + "\n")
        }
        text.text = sb.toString()
    }
}