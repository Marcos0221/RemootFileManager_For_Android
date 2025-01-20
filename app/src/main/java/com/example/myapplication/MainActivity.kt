package com.example.myapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.FileListAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.entity.Files

class MainActivity : AppCompatActivity() {

    private lateinit var viewbinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)

        val file1 = Files().apply {
            filename = "C语言注释规范.md"
            filesize = 1024*16
            modifyTime = 1737383767
        }

        val file2 = Files().apply {
            filename = "嵌入式的神奇魔法.md"
            filesize = 1024*32
            modifyTime = 1737383793
        }

        val file3 = Files().apply {
            filename = "HelloWorld.md"
            filesize = 1024*64
            modifyTime = 1737383798
        }

        val list: ArrayList<Files> = ArrayList()
        list.add(file1)
        list.add(file2)
        list.add(file3)

        val adapter = FileListAdapter(this, list)
        val layoutManager = LinearLayoutManager(this)
        viewbinding.filelistView.layoutManager = layoutManager
        viewbinding.filelistView.adapter = adapter
    }
}