package com.example.toshinomi.firebasetest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG: String = "MainActivity"
    }
    private var m_btnCreate: Button? = null
    private var m_btnSignIn: Button? = null
    private lateinit var m_Auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        m_Auth = FirebaseAuth.getInstance()

        InitControl()
    }

    fun InitControl() {
        m_btnCreate = findViewById(R.id.buttonCreate)
        m_btnCreate?.setOnClickListener {
            var textEmail = findViewById<EditText>(R.id.editTextEmail)
            var textPassword = findViewById<EditText>(R.id.editTextPassword)
            var email = textEmail.text.toString()
            var password = textPassword.text.toString()
            m_Auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Authentication successful", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        m_btnSignIn = findViewById(R.id.buttonSignIn)
        m_btnSignIn?.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                //SignIn()
            }
        })
    }
}
