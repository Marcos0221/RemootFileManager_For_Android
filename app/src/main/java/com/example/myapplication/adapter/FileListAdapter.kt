package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.entity.Files
import com.google.android.material.textview.MaterialTextView

class FileListAdapter(private val context: Context, private val list: ArrayList<Files>?) : RecyclerView.Adapter<FileListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.filelist_item, parent, false);
        val viewHolder = FileListViewHolder(view)
        return  viewHolder
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: FileListViewHolder, position: Int) {
        val files = list?.get(position)
        holder.fileName.text = files?.filename
        holder.fileSize.text = files?.filesize.toString()
        holder.modifyTime.text = files?.modifyTime.toString()
    }
}

class FileListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val fileName: MaterialTextView = itemView.findViewById<MaterialTextView>(R.id.file_name)
    val modifyTime: MaterialTextView = itemView.findViewById<MaterialTextView>(R.id.modify_time)
    val fileSize: MaterialTextView = itemView.findViewById<MaterialTextView>(R.id.file_size)
}
