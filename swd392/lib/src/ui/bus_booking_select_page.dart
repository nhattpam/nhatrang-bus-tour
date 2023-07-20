import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:razorpay_flutter/razorpay_flutter.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:swd392/service/notification_service.dart';

class BusBookingSelectPage extends StatefulWidget {
  const BusBookingSelectPage({Key? key}) : super(key: key);

  @override
  State<BusBookingSelectPage> createState() => _BusBookingSelectPageState();
}

class _BusBookingSelectPageState extends State<BusBookingSelectPage> {
  NotificationServices notificationServices = NotificationServices();
  late Razorpay _razorpay;

  int adultCount = 0;
  int kidsCount = 0;
  int partnerCount = 0;
  int foreignTouristCount = 0;

  @override
  void initState() {
    super.initState();
    notificationServices.initialiseNotification();
    //razorpay
    // Initialize Razorpay with your API key
    _razorpay = Razorpay();
    _razorpay.on(Razorpay.EVENT_PAYMENT_SUCCESS, _handlePaymentSuccess);
    _razorpay.on(Razorpay.EVENT_PAYMENT_ERROR, _handlePaymentError);
    _razorpay.on(Razorpay.EVENT_EXTERNAL_WALLET, _handleExternalWallet);
  }

  @override
  void dispose() {
    super.dispose();

    // Remember to dispose of Razorpay when the widget is disposed
    _razorpay.clear();
  }

  // Define the callback functions for payment events
  void _handlePaymentSuccess(PaymentSuccessResponse response) {
    // Payment successful, handle success here
    print("Payment Successful");
    // You can perform further actions after successful payment
    notificationServices.sendNotification(
      'Booking Complete',
      235,
    );

    context.push('/ticket');
  }

  void _handlePaymentError(PaymentFailureResponse response) {
    // Payment failed, handle error here
    print("Payment Error: ${response.message}");
  }

  void _handleExternalWallet(ExternalWalletResponse response) {
    // Handle external wallet response here
    print("External Wallet: ${response.walletName}");
  }

  void _startPayment() {
    _getUserEmailFromSharedPreferences().then((userEmail) {
      var options = {
        'key': 'rzp_test_pZeKh9EQhVbOtn', // Replace with your Razorpay API key
        'amount': 23500, // Amount in paise (e.g., for Rs 235, set 23500)
        'name': userEmail ?? '',
        'description': 'Payment Processing',
        'prefill': {
          'contact': '9876543210', // Replace with the customer's phone number
          'email': userEmail ?? '', // Replace with the customer's email
        },
        'external': {
          'wallets': ['paytm'], // Optionally, add supported wallets (if any)
        }
      };

      try {
        _razorpay.open(options);
      } catch (e) {
        print("Error: $e");
      }
    });
  }

