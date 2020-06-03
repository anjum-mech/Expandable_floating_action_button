package com.malakinfo.tailoraapp

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import kotlinx.android.synthetic.main.activity_add_update_record.*



class AddUpdateRecordActivity : AppCompatActivity() {

    private val  CAMERA_REQUEST_CODE = 100;
    private val  STORAGE_REQUEST_CODE = 101;
    private val  IMAGE_PICK_CAMERA_COODE = 102;
    private val  IMAGE_PICK_GALLERY_COODE = 103;
    private lateinit var cameraPermissions:Array<String>;
    private lateinit var storagePermissions:Array<String>;
    private var imageUri:Uri? = null
    private var name: String? = ""
    private var phone : String? = ""
    private var dobEnter : String? = ""
    private var dobRelig : String? = ""
    private var bio :String? = ""

    private var actionBar : ActionBar? = null
    lateinit var  dbHelper :MyDbHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_update_record)

        actionBar = supportActionBar
        actionBar!!.title = "Add Record"
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)
        permissionArry()
    }

    private fun permissionArry() {

        dbHelper = MyDbHelper(this)
        cameraPermissions = arrayOf(Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        storagePermissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)

        profileIv.setOnClickListener {
            imagePickDialog()
        }
        saveBtn.setOnClickListener {
             inputData()

        }

    }

    private fun inputData() {
        name = ""+nameEt.text.toString().trim()
        phone = ""+phoneEt.text.toString().trim()
        dobEnter = ""+dobEnter.toString().trim()
        dobRelig = ""+dobRelig.toString().trim()
        bio = ""+bio.toString().trim()


      val timestamp = System.currentTimeMillis()

        val id = dbHelper.insertRecord(
            ""+name,
            ""+imageUri,
            ""+bio,
            ""+phone,
            ""+dobEnter,
        ""+dobRelig,
            ""+timestamp,
            "" +timestamp

        )
        Toast.makeText(this,"Record Added against Id $id",Toast.LENGTH_SHORT).show()



    }

    private fun imagePickDialog() {
        val options = arrayOf("Camera","Gallery")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Pick Image For")
        builder.setItems(options){ dialog, which ->
            if (which == 0){
                if (!checkCameraPermission()){
                    requestCameraPermission()
                }
                else{
                    pickFromCamera()
                }
            }
            else{
                if (!checkStoragePermission()){
                    requestStoragePermission()

                }
                else{
                    pickFromGallery()

                }

            }

        }
        builder.show()
    }

    private fun pickFromGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK)
        galleryIntent.type = "image/*"
        startActivityForResult(
            galleryIntent,IMAGE_PICK_GALLERY_COODE
        )
    }

    private fun requestStoragePermission() {
        ActivityCompat.requestPermissions(this,storagePermissions,STORAGE_REQUEST_CODE)

    }

    private fun checkStoragePermission(): Boolean {

        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

    }

    private fun pickFromCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "Image Title")
        values.put(MediaStore.Images.Media.DESCRIPTION,"Image Description")
        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
        startActivityForResult(
            cameraIntent,IMAGE_PICK_CAMERA_COODE
        )
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(this,cameraPermissions,CAMERA_REQUEST_CODE)

    }

    private fun checkCameraPermission(): Boolean {
        val results = ContextCompat.checkSelfPermission(this,
        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        val resultsl = ContextCompat.checkSelfPermission(this,
        Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        return results && resultsl


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isNotEmpty()){
                    val cameraAccepted = grantResults [0] == PackageManager.PERMISSION_GRANTED
                    val storageAccepted = grantResults [1] == PackageManager.PERMISSION_GRANTED
                    if (cameraAccepted && storageAccepted){
                        pickFromCamera()
                    }
                    else{
                        Toast.makeText(this,"Camera and Storage Permission are required",Toast.LENGTH_SHORT).show()
                    }

                }
            }
            STORAGE_REQUEST_CODE -> {
                if (grantResults.isNotEmpty()){
                    val storageAccepted = grantResults [0] == PackageManager.PERMISSION_GRANTED
                    if (storageAccepted){
                        pickFromGallery()
                    }
                    else{
                        Toast.makeText(this,"Storage Permission are required",Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == Activity.RESULT_OK){
            if (requestCode == IMAGE_PICK_GALLERY_COODE){
                CropImage.activity(data!!.data)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this)
            }
            else if (requestCode == IMAGE_PICK_CAMERA_COODE){
                CropImage.activity(imageUri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this)
            }
            else if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
                val result = CropImage.getActivityResult(data)
                if (resultCode == Activity.RESULT_OK){
                    val resultUri = result.uri
                    imageUri = resultUri
                    profileIv.setImageURI(resultUri)
                }
                else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    val error = result.error
                    Toast.makeText(this,""+error,Toast.LENGTH_SHORT).show()


                }
            }

        }

        super.onActivityResult(requestCode, resultCode, data)

    }

}
