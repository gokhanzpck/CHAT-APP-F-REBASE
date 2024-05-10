package com.gokhanzopcuk.appchat.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gokhanzopcuk.appchat.R
import com.gokhanzopcuk.appchat.adampter.ChatAdapter
import com.gokhanzopcuk.appchat.databinding.FragmentHomePageBinding
import com.gokhanzopcuk.appchat.model.Chat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore

class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var auth: FirebaseAuth
     private lateinit var adapter:ChatAdapter
     private  var chats= arrayListOf<Chat>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore=Firebase.firestore
        auth=Firebase.auth
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
     binding= FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.rv.layoutManager=LinearLayoutManager(requireContext())
        adapter= ChatAdapter(requireContext())
        binding.rv.adapter=adapter


        binding.button.setOnClickListener {


            auth.currentUser?.let {user->
                val chatText=binding.editTextText3.text.toString()
                val date=FieldValue.serverTimestamp()
                val dataMap=HashMap<String,Any>()
                val user=user.email
                dataMap.put("text",chatText)
                dataMap.put("user",user!!)
                dataMap.put("date",date)

                firestore.collection("Chats").add(dataMap).addOnCompleteListener{task->
                    if (task.isSuccessful){
                        binding.editTextText3.setText("")
                    }else{
                        Toast.makeText(requireContext(),task.exception?.localizedMessage,Toast.LENGTH_LONG).show()
                    }
                }
            }
        }







        firestore.collection("Chats").orderBy("date",Query.Direction.ASCENDING).addSnapshotListener{value,error->
            if (error!=null){
                Toast.makeText(requireContext(),error.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if (value!=null){
                    if (!value.isEmpty){
                        val documents=value.documents
                        chats.clear()
                        for (document in documents){
                            val text=document.get("text")as String
                            val user=document.get("user") as String
                            val chat =Chat(user,text)
                            chats.add(chat)
                            adapter.chats=chats
                        }
                    }
                    adapter.notifyDataSetChanged()
                }

            }


        }



        super.onViewCreated(view, savedInstanceState)
    }

}