  // Method to get userEmail from shared preferences
  Future<String?> _getUserEmailFromSharedPreferences() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getString('userEmail');
  }

  void incrementCount(String ticketType) {
    setState(() {
      int totalCount = adultCount + kidsCount + partnerCount + foreignTouristCount;
      if (totalCount >= 5) {
        showDialog(
          context: context,
          builder: (BuildContext context) {
            return AlertDialog(
              title: Text("Warning"),
              content: Text("You can select a maximum of 5 tickets per booking."),
              actions: [
                TextButton(
                  child: Text("OK"),
                  onPressed: () {
                    Navigator.of(context).pop();
                  },
                ),
              ],
            );
          },
        );
      } else {
        if (ticketType == 'adult' && adultCount < 5) {
          adultCount++;
        } else if (ticketType == 'kids' && kidsCount < 5) {
          kidsCount++;
        } else if (ticketType == 'partner' && partnerCount < 5) {
          partnerCount++;
        } else if (ticketType == 'foreignTourist' && foreignTouristCount < 5) {
          foreignTouristCount++;
        }
      }
    });
  }

  void decrementCount(String ticketType) {
    setState(() {
      if (ticketType == 'adult' && adultCount > 0) {
        adultCount--;
      } else if (ticketType == 'kids' && kidsCount > 0) {
        kidsCount--;
      } else if (ticketType == 'partner' && partnerCount > 0) {
        partnerCount--;
      } else if (ticketType == 'foreignTourist' && foreignTouristCount > 0) {
        foreignTouristCount--;
      }
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.grey[200],
      appBar: AppBar(
        title: Text("Select Your Tickets"),
        titleTextStyle: TextStyle(
          fontWeight: FontWeight.bold,
          fontSize: 24,
          color: Colors.white,
        ),
        leading: IconButton(
          icon: Icon(Icons.arrow_back_ios),
          onPressed: () {
            Navigator.of(context).pop();
          },
          color: Colors.white,
        ),
        backgroundColor: Colors.red,
        elevation: 0,
        foregroundColor: Colors.black,
        centerTitle: true,
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              buildAnnouncementBlock(),
              SizedBox(height: 16),
              buildBusInformationBlock(),
              SizedBox(height: 16),
              Text(
                'Adult:',
                style: TextStyle(
                  fontSize: 20,
                  fontWeight: FontWeight.bold,
                ),
              ),
              buildTicketTypeRow(
                title: 'For passengers who have a height exceeding 1m3.',
                price: 20,
                count: adultCount,
                increment: () => incrementCount('adult'),
                decrement: () => decrementCount('adult'),
              ),
              SizedBox(height: 16),
              Text(
                'Kids:',
                style: TextStyle(
                  fontSize: 20,
                  fontWeight: FontWeight.bold,
                ),
              ),
              buildTicketTypeRow(
                title: 'For passengers who are shorter than or equal to 1m3.',
                price: 10,
                count: kidsCount,
                increment: () => incrementCount('kids'),
                decrement: () => decrementCount('kids'),
              ),
              SizedBox(height: 16),
              Text(
                'Partner:',
                style: TextStyle(
                  fontSize: 20,
                  fontWeight: FontWeight.bold,
                ),
              ),
              buildTicketTypeRow(
                title: 'For individuals who are eligible for a discount through a partnership or affiliation with a tourist company.',
                price: 15,
                count: partnerCount,
                increment: () => incrementCount('partner'),
                decrement: () => decrementCount('partner'),
              ),
              SizedBox(height: 16),
              Text(
                'Foreign Tourist:',
                style: TextStyle(
                  fontSize: 20,
                  fontWeight: FontWeight.bold,
                ),
              ),
              buildTicketTypeRow(
                title: 'Specifically tailored for international tourists.',
                price: 30,
                count: foreignTouristCount,
                increment: () => incrementCount('foreignTourist'),
                decrement: () => decrementCount('foreignTourist'),
              ),
              SizedBox(height: 16),
              buildTotalAmountRow(),
            ],
          ),
        ),
      ),
    );
  }

  Widget buildAnnouncementBlock() {
    return Container(
      padding: EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: Colors.green,
        borderRadius: BorderRadius.circular(8),
      ),
      child: Row(
        children: [
          Icon(
            Icons.verified,
            color: Colors.white,
            size: 20,
          ),
          SizedBox(width: 8),
          Expanded(
            child: Text(
              'Nha Trang Bus guarantees to keep the exact number of tickets you have selected.',
              style: TextStyle(
                color: Colors.white,
                fontSize: 14,
              ),
            ),
          ),
        ],
      ),
    );
  }

  Widget buildBusInformationBlock() {
    return Container(
      padding: EdgeInsets.all(16),
      decoration: BoxDecoration(
        color: Colors.blue,
        borderRadius: BorderRadius.circular(8),
      ),
      child: Text(
        'Bus Information: '
            'Route: Nha Trang 1 to Nha Trang 2. '
            'Schedule: 6:30 to 10:30.',
        style: TextStyle(
          color: Colors.white,
          fontSize: 14,
        ),
      ),
    );
  }

  Widget buildTicketTypeRow({
    required String title,
    required int price,
    required int count,
    required VoidCallback increment,
    required VoidCallback decrement,
  }) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      children: [
        Expanded(
          child: Text(
            title,
            style: TextStyle(
              fontSize: 14,
            ),
          ),
        ),
        SizedBox(width: 8),
        IconButton(
          icon: Icon(Icons.remove),
          onPressed: decrement,
        ),
        Text('$count'),
        IconButton(
          icon: Icon(Icons.add),
          onPressed: increment,
        ),
        SizedBox(width: 8),
        Text(
          '\$$price',
          style: TextStyle(
            fontSize: 16,
            fontWeight: FontWeight.bold,
          ),
        ),
      ],
    );
  }

  Widget buildTotalAmountRow() {
    final totalAmount =
        235;

    return Container(
      margin: EdgeInsets.only(top: 24),
      child: Column(
        children: [
          Text(
            'Total Amount:',
            style: TextStyle(
              fontSize: 18,
              fontWeight: FontWeight.bold,
            ),
          ),
          Text(
            '\$$totalAmount',
            style: TextStyle(
              fontSize: 24,
              fontWeight: FontWeight.bold,
            ),
          ),
          SizedBox(height: 16),
          Container(
            height: 54,
            width: double.infinity,
            decoration: BoxDecoration(
              color: Colors.red,
              borderRadius: BorderRadius.circular(32),
            ),
            child: TextButton(
              onPressed: () {
                // notificationServices.sendNotification(
                //   'Booking Complete',
                //   totalAmount,
                // );
                // context.push('/ticket');
                _startPayment(); // Call _startPayment
              },
              child: Text(
                'Confirm',
                style: TextStyle(
                  fontSize: 20,
                  fontWeight: FontWeight.bold,
                  color: Colors.white,
                ),
              ),
            ),
          ),
        ],
      ),
    );
  }
}
