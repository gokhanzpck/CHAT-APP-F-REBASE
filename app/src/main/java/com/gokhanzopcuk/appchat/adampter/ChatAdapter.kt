package com.gokhanzopcuk.appchat.adampter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gokhanzopcuk.appchat.R
import com.gokhanzopcuk.appchat.model.Chat

class ChatAdapter(var mContext: Context):RecyclerView.Adapter<ChatAdapter.ChatHolder>(){
    class ChatHolder(itemView: View):RecyclerView.ViewHolder(itemView)



    private val diffUtil=object :DiffUtil.ItemCallback<Chat>(){
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
           return oldItem==newItem
        }
    }
    private val recylerListDiffer=AsyncListDiffer(this,diffUtil)



    var chats:List<Chat>
        get() =recylerListDiffer.currentList
            set(value)=recylerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val view=LayoutInflater.from(mContext).inflate(R.layout.recyler_row,parent,false)
        return ChatHolder(view)
    }
    override fun getItemCount(): Int {
return chats.size
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        val chat = chats.get(position)
        val t =holder.itemView
        t.findViewById<TextView>(R.id.textView3).text = chat.user
        t.findViewById<TextView>(R.id.textView5).text = chat.text
    }
}