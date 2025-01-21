package com.example.myapplication.tool

import com.example.myapplication.entity.Files
import okhttp3.OkHttpClient
import okhttp3.Request

class RemoteFileManager {

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

}