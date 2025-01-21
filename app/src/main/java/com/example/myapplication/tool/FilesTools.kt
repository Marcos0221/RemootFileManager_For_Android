package com.example.myapplication.tool

import com.example.myapplication.entity.Files
import org.json.JSONArray

class FilesTools {

    fun toArrayList(string: String): ArrayList<Files>? {
        val list: ArrayList<Files> = ArrayList()
        val jsonArray = JSONArray(string)
        for (i in 0 until jsonArray.length()) {
            val jsonObject  = jsonArray.getJSONObject(i)
            val files = Files()
            files.filename = jsonObject.getString("fileName")
            files.filesize = jsonObject.getString("fileSize")
            files.modifyTime = jsonObject.getString("modifyTime")
            list.add(files)
        }
        return list
    }

}