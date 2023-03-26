package com.mocompany.id_entifier

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_pop_up_layout.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submit_btn.setOnClickListener(){
                if(id_text.text?.isEmpty() == true){
                    Toast.makeText(this , "Please enter your ID number" ,Toast.LENGTH_SHORT).show()
                    id_text.setError("Please enter your ID number")

                }else if (id_text.text?.length?.equals(14) == true){
                    val IDNumber= id_text.text.toString()
                    val dateOfTheUser :String = getBirth(IDNumber)
                    val genderOfTheUser :String = getGender(IDNumber)
                    val countryOfTheUser :String = getCountry(IDNumber)

                  //  val AGEINMINUTES : String = getAgeInMinutes(dateOfTheUser)
                    val checkNumber :Boolean = isTrue(IDNumber)

                    if(checkNumber==true){
                    showDialog(dateOfTheUser, genderOfTheUser,countryOfTheUser )
                    }else{
                        Toast.makeText(this , "Wrong ID Number!" ,Toast.LENGTH_SHORT).show()
                        id_text.setError("Wrong ID Number!")

                    }
                }else{
                    Toast.makeText(this , "Wrong ID Number!" ,Toast.LENGTH_SHORT).show()
                    id_text.setError("Wrong ID Number!")

                }
        }
    }

    fun getBirth(s: String): String {

        val fDigit = s[0].toString().toInt()
        var YEAR = ""

        val MONTH = s.substring(3, 5).toInt()
        var Date = ""
        val DAY = s.substring(5, 7).toInt()
        val bYEAR = s.substring(1, 3)
        if((MONTH>12||MONTH==0)||(DAY>31||DAY==0)||(MONTH==2&&DAY<29)){
            return "wrong"
        }
        if (fDigit == 2) {
            val fYEAR = "19"
            YEAR = fYEAR + bYEAR
        } else if (fDigit == 3) {
            val fYEAR = "20"
            YEAR = fYEAR + bYEAR
        }
        var monthString = ""
        when(MONTH){   1->{
            monthString="Jan"
        }

            2->{
                monthString="Feb"
            }
            3->{
                monthString="Mar"
            }
            4->{
                monthString="Apr"
            }
            5->{
                monthString="May"
            }
            6->{
                monthString="Jun"
            }
            7->{
                monthString="Jul"
            }
            8->{
                monthString="Aug"
            }
            9->{
                monthString="Sep"
            }
            10->{
                monthString="Oct"
            }
            11->{
                monthString="Nov"
            }
            12->{
                monthString="Dec"
            }
        }
        Date = "$DAY ,$monthString,$YEAR"
        return Date

    }



    fun getGender(s: String): String {
        val bLast = s[s.length - 2].toString()
        val blast = bLast.toInt()
        var Gender = ""
        Gender = if (blast % 2 == 0) {
            "Female"
        } else {
            "Male"
        }
        return Gender
    }

    fun getCountry(s: String): String {
        val country = s.substring(7, 9)
        var nameOfCountry = ""
        when (country) {
            "01" -> nameOfCountry = "Cairo"
            "02" -> nameOfCountry = "Alexandria"
            "03" -> nameOfCountry = "Port Said"
            "04" -> nameOfCountry = "Suez"
            "11" -> nameOfCountry = "Damietta"
            "12" -> nameOfCountry = "Dakahlia"
            "13" -> nameOfCountry = "Eastern"
            "14" -> nameOfCountry = "Qalubia"
            "15" -> nameOfCountry = "Kafr El-Sheikh"
            "16" -> nameOfCountry = "Western"
            "17" -> nameOfCountry = "Menoufia"
            "18" -> nameOfCountry = "ِAlbuhayra"
            "19" -> nameOfCountry = "Ismailia"
            "21" -> nameOfCountry = "Giza"
            "22" -> nameOfCountry = "Bani Sweif"
            "23" -> nameOfCountry = "Fayoum"
            "24" -> nameOfCountry = "Minya"
            "25" -> nameOfCountry = "Asyut"
            "26" -> nameOfCountry = "Sohag"
            "27" -> nameOfCountry = "Qena"
            "28" -> nameOfCountry = "Aswan"
            "29" -> nameOfCountry = "ِAlaqasr"
            "31" -> nameOfCountry = "The Red Sea"
            "32" -> nameOfCountry = "the new Valley"
            "33" -> nameOfCountry = "Marsa Matrouh"
            "34" -> nameOfCountry = "North of Sinai"
            "35" -> nameOfCountry = "South of Sinai"
            "88" -> nameOfCountry = "Outside the Republic"
            else -> {
                nameOfCountry = "wrong"
            }
        }
        return nameOfCountry
    }

    private fun showDialog(date: String , gender : String , country:String ) {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_pop_up_layout)
        val dateTV = dialog.findViewById(R.id.date_tv) as TextView
        dateTV.setText("Date : $date")
        val countryTV = dialog.findViewById(R.id.country_tv) as TextView
        countryTV.setText("Country : $country")
        val genderTV = dialog.findViewById(R.id.gender_tv) as TextView
        genderTV.setText("Gender : $gender")
      //  val ageInMinutesTV = dialog.findViewById(R.id.age_in_minutes_tv) as TextView
        //ageInMinutesTV.setText("$ageInMinutes mins till now ")
        val tryAgain = dialog.findViewById(R.id.try_again_button) as Button

        tryAgain.setOnClickListener {
            dialog.dismiss()
            id_text.setText("")
        }
        dialog.show()

    }
    fun isTrue(IDNumber : String):Boolean{

        val nameOfTheCountry = getCountry(IDNumber)
        val getBirth = getBirth(IDNumber)
        if((getBirth.equals("wrong"))||(nameOfTheCountry.equals("wrong"))){
            return false
        } else
            return true
    }
    /*fun getAgeInMinutes(date :String):String{
        val sdf = SimpleDateFormat("dd/MM/yyyy" , Locale.ENGLISH)
        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
        val currentDateInMinutes = currentDate!!.time/ 60000

        val theDate = sdf.parse(date)
        val selectedDateInMinutes = theDate!!.time/ 60000

        val difference = currentDateInMinutes - selectedDateInMinutes
        val differenceString : String = "$difference"
        return differenceString

    }*/
}