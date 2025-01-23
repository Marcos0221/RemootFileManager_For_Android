package com.example.myapplication.tool

import android.os.Environment
import android.util.Log
import com.example.myapplication.entity.Files
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class RemoteFileManager {

    val TAG = "RemoteFileManager"

    // 获取远程文件列表
    fun getFileList(): ArrayList<Files>? {
        var responseBody: String? = null
        val client = OkHttpClient()
        val request = Request.Builder().apply {
            url("http://192.168.2.3:8080/file/list")
        }.build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            responseBody = response.body?.string()
        } else {
            error("获取远程文件列表失败")
        }
        if (responseBody != null) {
            return FilesTools().toArrayList(responseBody)
        }
        return null
    }

    // 下载网络文件
    fun downloadRemoteFile(filename: String) {
        val client = OkHttpClient()
        val request = Request.Builder().apply {
            get()
            url("http://192.168.2.3:8080/file/download/$filename")
        }.build()

        val call = client.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i(TAG, "onFailure: 下载失败")
            }

            override fun onResponse(call: Call, response: Response) {
                val file = File(
                    Environment.getExternalStorageDirectory().absolutePath +
                            "/Dav/$filename"
                )
                val inStream = response.body?.byteStream()
                if (!file.exists()) {
                    file.createNewFile()
                } else {
                    file.delete()
                    file.createNewFile()
                }
                val outStream = FileOutputStream(file)
                val buf = ByteArray(1024 * 1024)
                var len = 0
                while (true) {
                    len = inStream!!.read(buf)
                    if (len == -1) {
                        break
                    }
                    outStream.write(buf, 0, len)
                }
                outStream.flush()
                inStream.close()
                outStream.close()
            }
        })
    }

    fun RemoteFileRename(fileName: String, newName: String) {
        val client = OkHttpClient()
        val request = Request.Builder().apply {
            url("http://192.168.2.3:8080/file/rename/$fileName?newName=$newName")
        }.build()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            Log.i(TAG, "RemoteFileRename: 重命名成功")
        }

    }

}