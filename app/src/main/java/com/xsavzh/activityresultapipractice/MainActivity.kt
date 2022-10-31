package com.xsavzh.activityresultapipractice

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import com.xsavzh.activityresultapipractice.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val requestPermissionLauncher = registerForActivityResult(RequestPermission()) {
        Log.d(MainActivity::class.java.simpleName, "Permission granted: $it")
        if (it) {
            Toast.makeText(this, "Permission granted", Toast.LENGTH_LONG)
        }
    }

    private val editMessageLauncher = registerForActivityResult(SecondActivity.Contract()) {
        Log.d(MainActivity::class.java.simpleName, "Edit result: $it")
        if (it.confirmed) {
            binding.valueTextView.text = it.message
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.requestPermissionBtn.setOnClickListener { requestPermission() }
        binding.editBtn.setOnClickListener { editMessage() }
    }

    private fun requestPermission() {
        requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    private fun editMessage() {
        editMessageLauncher.launch(binding.valueTextView.text.toString())
    }
}