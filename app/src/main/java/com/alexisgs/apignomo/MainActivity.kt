package com.alexisgs.apignomo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.alexisgs.apignomo.personajes.data.model.Characters
import com.alexisgs.apignomo.personajes.ui.CharactersFragment
import com.alexisgs.apignomo.personajes.ui.DetailsCharactersFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.gson.Gson

class MainActivity : AppCompatActivity(), CharactersFragment.ViewClickElement {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseInstanceId.getInstance().instanceId
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w(TAG, "getInstanceId failed", task.exception)
                    return@OnCompleteListener
                }
                val token = task.result?.token

                // Log and toast
                val msg = getString(R.string.msg_token_fmt, token)
                Log.d(TAG, msg)
                //Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
            })

        supportFragmentManager!!
            .beginTransaction()
            .replace(R.id.root_layout, CharactersFragment.newInstance(), CharactersFragment.TAG)
            .commit()

    }

    override fun viewDetailUser(data: Characters) {
        Log.e(TAG, "Data User: ${Gson().toJson(data)}")
        val bundle = Bundle()
        bundle.putParcelable(KEY_CHARACTERS, data)
        val detailsCharactersFragment = DetailsCharactersFragment.newInstance()
        detailsCharactersFragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, detailsCharactersFragment, DetailsCharactersFragment.TAG)
            .addToBackStack(DetailsCharactersFragment.TAG)
            .commit()

    }


    companion object {
        val TAG = MainActivity::class.java.canonicalName
        val KEY_CHARACTERS = "CHARACTERS_KEYS"
    }
}