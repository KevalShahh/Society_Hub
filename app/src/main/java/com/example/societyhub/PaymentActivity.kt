package com.example.societyhub

import android.accessibilityservice.GestureDescription
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.payumoney.core.PayUmoneySdkInitializer
import com.payumoney.core.entity.TransactionResponse
import com.payumoney.sdkui.ui.utils.PayUmoneyFlowManager
import org.json.JSONException
import org.json.JSONObject
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap
import kotlin.experimental.and

class PaymentActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_payment)
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
        var fireuser=FirebaseAuth.getInstance().currentUser
        var user= fireuser?.email
        var en=intent.getStringExtra("eventname")
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
                    if (user != null) {
                        FirebaseFirestore.getInstance().collection("Users").document(user).addSnapshotListener { value, error ->
                            var m= value?.toObject(UserModel1::class.java)
                            var mm= m?.flat
                            if (mm != null) {
                                if (en != null) {
                                    FirebaseFirestore.getInstance().collection("Society").document(mm).collection("Events").document(en).addSnapshotListener { value, error ->
                                        var model= value?.toObject(EventModel::class.java)
                                        if (model != null) {
                                            model.paid="Yes"
                                            var map=HashMap<String,String>()
                                            map.put("paid","Yes")
                                          //  map.put("registeredAt",model.getCreatedDateFormat())
                                            FirebaseFirestore.getInstance().collection("Society").document(mm).collection("Events").document(en).update(map as Map<String, Any>)
                                            var intent=Intent(this,EventsChairmanSide::class.java)
                                            startActivity(intent)
                                            }
                                    }
                                }
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



  /*  var theRandomNum = (Math.random() * Math.pow(10.0, 10.0)).toLong()
    var key = "XETbPtgK"
    var salt = "H14dXamrO3"
    var merchantId = "8024519"
    var transactionId = theRandomNum.toString()
    var charges = ""
    var totalAmount = "10"
    var firstname = "Dhaval"
    var email = "dhavalkhandala.8.ce@iite.indusuni.ac.in"
    var udf1 = ""
    var udf2 = ""
    var udf3 = ""
    var udf4 = ""
    var udf5 = ""
    var udf6 = ""
    var udf7 = ""
    var udf8 = ""
    var udf9 = ""
    var udf10 = ""
    var productName = "LifeCare Demo"
    var productInfo = "LifeCare Demo"
    var generatedHash: String? = null
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val amount = intent.getStringExtra("totalprice")
            val hashSequence = "$key|$transactionId|$amount|$productInfo|$firstname|$email|$udf1|$udf2|$udf3|$udf4|$udf5||||||$salt"
            val builder: GestureDescription.Builder = GestureDescription.Builder()
            builder.setAmount(amount) // Payment amount
                    .setTxnId(transactionId) // Transaction ID//
                    .setPhone("9104261130") // User Phone number
                    .setProductName(productInfo) // Product Name or description
                    .setFirstName(firstname) // User First name
                    .setEmail(email) // User Email ID
                    .setsUrl("https://www.payumoney.com/mobileapp/payumoney/success.php") // Success URL (surl)
                    .setfUrl("https://www.payumoney.com/mobileapp/payumoney/failure.php") //Failure URL (furl)
                    .setUdf1(udf1)
                    .setUdf2(udf2)
                    .setUdf3(udf3)
                    .setUdf4(udf4)
                    .setUdf5(udf5)
                    .setUdf6(udf6)
                    .setUdf7(udf7)
                    .setUdf8(udf8)
                    .setUdf9(udf9)
                    .setUdf10(udf10)
                    .setIsDebug(true) // Integration environment - true (Debug)/ false(Production)
                    .setKey(key) // Merchant key
                    .setMerchantId(merchantId)
            var paymentParam: PayUmoneySdkInitializer.PaymentParam? = null
            try {
                paymentParam = builder.build()
            } catch (e: Exception) {
                e.printStackTrace()
            }
            //set the hash
            val hash = hashCal("sha512", hashSequence)
            paymentParam.setMerchantHash(hash)
            PayUmoneyFlowManager.startPayUMoneyFlow(
                    paymentParam,
                    this,
                    R.style.AppTheme_default,
                    true)
        } else {
            Toast.makeText(this, "Please Login Your account", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@PaymentActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("MainActivity", "request code $requestCode resultcode $resultCode")
        if (requestCode == PayUmoneyFlowManager.REQUEST_CODE_PAYMENT && resultCode == RESULT_OK && data != null) {
            val transactionResponse: TransactionResponse = data.getParcelableExtra(PayUmoneyFlowManager.INTENT_EXTRA_TRANSACTION_RESPONSE)
            if (transactionResponse != null && transactionResponse.getPayuResponse() != null) {
                if (transactionResponse.getTransactionStatus().equals(TransactionResponse.TransactionStatus.SUCCESSFUL)) {
                    val user = FirebaseAuth.getInstance().currentUser
                    if (user != null) {
                        val userEmail = user.email
                        val model = PaymentModel()
                        val model1 = OrderModel()
                        val res: String = transactionResponse.getPayuResponse()
                        try {
                            val root = JSONObject(res)
                            val array = root.getJSONObject("result")
                            model.setPaymentid(array.getString("paymentId"))
                            model.setAmount(array.getString("amount"))
                            model.setDatetime(array.getString("addedon"))
                            model.setAddress(intent.getStringExtra("address") + " " + "Pincode:" + intent.getStringExtra("pincode"))
                            model.setMobileno(intent.getStringExtra("mobileno"))
                            model.setTransectionid(transactionId)
                            model.setStatus(array.getString("status"))
                            model.setResponse(res)
                            model.setProductinfo(intent.getStringExtra("productinfo"))
                            model.setEmail(userEmail)
                            val payuid = array.getString("paymentId")
                            model1.setPaymentid(array.getString("paymentId"))
                            model1.setAmount(array.getString("amount"))
                            model1.setDatetime(array.getString("addedon"))
                            model1.setAddress(intent.getStringExtra("address") + " " + "Pincode:" + intent.getStringExtra("pincode"))
                            model1.setMobileno(intent.getStringExtra("mobileno"))
                            model1.setOrderid(transactionId)
                            model1.setProductinfo(intent.getStringExtra("productinfo"))
                            model1.setEmail(userEmail)
                            model1.setStatus("enable")
                            FirebaseFirestore.getInstance().collection("Transaction History")
                                    .document(transactionId).set(model).addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            FirebaseFirestore.getInstance().collection("Users").document(userEmail!!).collection("Cart")
                                                    .addSnapshotListener(object : EventListener<QuerySnapshot?>() {
                                                        fun onEvent(@Nullable value: QuerySnapshot?, @Nullable error: FirebaseFirestoreException?) {
                                                            if (value != null && !value.isEmpty) {
                                                                for (i in 0 until value.size()) {
                                                                    value.documents[i].reference.delete()
                                                                }
                                                            }
                                                        }
                                                    })
                                            FirebaseFirestore.getInstance().collection("Users").document(userEmail).collection("ServicesCart")
                                                    .addSnapshotListener(object : EventListener<QuerySnapshot?>() {
                                                        fun onEvent(@Nullable value: QuerySnapshot?, @Nullable error: FirebaseFirestoreException?) {
                                                            if (value != null && !value.isEmpty) {
                                                                for (i in 0 until value.size()) {
                                                                    value.documents[i].reference.delete()
                                                                }
                                                            }
                                                        }
                                                    })
                                            SweetAlertDialog(this@PaymentActivity, SweetAlertDialog.SUCCESS_TYPE)
                                                    .setTitleText("Congratulation")
                                                    .setConfirmText("Done" + "\uD83D\uDC4C")
                                                    .setContentText("Order Id:$transactionId\nPayment Id:$payuid")
                                                    .setConfirmClickListener(object : OnSweetClickListener() {
                                                        fun onClick(sDialog: SweetAlertDialog) {
                                                            sDialog.dismissWithAnimation()
                                                            val intent = Intent(this@PaymentActivity, DasboardActivity::class.java)
                                                            startActivity(intent)
                                                            finish()
                                                        }
                                                    }).show()
                                            FirebaseFirestore.getInstance().collection("Order")
                                                    .document(transactionId).set(model1).addOnCompleteListener { task ->
                                                        if (task.isSuccessful) {
                                                            Toast.makeText(this@PaymentActivity, "Add Order Successfully", Toast.LENGTH_SHORT).show()
                                                        }
                                                    }
                                        } else {
                                        }
                                    }
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    } else {
                        //Failure Transaction
                        SweetAlertDialog(this@PaymentActivity, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText("Something went wrong!")
                                .show()
                        val intent = Intent(this@PaymentActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                    // Response from Payumoney
                    val payuResponse: String = transactionResponse.getPayuResponse()
                    Log.e("payuresponse>>>", payuResponse + "")

                    // Response from SURl and FURL
                    val merchantResponse: String = transactionResponse.getTransactionDetails()
                    Log.e("merchantResponse>>>", merchantResponse + "")
                } else {
                    SweetAlertDialog(this@PaymentActivity, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Something went wrong!")
                            .show()
                }
            }
        }
    }

    companion object {
        fun hashCal(type: String?, hashString: String): String {
            val hash = StringBuilder()
            var messageDigest: MessageDigest? = null
            try {
                messageDigest = MessageDigest.getInstance(type)
                messageDigest.update(hashString.toByteArray())
                val mdbytes: ByteArray = messageDigest.digest()
                for (hashByte in mdbytes) {
                    hash.append(Integer.toString((hashByte and 0xff) + 0x100, 16).substring(1))
                }
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            }
            return hash.toString()
        }
    }*/

