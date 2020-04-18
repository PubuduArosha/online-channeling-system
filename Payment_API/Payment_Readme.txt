Payment Management Service

After a registered patient makes an appointment, the patient will have to make a payment. Patient will enter his/her card details and proceed payment. The payment status will be updated as per the payment success or failure. The patient can edit the payment details and also view their payments made to the system. An Admin can add, view and Update the Payment details in the Database (Payment Table) by search Its PaymentID. Also, if the patient needs to cancel appointment the Request send to Admin First. If the Request is Accepted, Admin add those Details to the Refund Table in the Database and Send the Refund to the Patient. If Admin Add Double Entries, He/ She can Delete Data By selecting unwanted   RefundID. And Admin Can View, Update, Edit and Delete the Refund Data inside the Table.

<<<<<<<<<<<<<  API design Payment  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

Resource: Payment
Request: GET http://localhost:8080/Payment_API/payAPI/Payment
Response: HTML table with type, dateAndTime, amount, PaymentStatus


Resource: Payment
Request: POST http://localhost:8080/Payment_API/payAPI/Payment
Media: Application form data - URL encoded
Data: {
	    “type”: “Credit Card”,
	    “dateAndTime”: “2020-04-20 15:14:10”,
	    “amount”: “5000.00”,
	    “paymentStatus”: “Pending”
	  }
Response: String Status message
"Inserted Payment successfully"/ "Error while inserting the Payment."


Resource: Payment
Request: PUT http://localhost:8080/Payment_API/payAPI/Payment
Media: Application JSON
Data: {
         "paymentID":"4",
         "type":"Debit Card",
         "dateAndTime":"2020-04-20 15:14:10",
         "amount":"5000.00",
 	 "paymentStatus":"Paid"
      }
Response: String status message
"Payment Updated successfully" / "Error while updating the Payment."



<<<<<<<<<<<<<  API design Refund  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


Resource: Refund
Request: GET http://localhost:8080/Payment_API/payAPI/Refund
Response: HTML table with paymentID, amout, date, time, method, adminID 


Resource: Refund
Request: POST http://localhost:8080/Payment_API/payAPI/Refund
Media: Application Form data - URL encoded
Data: {
	“paymentID”: “4”,
	“amount”: “5000.00”,
	“date”: “2020-04-05”,
	“time”: “12:14:40”,
	“method”: “Online”, 
	“adminID”: “2”
      }
Response: String Status message
"Inserted Refund successfully" / "Error while inserting the Refund."


Resource: Refund
Request: DELETE http://localhost:8080/Payment_API/payAPI/Refund
Media: Application XML
Data:	<refundData>
 		<refundID> 2 </refundID>
	</refundData> 
Response: String Status message
"Refund Data Deleted successfully" / "Error while deleting the Refund Data."


Resource: Refund
Request: PUT http://localhost:8080/Payment_API/payAPI/Refund
Media: Application JSON
Data: {
 	"refundID":"3",
 	"paymentID":"2",
 	"amount":"1000.00",
 	"date":"2020-05-10",
 	"time":"23:14:40",
 	"method":"Online",
 	"adminID":"2"
      } 
Response: String status message
"Refund Updated successfully"/ "Error while updating the Refund Amount."
