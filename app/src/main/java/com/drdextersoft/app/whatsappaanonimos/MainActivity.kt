package com.drdextersoft.app.whatsappaanonimos

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.text.Editable
import android.text.TextWatcher
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.title = "Whatsapp2Anonymous"
        actionBar!!.subtitle = getPackageManager().getPackageInfo(packageName, 0).versionName
        actionBar.elevation = 4.0F
        actionBar.setDisplayShowHomeEnabled(true)
        actionBar.setLogo(R.mipmap.w2a_foreground)
        actionBar.setDisplayUseLogoEnabled(true)

        Numero.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val pattern = Regex("[0-9]{8,20}$")

                if (pattern.containsMatchIn(Numero.text)) {
                    Enviar.isEnabled = true
                } else {
                    Enviar.isEnabled = false
                }
            }
        })

        Enviar.setOnClickListener() {
            val linea: String = "https://api.whatsapp.com/send?phone=" + Numero.text
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(linea)
            startActivity(i)
        }

        About.setOnClickListener {
            val acercade = Intent(this, com.drdextersoft.app.whatsappaanonimos.About::class.java)
            startActivity(acercade)
        }

        val paises = arrayOf("us","ru","eg","za","gr","nl","be","fr","es","hu","it",
            "ro","ch","at","gb","dk","se","no","pl","de","pe","mx","cu","ar","br","cl","co","ve",
            "my","au","id","ph","nz","sg","th","jp","kr","vn","cn","tr","in","pk","af","lk","mm",
            "ir","ss","ma","dz","tn","ly","gm","sn","mr","ml","gn","ci","bf","ne","tg","bj","mu",
            "lr","sl","gh","ng","cf","cm","cv","st","gq","ga","cg","cd","ao","gw","sc","sd","rw",
            "et","so","dj","ke","tz","ug","bi","mz","zm","mg","re","zw","na","mw","ls","bw","sz",
            "km","sh","er","aw","fo","gl","gi","pt","lu","ie","is","al","mt","cy","fi","bg","lt",
            "lv","ee","md","am","by","ad","mc","sm","ua","rs","me","hr","si","ba","mk","cz","sk",
            "li","fk","bz","gt","sv","hn","ni","cr","pa","pm","ht","bl","bo","gy","ec","py","sr",
            "uy","cw","tl","aq","bn","nr","pg","sb","vu","fj","pw","ck","nu","ws","ki","nc","tv",
            "pf","tk","fm","mh","kp","hk","mo","kh","la","pn","bd","tw","mv","lb","jo","sy","iq",
            "kw","sa","ye","om","ae","il","bh","qa","bt","mn","np","tj","tm","az","ge","kg","uz",
            "ai","vg","ky","mf","ms","mp","gu","as")
        val codigos = arrayOf(1,7,20,27,30,31,32,33,34,36,39,40,41,43,44,45,46,47,48,49,
            51,52,53,54,55,56,57,58,60,61,62,63,64,65,66,81,82,84,86,90,91,92,93,94,95,98,211,212,
            213,216,218,220,221,222,223,224,225,226,227,228,229,230,231,232,233,234,236,237,238,239,
            240,241,242,243,244,245,248,249,250,251,252,253,254,255,256,257,258,260,261,262,263,264,
            265,266,267,268,269,290,291,297,298,299,350,351,352,353,354,355,356,357,358,359,370,371,
            372,373,374,375,376,377,378,380,381,382,385,386,387,389,420,421,423,500,501,502,503,504,
            505,506,507,508,509,590,591,592,593,595,597,598,599,670,672,673,674,675,677,678,679,680,
            682,683,685,686,687,688,689,690,691,692,850,852,853,855,856,870,880,886,960,961,962,963,
            964,965,966,967,968,971,972,973,974,975,976,977,992,993,994,995,996,998,1264,1284,1345,
            1599,1664,1670,1671,1684
        )
        val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        for (i in 0..paises.size - 1) {
            if (tm.simCountryIso == paises[i]) {Numero.setText("+" + codigos[i])}
          }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        Numero.requestFocus()


    }
}
