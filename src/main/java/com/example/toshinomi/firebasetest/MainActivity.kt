package com.example.toshinomi.firebasetest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG: String = "MainActivity"
    }
    private lateinit var m_btnCreate: Button
    private lateinit var m_btnSignIn: Button
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
            var strEmail = textEmail.text.toString()
            var strPassword = textPassword.text.toString()
            createUser(strEmail, strPassword)
        }

        m_btnSignIn = findViewById(R.id.buttonSignIn)
        m_btnSignIn?.setOnClickListener {
            var textEmail = findViewById<EditText>(R.id.editTextEmail)
            var textPassword = findViewById<EditText>(R.id.editTextPassword)
            var strEmail = textEmail.text.toString()
            var strPassword = textPassword.text.toString()
            signIn(strEmail, strPassword)
        }
    }

    fun createUser(_strEmail: String, _strPassword: String) {
        m_Auth?.createUserWithEmailAndPassword(_strEmail, _strPassword)?.addOnCompleteListener { task: Task<AuthResult> ->
            var textViewLoginEmail = findViewById<TextView>(R.id.textViewLoginEmail)
            var textBiewLoginStatus = findViewById<TextView>(R.id.textViewLoginStatus)
            textViewLoginEmail.text = ""
            textBiewLoginStatus.text = ""
            if (task.isSuccessful) {
                textBiewLoginStatus.text = "Login success"
                textViewLoginEmail.text = _strEmail
            } else {
                signIn(_strEmail, _strPassword)
            }
        }
    }

    fun signIn(_strEmail: String, _strPassword: String) {
        m_Auth?.signInWithEmailAndPassword(_strEmail, _strPassword)?.addOnCompleteListener { task: Task<AuthResult> ->
            var textViewLoginEmail = findViewById<TextView>(R.id.textViewLoginEmail)
            var textBiewLoginStatus = findViewById<TextView>(R.id.textViewLoginStatus)
            textViewLoginEmail.text = ""
            textBiewLoginStatus.text = ""
            if (task.isSuccessful) {
                textBiewLoginStatus.text = "Login success"
                textViewLoginEmail.text = _strEmail
            } else {
                textBiewLoginStatus.text = "Login fail"
            }
        }
    }
}