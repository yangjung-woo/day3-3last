package com.example.day3_3last

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {

        // Initialize Firebase Auth
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val click_btn =findViewById<Button>(R.id.btn)
        val click_logout=findViewById<Button>(R.id.log_out)
        val click_login =findViewById<Button>(R.id.login)
        //현재 uid 토스트 출력
        Toast.makeText(this, auth.currentUser?.uid.toString(),Toast.LENGTH_SHORT).show()

        //회원가입 클릭시
        click_btn.setOnClickListener {
            //입력받은 email password를 서버에 회원가입
            val us_em =findViewById<EditText>(R.id.user_email)
            val us_pw =findViewById<EditText>(R.id.user_password)
            auth.createUserWithEmailAndPassword(
                us_em.text.toString(), us_pw.text.toString()
            )//입력받은 email 은 ~.com 형태여야 한다

                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this ,"success" , Toast.LENGTH_SHORT).show()

                    } else {
                        Toast.makeText(this ,"fail" , Toast.LENGTH_SHORT).show()

                    }
                }

        }

        //로그아웃 클릭시
        click_logout.setOnClickListener {
            auth.signOut()
            Toast.makeText(this, auth.currentUser?.uid.toString(),Toast.LENGTH_SHORT).show()
            //null 출력됨 로그아웃 했기때문
        }
        //로그인
        click_login.setOnClickListener {
            val us_em =findViewById<EditText>(R.id.user_email)
            val us_pw =findViewById<EditText>(R.id.user_password)
            auth.signInWithEmailAndPassword(us_em.text.toString(), us_pw.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this ,auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()


                        Toast.makeText(this ,"success" , Toast.LENGTH_SHORT).show()


                    } else {
                        Toast.makeText(this ,"success" , Toast.LENGTH_SHORT).show()


                    }
                }
        }


    }
}