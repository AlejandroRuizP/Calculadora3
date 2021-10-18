package com.example.calculadora3

import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var isNewOp = true // borra el textview de la pantalla para poner el 2 nunero
    var oldNumber= ""
    var op = ""
    var isBinario = false
    var isHexa = false
    var isDec = false
    var opRealizada = false  // me comprueba si se ha realizado una OP para resetear el TextView
    var isPuntoVacio = true



    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

            //  Por defecto cuando se pone en horizontal, la calculadora está en DECIMAL
            isBinario = false
            isHexa = false
            isDec = false

            ABoton.isEnabled = false
            BBoton.isEnabled = false
            CBoton.isEnabled = false
            DBoton.isEnabled = false
            EBoton.isEnabled = false
            FBoton.isEnabled = false

            buttonBinario.setOnClickListener {   // CONFIGURACION BOTONES PARA BINARIO
                ResultadoTextView.setText("")
                isNewOp = true

                isBinario = true
                isHexa = false
                isDec = false

                DosBoton.isEnabled = false
                TresBoton.isEnabled = false
                CuatroBoton.isEnabled = false
                CincoBoton.isEnabled = false
                SeisBoton.isEnabled = false
                SieteBoton.isEnabled = false
                OchoBoton.isEnabled = false
                NueveBoton.isEnabled = false
                ABoton.isEnabled = false
                BBoton.isEnabled = false
                CBoton.isEnabled = false
                DBoton.isEnabled = false
                EBoton.isEnabled = false
                FBoton.isEnabled = false
                PuntoBoton.isEnabled = false


            }

            buttonHexaDecimal.setOnClickListener{ //CONFIGURACION DE BOTONES PARA HEXADECIMAL
                    ResultadoTextView.setText("")
                    isNewOp = true

                    isBinario = false
                    isHexa = true
                    isDec = false


                    DosBoton.isEnabled = true
                    TresBoton.isEnabled = true
                    CuatroBoton.isEnabled = true
                    CincoBoton.isEnabled = true
                    SeisBoton.isEnabled = true
                    SieteBoton.isEnabled = true
                    OchoBoton.isEnabled = true
                    NueveBoton.isEnabled = true
                    ABoton.isEnabled = true
                    BBoton.isEnabled = true
                    CBoton.isEnabled = true
                    DBoton.isEnabled = true
                    EBoton.isEnabled = true
                    FBoton.isEnabled = true
                   PuntoBoton.isEnabled = false


            }

            DecBoton.setOnClickListener {  //CONFIGRUACIÓN DE BOTONES PARA DECIMAL
                ResultadoTextView.setText("")
                isNewOp = true

                isBinario = false
                isHexa = false
                isDec = true

                DosBoton.isEnabled = true
                TresBoton.isEnabled = true
                CuatroBoton.isEnabled = true
                CincoBoton.isEnabled = true
                SeisBoton.isEnabled = true
                SieteBoton.isEnabled = true
                OchoBoton.isEnabled = true
                NueveBoton.isEnabled = true
                ABoton.isEnabled = false
                BBoton.isEnabled = false
                CBoton.isEnabled = false
                DBoton.isEnabled = false
                EBoton.isEnabled = false
                FBoton.isEnabled = false
                PuntoBoton.isEnabled = true


            }


        }


        }


    fun convertirBinarioToDecimal(num: Long): Int {
        var num = num
        var numeroDecimal = 0
        var i = 0
        var remainder: Long

        while (num.toInt() != 0) {
            remainder = num % 10
            num /= 10
            numeroDecimal += (remainder * Math.pow(2.0, i.toDouble())).toInt()
            ++i
        }
        return numeroDecimal
    }


    fun borrarEvent  (view: View) {
       ResultadoTextView.setText("")
       isNewOp = true
        isPuntoVacio = true
        PuntoBoton.isEnabled = true


    }
     fun operatorEvent(view: View) {
         isNewOp = true
         oldNumber = ResultadoTextView.text.toString()
         isPuntoVacio = true
         PuntoBoton.isEnabled = true



         var buselect = view as Button
         when(buselect.id) {
             DividirBoton.id -> {op = "/"}
             SumaBoton.id -> {op = "+"}
             RestaBoton.id -> {op ="-"}
             MultiplicarBoton.id -> {op = "*"}
         }




     }

    fun hacerOperacion(num1: String, num2: String, tipoOp: String, operacion: String ) :Double{
        /*
         SE LE PASA DOS NUMEROS, EL TIPO DE OPERACION (+ - * o /) Y EL TIPO DE OPERACION DE BINARIO
         DECIMAL O HEXA, CALCULA EL RESULTADO Y DEVUELVE UN DOUBLE

        */
          isPuntoVacio = true
        PuntoBoton.isEnabled = true


        var resultado =  0.0
         var olnumber: Double
         var newnumber: Double

        if (tipoOp == "binario") {

            var oldnumberBinarioConvertido = convertirBinarioToDecimal(num1.toLong())
            var newnumberBinarioConvertido = convertirBinarioToDecimal(num2.toLong())
             olnumber = oldnumberBinarioConvertido.toDouble()
             newnumber = newnumberBinarioConvertido.toDouble()


        } else if (tipoOp == "hexa") {
            olnumber = num1.toLong(radix = 16).toDouble()  // me lo convierte a Decimal
            newnumber =  num2.toLong(radix = 16).toDouble()  // me lo convierte a Decimal


        } else {
            olnumber = num1.toDouble()
            newnumber = num2.toDouble()
        }
        when (operacion) {
            "+" -> {resultado = olnumber.toDouble() + newnumber.toDouble()}
            "-" -> {resultado = olnumber.toDouble() - newnumber.toDouble()}
            "*" -> {resultado = olnumber.toDouble() * newnumber.toDouble()}
            "/" -> {resultado = olnumber.toDouble() / newnumber.toDouble()}

        }

        return resultado  // devuelve un double



    }

    fun igualEvent2(view: View) {
        isPuntoVacio = true
        PuntoBoton.isEnabled = true

        opRealizada = true
        var newnumber = ResultadoTextView.text.toString()
        var resulado = 0.0
        if (isBinario) {  // cuando es binario
             resulado = hacerOperacion(oldNumber ,newnumber , "binario", op)

            // TENGO QUE PASAR EL RESULTADO A BINARIO
            var re = Integer.toBinaryString(resulado.toInt())
            ResultadoTextView.setText(re.toString())

        } else if (isHexa){  // hexa se mete aqui
            resulado = hacerOperacion(oldNumber, newnumber, "hexa", op)

            var re = Integer.toHexString(resulado.toInt())  // me lo convierte a HEX
            ResultadoTextView.setText(re.toString())

        }  else {  // AQUI SE METE CUANDO EL NUMERO ES DECIMAL

            resulado = hacerOperacion(oldNumber ,newnumber , "isDec", op)
            ResultadoTextView.setText(resulado.toString())

        }  }






                fun numberEvent(view: View) {
                    if(isPuntoVacio == false) {
                        PuntoBoton.isEnabled = false
                    }
                    if (isNewOp or opRealizada) {
                        ResultadoTextView.setText("")
                        opRealizada = false
                    }
                    var buclick = ResultadoTextView.text.toString()
                    var buselect = view as Button
                    isNewOp = false

                    when(buselect.id) {
                        CeroBoton.id -> {buclick += "0"}
                        UnoBoton.id -> {buclick += "1"}
                        DosBoton.id -> {buclick += "2"}
                        TresBoton.id -> {buclick += "3"}
                        CuatroBoton.id -> {buclick += "4"}
                        CincoBoton.id -> {buclick += "5"}
                        SeisBoton.id -> {buclick += "6"}
                        SieteBoton.id -> {buclick += "7"}
                        OchoBoton.id -> {buclick += "8"}
                        NueveBoton.id -> {buclick += "9"}
                            PuntoBoton.id -> {  isPuntoVacio = false
                                buclick += "."


                            }


                        ABoton.id -> {buclick +="A"}
                        BBoton.id -> {buclick +="B"}
                        CBoton.id -> {buclick +="C"}
                        DBoton.id -> {buclick +="D"}
                        EBoton.id -> {buclick +="E"}
                        FBoton.id -> {buclick +="F"}



                    }
                    if(isPuntoVacio == false) {
                        PuntoBoton.isEnabled = false
                    }


                    ResultadoTextView.setText(buclick)

                    }





