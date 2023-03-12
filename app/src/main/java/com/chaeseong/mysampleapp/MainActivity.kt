package com.chaeseong.mysampleapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

	private lateinit var auth: FirebaseAuth

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		auth = FirebaseAuth.getInstance()

		val joinBtnClicked = findViewById<Button>(R.id.joinBtn)
		joinBtnClicked.setOnClickListener {
			auth.createUserWithEmailAndPassword("abc@abc.com", "12341234")
				.addOnCompleteListener(this) { task ->
					if (task.isSuccessful) {
						Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show()
					} else {
						Toast.makeText(this,"fail",Toast.LENGTH_SHORT).show()
					}
				}

		}
	}
}