package com.drdextersoft.app.ws2anonimous

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_about.*

class About : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val actionBar = supportActionBar
        actionBar!!.title = "Ws2Anonymous"
        actionBar.subtitle = packageManager.getPackageInfo(packageName, 0).versionName
        actionBar.elevation = 4.0F
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setLogo(R.mipmap.w2a_foreground)
        actionBar.setDisplayUseLogoEnabled(true)
        button.setOnClickListener { finish() }
        Correo.setOnClickListener {

            val to = "drvictoroviedo@gmail.com"
            val subject = "Ws2Anonymous"
            val message: String = TextoCorreo.text.toString()

            val mailintent =
                Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
            val addressees = arrayOf(to)
            mailintent.putExtra(Intent.EXTRA_EMAIL, addressees)
            mailintent.putExtra(Intent.EXTRA_SUBJECT, subject)
            mailintent.putExtra(Intent.EXTRA_TEXT, message)
            startActivity(Intent.createChooser(mailintent, ""))
        }
    }

}
