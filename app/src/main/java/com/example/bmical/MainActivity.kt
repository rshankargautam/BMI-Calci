package com.example.bmical

import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import org.florescu.android.rangeseekbar.RangeSeekBar
import java.lang.Exception
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.FieldPosition


class MainActivity : AppCompatActivity()  {
    private var weightSelected:String = ""
    private var heightSelected:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weight = resources.getStringArray(R.array.weight_array)
        val height = resources.getStringArray(R.array.height_array)

        val zeroButton=findViewById<Button>(R.id.zero)
        val oneButton=findViewById<Button>(R.id.one)
        val twoButton=findViewById<Button>(R.id.two)
        val threeButton=findViewById<Button>(R.id.three)
        val fourButton=findViewById<Button>(R.id.four)
        val fiveButton=findViewById<Button>(R.id.five)
        val sixButton=findViewById<Button>(R.id.six)
        val sevenButton=findViewById<Button>(R.id.seven)
        val eightButton=findViewById<Button>(R.id.eight)
        val nineButton=findViewById<Button>(R.id.nine)
        val decimalButton=findViewById<Button>(R.id.decimal)
        val acButton=findViewById<Button>(R.id.ac)
        val delButton=findViewById<Button>(R.id.del)
        val goButton=findViewById<Button>(R.id.go)
        val resultSb=findViewById<SeekBar>(R.id.sbResult)
        val etWeight = findViewById<EditText>(R.id.etWeight)
        val etHeight = findViewById<EditText>(R.id.etHeight)
        val bmiResult = findViewById<TextView>(R.id.bmiResult)

        val weightSpinner = findViewById<Spinner>(R.id.weightSpinner)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,weight)
        weightSpinner.adapter = adapter
        weightSpinner.onItemSelectedListener = object:
        AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id:Long) {
                weightSelected = weight[position]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        val adapterSecond = ArrayAdapter(this,android.R.layout.simple_spinner_item,height)
        heightSpinner.adapter = adapterSecond
        val heightSpinner = findViewById<Spinner>(R.id.heightSpinner)
        heightSpinner.onItemSelectedListener = object :
        AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id:Long) {
                heightSelected = height[position]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }


        etWeight.setOnClickListener{
            zeroButton?.setOnClickListener{
                etWeight.append("0")
            }
            oneButton?.setOnClickListener{
                etWeight.append("1")
            }
            twoButton?.setOnClickListener{
                etWeight.append("2")
            }
            threeButton?.setOnClickListener{
                etWeight.append("3")
            }
            fourButton?.setOnClickListener{
                etWeight.append("4")
            }
            fiveButton?.setOnClickListener{
                etWeight.append("5")
            }
            sixButton?.setOnClickListener{
                etWeight.append("6")
            }
            sevenButton?.setOnClickListener{
                etWeight.append("7")
            }
            eightButton?.setOnClickListener{
                etWeight.append("8")
            }
            nineButton?.setOnClickListener{
                etWeight.append("9")
            }
            decimalButton?.setOnClickListener{
                etWeight.append(".")
            }
            acButton.setOnClickListener {
                etWeight.setText("")
            }
            delButton.setOnClickListener {
                etWeight.setText((etWeight.getText().toString()).dropLast(1))
            }
        }

        etHeight.setOnClickListener{
            zeroButton?.setOnClickListener {
                etHeight.append("0")
            }
            oneButton?.setOnClickListener {
                etHeight.append("1")
            }
            twoButton?.setOnClickListener {
                etHeight.append("2")
            }
            threeButton?.setOnClickListener {
                etHeight.append("3")
            }
            fourButton?.setOnClickListener {
                etHeight.append("4")
            }
            fiveButton?.setOnClickListener {
                etHeight.append("5")
            }
            sixButton?.setOnClickListener {
                etHeight.append("6")
            }
            sevenButton?.setOnClickListener {
                etHeight.append("7")
            }
            eightButton?.setOnClickListener {
                etHeight.append("8")
            }
            nineButton?.setOnClickListener {
                etHeight.append("9")
            }
            decimalButton?.setOnClickListener {
                etHeight.append(".")
            }
            acButton.setOnClickListener {
                etHeight.setText("")
            }
            delButton.setOnClickListener {
                etHeight.setText((etHeight.getText().toString()).dropLast(1))
            }
        }
        goButton.setOnClickListener {
            var changedWeight: Float = 0.0f
            var changedHeight: Float = 0.0f
            var result: Float = 0.0f
            if (etWeight.text.isEmpty() || etHeight.text.isEmpty()) {
                Toast.makeText(this, "Input fields are empty", Toast.LENGTH_LONG).show()
            } else {
                if (weightSelected.equals("Pounds")) {
                    changedWeight = (etWeight.getText().toString()).toFloat()
                    changedWeight *= 2.20462262f
                } else {
                    changedWeight = (etWeight.getText().toString()).toFloat()
                }
                if (heightSelected.equals("Centimeter")) {
                    changedHeight = (etHeight.getText().toString()).toFloat() / 100f
                } else if (heightSelected.equals("Meter")) {
                    changedHeight = (etHeight.getText().toString()).toFloat()
                } else if (heightSelected.equals("Feet")) {
                    changedHeight = (etHeight.getText().toString()).toFloat() / 3.2808399f
                } else {
                    changedHeight = (etHeight.getText().toString()).toFloat() / 39.3700787f
                }
                result = changedWeight / (changedHeight * changedHeight)
                val df = DecimalFormat("#.#")
                df.roundingMode = RoundingMode.CEILING
                if (result < 16) {
                    bmiResult.setTextColor(Color.parseColor("#2971CD"))
                    bmiResult.setText((df.format(result)).toString())
                    resultSb.setProgress((result * 2.5).toInt())
                    tvResult.setTextColor(Color.parseColor("#2971CD"))
                    tvResult.setText("•16.0 - 18.5 UnderWeight")
                } else if (result >= 16 && result < 18.5) {
                    bmiResult.setTextColor(Color.parseColor("#2971CD"))
                    bmiResult.setText((df.format(result)).toString())
                    resultSb.setProgress((result * 2.5).toInt())
                    tvResult.setTextColor(Color.parseColor("#2971CD"))
                    tvResult.setText("•16.0 - 18.5 UnderWeight")
                } else if (result >= 18.5 && result < 25) {
                    bmiResult.setTextColor(Color.parseColor("#309E1E"))
                    bmiResult.setText((df.format(result)).toString())
                    resultSb.setProgress((result * 2.5).toInt())
                    tvResult.setTextColor(Color.parseColor("#309E1E"))
                    tvResult.setText("•18.5 - 25.0 Normal")
                } else if (result >= 25 && result <= 40) {
                    bmiResult.setTextColor(Color.parseColor("#DA1010"))
                    bmiResult.setText((df.format(result)).toString())
                    resultSb.setProgress((result * 2.5).toInt())
                    tvResult.setTextColor(Color.parseColor("#DA1010"))
                    tvResult.setText("•25.0 - 40.0 OverWeight")
                } else if (result > 40.0) {
                    bmiResult.setTextColor(Color.parseColor("#DA1010"))
                    bmiResult.setText((df.format(result)).toString())
                    resultSb.setProgress((result * 2.5).toInt())
                    tvResult.setTextColor(Color.parseColor("#DA1010"))
                    tvResult.setText("•25.0 - 40.0 OverWeight")
                }
            }
        }
    }
}
