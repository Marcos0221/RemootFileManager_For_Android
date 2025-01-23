package com.example.myapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.entity.Files
import com.example.myapplication.tool.RemoteFileManager
import com.google.android.material.textview.MaterialTextView

class FileListAdapter(private val context: Context, private val list: ArrayList<Files>?) :
    RecyclerView.Adapter<FileListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.filelist_item, parent, false);
        return FileListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    override fun onBindViewHolder(holder: FileListViewHolder, position: Int) {
        val files = list?.get(position)
        holder.fileName.text = files?.filename
        holder.fileSize.text = files?.filesize.toString()
        holder.modifyTime.text = files?.modifyTime.toString()

        holder.item.setOnClickListener {
            Toast.makeText(context, "点击了列表: $position", Toast.LENGTH_SHORT).show()
        }

        holder.item.setOnClickListener(View.OnClickListener {

        })

        holder.item.setOnLongClickListener {
            val popupMenu = PopupMenu(context, holder.item)
            popupMenu.menu.add("重命名")
            popupMenu.menu.add("下载")
            popupMenu.menu.add("删除")

            popupMenu.setOnMenuItemClickListener { item: MenuItem ->
                when(item.title){
                    "下载" -> {
                        RemoteFileManager().downloadRemoteFile(holder.fileName.text.toString())
                    }
                }
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
            return@setOnLongClickListener true
        }
    }
}

class FileListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val fileName: MaterialTextView = itemView.findViewById<MaterialTextView>(R.id.file_name)
    val modifyTime: MaterialTextView = itemView.findViewById<MaterialTextView>(R.id.modify_time)
    val fileSize: MaterialTextView = itemView.findViewById<MaterialTextView>(R.id.file_size)
    val item: LinearLayout = itemView.findViewById<LinearLayout>(R.id.item)
}
