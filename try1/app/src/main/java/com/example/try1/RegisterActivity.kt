package com.example.try1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.try1.Retrofit.ApiClient
import com.example.try1.Retrofit.Request.RegisterRequest
import com.example.try1.Retrofit.Response.RegisterResponse
import com.example.try1.Retrofit.SessionManager
import com.example.try1.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity()  {
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        binding.btnRegister.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val email = binding.email.text.toString().trim()
            val password = binding.password.text.toString().trim()

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val registerRequest = RegisterRequest(name, email, password)

            apiClient.getApiService(this).register(registerRequest).enqueue(object :
                Callback<RegisterResponse> {
                override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
                ) {
                    if (response.isSuccessful) {
                        val registerResponse = response.body()
                        if (registerResponse != null && registerResponse.success) {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Register berhasil",
                                Toast.LENGTH_SHORT
                            ).show()

                            toLogin()

                        } else {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Register gagal",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Register gagal",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    Toast.makeText(this@RegisterActivity, "Register gagal", Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }

        binding.tvLogin.setOnClickListener {
            toLogin()
        }
    }

    private fun toLogin() {
        Intent(applicationContext, LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }
}