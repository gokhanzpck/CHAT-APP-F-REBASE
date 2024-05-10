package com.gokhanzopcuk.appchat.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.gokhanzopcuk.appchat.R
import com.gokhanzopcuk.appchat.databinding.FragmentLoginBinding
import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth
private lateinit var binding: FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth=Firebase.auth
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?, ): View? {
       binding= FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentUser=auth.currentUser
        if (currentUser!=null){
            Navigation.findNavController(requireView()).navigate(R.id.loginHomePageGecis)
        }
        binding.girisButton.setOnClickListener {
            val email=binding.editTextText.text.toString()
            val sifre=binding.editTextText2.text.toString()
            if (binding.editTextText.text.toString().isEmpty()||binding.editTextText2.text.toString().isEmpty()){
                Toast.makeText(requireContext(),"sifre veya email boş olamaz",Toast.LENGTH_LONG).show()
            }else{
                auth.signInWithEmailAndPassword(email,sifre).addOnCompleteListener{task->
                    if (task.isSuccessful){
                        Navigation.findNavController(it).navigate(R.id.loginHomePageGecis)
                    }else{
                        Toast.makeText(requireContext(),task.exception?.localizedMessage,Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        binding.kayTButton.setOnClickListener {
            val email=binding.editTextText.text.toString()
            val sifre=binding.editTextText2.text.toString()
            if (binding.editTextText.text.toString().isEmpty()||binding.editTextText2.text.toString().isEmpty()){
                Toast.makeText(requireContext(),"sifre veya email boş olamaz",Toast.LENGTH_LONG).show()
            }else{
                auth.signInWithEmailAndPassword(email,sifre).addOnCompleteListener{task->
                    if (task.isSuccessful){
                        Toast.makeText(requireContext(),"kayıt başarılı",Toast.LENGTH_LONG).show()
                        binding.editTextText.setText("")
                        binding.editTextText2.setText("")
                    }else{
                        Toast.makeText(requireContext(),task.exception?.localizedMessage,Toast.LENGTH_LONG).show()
                    }
                }
            }
        }




    }
}