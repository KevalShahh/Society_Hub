package com.example.societyhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.payumoney.core.PayUmoneySdkInitializer
import com.payumoney.core.entity.TransactionResponse
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager
import java.security.MessageDigest

class ChairmanMaintenancePaymentActivity : AppCompatActivity() {
    private val paymentParamBuilder = PayUmoneySdkInitializer.PaymentParam.Builder()
    private var paymentParam: PayUmoneySdkInitializer.PaymentParam? = null
    val key = "5nx6SBqi"
    val salt = "xail53tFjr"
    val merchantId = "7419349"
    var transactionId = "text123456"

    var charges = ""

    var firstname = "Keval"
    var email = "kevalshah.17.ce@iite.indusuni.ac.in"

    private val productName = "Society Hub Demo"
    private val productInfo = "Society Hub Demo"
    private var generatedHash: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chairman_maintenance_payment)
        var amount = intent.getStringExtra("amount")
        var totalAmount = intent.getStringExtra("amount")
        val hashSeq =
                "$key|$transactionId|$totalAmount|$productInfo|$firstname|$email|||||||||||$salt"
        generatedHash = hashCal("sha512", hashSeq)
        paymentParamBuilder.setAmount(totalAmount)                          // Payment amount
                .setTxnId(transactionId)                                             // Transaction ID
                .setPhone("9106955017")                                           // User Phone number
                .setProductName(productName)                   // Product Name or description
                .setFirstName(firstname)                              // User First name
                .setEmail(email)
                .setsUrl("https://www.payumoney.com/mobileapp/payumoney/success.php") // Success URL (surl)
                .setfUrl("https://www.payumoney.com/mobileapp/payumoney/failure.php") //Failure URL (furl)                 //Failure URL (furl)
                .setUdf1("")
                .setUdf2("")
                .setUdf3("")
                .setUdf4("")
                .setUdf5("")
                .setUdf6("")
                .setUdf7("")
                .setUdf8("")
                .setUdf9("")
                .setUdf10("")
                .setIsDebug(true)                              // Integration environment - true (Debug)/ false(Production)
                .setKey(key)                        // Merchant key
                .setMerchantId(merchantId);

        paymentParam = paymentParamBuilder.build()
        paymentParam?.setMerchantHash(generatedHash)

        PayUmoneyFlowManager.startPayUMoneyFlow(
                paymentParam,
                this,
                R.style.AppTheme_default,
                true
        )
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var fireuser= FirebaseAuth.getInstance().currentUser
        var user= fireuser?.email
        // Result Code is -1 send from Payumoney activity
        Log.d("PaymentActivity", "request code $requestCode resultcode $resultCode")
        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {
            val transactionResponse: TransactionResponse =
                    data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE)!!
            if (transactionResponse.getPayuResponse() != null) {
                if (transactionResponse.transactionStatus
                                .equals(TransactionResponse.TransactionStatus.SUCCESSFUL)
                ) {
                    Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show()
                    var month=intent.getStringExtra("maintenanceMonth")
                    if (month != null) {
                        FirebaseFirestore.getInstance().collection("Maintenance").document(month).addSnapshotListener { value, error ->
                            var model= value?.toObject(MaintenanceModel::class.java)
                            if (model != null) {
                                model.paid="Yes"
                                var map=HashMap<String,String>()
                                map.put("paid","Yes")
                                FirebaseFirestore.getInstance().collection("Maintenance").document(month).update(map as Map<String, Any>)
                                startActivity(Intent(this,MaintenanceListChairmanSide::class.java))
                            }
                        }
                    }
                    Log.d("TAG", "onActivityResult: "+transactionResponse.getPayuResponse())
                } else {
                    Toast.makeText(this, "Payment Failed" + transactionResponse.message, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,AttendEventNotRegisteredChairmanSide::class.java))
                }

                // Response from Payumoney
                val payuResponse: String = transactionResponse.getPayuResponse()

                // Response from SURl and FURL
                val merchantResponse: String = transactionResponse.transactionDetails
            } else {
                Log.d("PaymentActivity", "Both objects are null!")
                Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this,AttendEventNotRegisteredChairmanSide::class.java))
            }
        }
    }
    private fun hashCal(type: String?, hashString: String): String? {
        return MessageDigest
                .getInstance(type)
                .digest(hashString.toByteArray())
                .fold("", { str, it -> str + "%02x".format(it) })
    }
}