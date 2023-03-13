package com.chaeseong.mysampleapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.chaeseong.mysampleapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

	private lateinit var auth: FirebaseAuth

	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {

		auth = FirebaseAuth.getInstance()

		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()

		binding = DataBindingUtil.setContentView(this,R.layout.activity_main)



		val joinBtnClicked = findViewById<Button>(R.id.joinBtn)
		joinBtnClicked.setOnClickListener {

			// 첫번째 방법
//			val email = findViewById<EditText>(R.id.emailArea)
//			val pwd = findViewById<EditText>(R.id.pwdArea)

			// 두번째 방법
			val email = binding.emailArea
			val pwd = binding.pwdArea

			auth.createUserWithEmailAndPassword(
				email.text.toString(),
				pwd.text.toString())
				.addOnCompleteListener(this) { task ->
					if (task.isSuccessful) {
						Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show()
					} else {
						Toast.makeText(this,"fail",Toast.LENGTH_SHORT).show()
					}
				}
		}

		binding.logoutBtn.setOnClickListener {

			auth.signOut()
			Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()
		}

		binding.loginBtn.setOnClickListener {

			val email = binding.emailArea
			val pwd = binding.pwdArea

			auth.signInWithEmailAndPassword(
				email.text.toString(),
				pwd.text.toString()
			).addOnCompleteListener(this) { task ->
					if (task.isSuccessful) {
						Toast.makeText(this,"ok",Toast.LENGTH_SHORT).show()
						Toast.makeText(this, auth.currentUser?.uid.toString(), Toast.LENGTH_SHORT).show()
					} else {
						Toast.makeText(this,"fail",Toast.LENGTH_SHORT).show()
					}
				}
		}
	}
}