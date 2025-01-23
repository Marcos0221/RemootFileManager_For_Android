package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.FileListAdapter
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.entity.Files
import com.example.myapplication.tool.RemoteFileManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var viewbinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewbinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewbinding.root)

        var list: ArrayList<Files>? = null

        runBlocking {
            val job = GlobalScope.async {
                val fileManager = RemoteFileManager()
                list = fileManager.getFileList()
            }
            job.await()
        }

        // 判断有无所有文件管理权限 否则调转设置
        if (!Environment.isExternalStorageManager()) {
            startActivity(Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION))
        }

        val adapter: FileListAdapter? = FileListAdapter(this, list)
        val layoutManager = LinearLayoutManager(this)
        viewbinding.filelistView.layoutManager = layoutManager
        viewbinding.filelistView.adapter = adapter
    }


}