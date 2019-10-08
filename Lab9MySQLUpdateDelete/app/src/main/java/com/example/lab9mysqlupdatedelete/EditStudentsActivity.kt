package com.example.lab9mysqlupdatedelete

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.widget.Toast
import com.myweb.lab9mysqlupdatedelete.Student
import kotlinx.android.synthetic.main.activity_edit_students.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditStudentsActivity : AppCompatActivity() {
    val createClient : StudentAPI = StudentAPI.create()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_students)

        val mId :String = intent.getStringExtra("mId")
        val mName :String = intent.getStringExtra("mName")
        val mAge :String = intent.getStringExtra("mAge")

        edit_id.setText(mId)
        edit_id.isEnabled =false
        edit_name.setText(mName)
        edit_age.setText(mAge)
    }

    fun saveStudent (v: View) {
        createClient.updateStudent(
            edit_id.text.toString(),
            edit_name.text.toString(),
            edit_age.text.toString().toInt()
        ).enqueue(object : Callback<Student> {

            override fun onResponse(call: Call<Student>, response: Response<Student>) {
                if (response.isSuccessful) {
                    Toast.makeText(applicationContext, "Successfully Updated", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                } else {
                    Toast.makeText(applicationContext,  "Error ", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Student>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                Toast.makeText(applicationContext,  "Error onFailure "+t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
    fun deleteStudent(v: View){
    }
}