/*

    fun igualEvent(view: View) {
        var newnumber = ResultadoTextView.text.toString()
        var resulado = 0.0
        if (isBinario) {  // cuando es binario

            var newnumberBinarioConvertido = convertirBinarioToDecimal(newnumber.toLong())
            var oldnumberBinarioConvertido = convertirBinarioToDecimal(oldNumber.toLong())
            var newnumber = newnumberBinarioConvertido
            var olnumber = oldnumberBinarioConvertido
            when (op) {
                "+" -> {resulado = olnumber.toDouble() + newnumber.toDouble()}
                "-" -> {resulado = olnumber.toDouble() - newnumber.toDouble()}
                "*" -> {resulado = olnumber.toDouble() * newnumber.toDouble()}
                "/" -> {resulado = olnumber.toDouble() / newnumber.toDouble()}

            }
            // TENGO QUE PASAR EL RESULTADO A BINARIO
            var re = Integer.toBinaryString(resulado.toInt())
            ResultadoTextView.setText(re.toString())

        } else if (isHexa){  // hexa se mete aqui
            var oldnumber =  oldNumber.toLong(radix = 16)  // me lo convierte a Decimal
            var nwnumber = newnumber.toLong(radix = 16)  // me lo convierte a Decimal

            when (op) {


                "+" -> {resulado = oldnumber.toDouble() + nwnumber.toDouble()}
                "-" -> {resulado = oldnumber.toDouble() - nwnumber.toDouble()}
                "*" -> {resulado = oldnumber.toDouble() * nwnumber.toDouble()}
                "/" -> {resulado = oldnumber.toDouble() / nwnumber.toDouble()}
            }
            var re = Integer.toHexString(resulado.toInt())  // me lo convierte a HEX
            ResultadoTextView.setText(re.toString())


        }  else {  // AQUI SE METE CUANDO EL NUMERO ES DECIMAL

            when (op) {
                "+" -> {resulado = oldNumber.toDouble() + newnumber.toDouble()}
                "-" -> {resulado = oldNumber.toDouble() - newnumber.toDouble()}
                "*" -> {resulado = oldNumber.toDouble() * newnumber.toDouble()}
                "/" -> {resulado = oldNumber.toDouble() / newnumber.toDouble()}

            }
            ResultadoTextView.setText(resulado.toString())

        }  }


 */
}
