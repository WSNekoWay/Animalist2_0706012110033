package com.example.animalist2_0706012110033

import Database.GlobalVar
import Model.Animal
import Model.Ayam
import Model.Kambing
import Model.Sapi
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import com.example.animalist2_0706012110033.databinding.ActivityAddAnimalBinding

class AddUpAnimal : AppCompatActivity() {
    private lateinit var viewBinding: ActivityAddAnimalBinding
    private lateinit var animal: Animal
    private var position = -1
    var image: String = ""
    var id: Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        viewBinding = ActivityAddAnimalBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        GetIntent()
        Listener()
    }

    private fun GetIntent() {
        position = intent.getIntExtra("position", -1)
        if (position != -1){
            var animal = GlobalVar.listDataAnimal[0]
            for(i in 0..GlobalVar.listDataAnimal.size-1){
                if(position == GlobalVar.listDataAnimal[i].id){
                    animal=GlobalVar.listDataAnimal[i]
                }
            }
            id=position
            viewBinding.toolbar.title = "Edit Animal Data"
            viewBinding.addButton.text = "Save"
            viewBinding.imageView.setImageURI(Uri.parse(animal.imageUri))
            viewBinding.namaTextInputLayout.editText?.setText(animal.nama)
            if (animal.jenis == "ayam"){
                viewBinding.radioAyam.isChecked=true
            }else if (animal.jenis == "sapi"){
                viewBinding.radioSapi.isChecked=true
            } else{
                viewBinding.radioKambing.isChecked=true
            }
            viewBinding.umurTextInputLayout.editText?.setText(animal.usia)
        }

    }

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){   // APLIKASI GALLERY SUKSES MENDAPATKAN IMAGE
            val uri = it.data?.data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if(uri != null){
                    baseContext.getContentResolver().takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }}// GET PATH TO IMAGE FROM GALLEY
            viewBinding.imageView.setImageURI(uri)  // MENAMPILKAN DI IMAGE VIEW
            image = uri.toString()
        }
    }



    private fun Listener() {
        viewBinding.imageView.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBinding.addButton.setOnClickListener{
            var nama = viewBinding.namaTextInputLayout.editText?.text.toString().trim()
            var jenis = ""
            var umur = viewBinding.umurTextInputLayout.editText?.text.toString().trim()

//            viewBinding.radioAyam.setOnClickListener{
//                Toast.makeText(this, "ayam", Toast.LENGTH_SHORT).show()
//                jenis="ayam"
//            }
//            viewBinding.radioSapi.setOnClickListener(){
//                Toast.makeText(this, "sapi", Toast.LENGTH_SHORT).show()
//
//                jenis="sapi"
//            }
//            viewBinding.radioKambing.setOnClickListener(){
//                jenis="kambing"
//            }

            if(viewBinding.radioAyam.isChecked == true){
                jenis = "ayam"
            }else if(viewBinding.radioKambing.isChecked == true){
                jenis = "kambing"
            }else if(viewBinding.radioSapi.isChecked == true){
                jenis = "sapi"
            }
            animal = Animal(nama, jenis, umur, image.toUri().toString(),id)
            checker()
        }
        viewBinding.toolbar.getChildAt(1).setOnClickListener {
            finish()
        }
    }

    private fun checker() {
        var isCompleted:Boolean = true

        if(animal.nama!!.isEmpty()){
            viewBinding.namaTextInputLayout.error = "Nama tidak boleh kosong!"
            isCompleted = false
        }else{
            viewBinding.namaTextInputLayout.error = ""
        }

        if(animal.jenis!!.isEmpty()){
            Toast.makeText(this, "Jenis hewan tidak boleh kosong!", Toast.LENGTH_SHORT).show()
            isCompleted = false
        }else{
        }

        if(animal.usia!!.isEmpty()){
            viewBinding.umurTextInputLayout.error = "Usia hewan tidak boleh kosong!"
            isCompleted = false
        }else{
            viewBinding.umurTextInputLayout.error = ""
        }



        if(isCompleted)
            if(isCompleted)
            {
                var temp: Animal = Animal("nama","banci","1","",0)
                if(position == -1)
                {
                    if (image.isNotEmpty()) {
                        temp.imageUri = image
                    }

                    animal.usia = viewBinding.umurTextInputLayout.editText?.text.toString()
                    if (GlobalVar.listDataAnimal.size>0){
                        id = GlobalVar.listDataAnimal[GlobalVar.listDataAnimal.size-1].id +1
                    }
                    if (animal.jenis == "ayam"){
                        temp = Ayam(animal.nama,animal.jenis,animal.usia,animal.imageUri,id)
                        temp.jenis= "ayam"
                    }
                    else if (animal.jenis == "sapi"){
                        temp = Sapi(animal.nama,animal.jenis,animal.usia,animal.imageUri,id)
                        temp.jenis= "sapi"
                    }else {
                        temp = Kambing(animal.nama,animal.jenis,animal.usia,animal.imageUri,id)
                        temp.jenis= "kambing"
                    }


                    GlobalVar.listDataAnimal.add(temp)
                }else
                {
                    var animal = GlobalVar.listDataAnimal[0]
                    for(i in 0..GlobalVar.listDataAnimal.size-1){
                        if(position == GlobalVar.listDataAnimal[i].id){
                            animal=GlobalVar.listDataAnimal[i]
                        }
                    }
                    if (image.isNotEmpty()) {
                        temp.imageUri = animal.imageUri

                    }
                    animal.nama = viewBinding.namaTextInputLayout.editText?.text.toString().trim()
                    if (animal.jenis == "ayam"){
                        temp = Ayam(animal.nama,animal.jenis,animal.usia,animal.imageUri,id)
                        temp.jenis= "ayam"
                    }
                    else if (animal.jenis == "sapi"){
                        temp = Sapi(animal.nama,animal.jenis,animal.usia,animal.imageUri,id)
                        temp.jenis= "sapi"
                    }else {
                        temp = Kambing(animal.nama,animal.jenis,animal.usia,animal.imageUri,id)
                        temp.jenis= "kambing"
                    }
                    animal.usia = viewBinding.umurTextInputLayout.editText?.text.toString()

                    for (i in 0..GlobalVar.listDataAnimal.size-1){
                        if (GlobalVar.listDataAnimal[i].id == position){
                            GlobalVar.listDataAnimal[i] = temp
                            break
                        }
                    }
                }
                finish()
            }
    }
}