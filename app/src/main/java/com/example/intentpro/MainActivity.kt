package com.example.intentpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.intentpro.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalllActivity2.setOnClickListener {
            // 명시적 인텐트
            val intent:Intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("name","홍길동")
            intent.putExtra("age",27)
            startActivity(intent)


        }
        binding.btnCalllActivity3.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("name","고길동")
            intent.putExtra("age",50)
            startActivity(intent)
        }
        binding.btnCallActivity4.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            intent.putExtra("x",45)
            intent.putExtra("y",23)
            intent.putExtra("operator","+")
            startActivityForResult(intent,579854)
        }
        binding.btnCallActivity5.setOnClickListener {
            val intent = Intent(this, MainActivity5::class.java)
            intent.putExtra("x",45)
            intent.putExtra("y",23)
            intent.putExtra("operator","-")
            startActivityForResult(intent,579853)
        }
        val activityResultLauncher:ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            //콜백함수
                if(it.data?.getIntExtra("requestCode",0) == 579852 && it.data?.getIntExtra("resultCode", RESULT_OK) == RESULT_OK){
                    Toast.makeText(applicationContext,"${it.data?.getIntExtra("sum",0)}",Toast.LENGTH_SHORT).show()
                }else if(it.data?.getIntExtra("requestCode",0) == 579851){
                    Toast.makeText(applicationContext,"${it.data?.getIntExtra("sum",0)}",Toast.LENGTH_SHORT).show()
                }
            }
        binding.btnCallActivity6.setOnClickListener {
            val intent = Intent(this, MainActivity6::class.java)
            intent.putExtra("x",45)
            intent.putExtra("y",23)
            intent.putExtra("operator","*")
            intent.putExtra("requestCode",579852)
            activityResultLauncher.launch(intent)
        }
        binding.btnCallActivity7.setOnClickListener {
            val intent = Intent(this, MainActivity7::class.java)
            intent.putExtra("x",45)
            intent.putExtra("y",23)
            intent.putExtra("operator","%")
            intent.putExtra("requestCode",579851)
            activityResultLauncher.launch(intent)
        }

    }//end of onCreate

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if(requestCode == 579854 && resultCode == RESULT_OK){
            Toast.makeText(applicationContext,"${intent?.getIntExtra("sum",0)}",Toast.LENGTH_SHORT).show()
        }else if(requestCode == 579853 && resultCode == RESULT_OK){
            Toast.makeText(applicationContext,"${intent?.getIntExtra("sum",0)}",Toast.LENGTH_SHORT).show()
        }
    }
}