import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:swd392/network/network_request_my_ticket.dart';
import 'login_page.dart';
import 'package:swd392/model/ticket.dart';
class OrderDetailScreen extends StatefulWidget {
  final int? orderId;

  const OrderDetailScreen({required this.orderId, Key? key}) : super(key: key);


  @override
  _OrderDetailScreenState createState() => _OrderDetailScreenState();
}

class _OrderDetailScreenState extends State<OrderDetailScreen> {

  List<Ticket> postData = []; // List to store orders

  @override
  void initState() {
    super.initState();
    _loadData(); // Load the list of orders
  }

  Future<void> _loadData() async {
    final orderId = widget.orderId ?? 0; // Replace 0 with a default value or handle the null case as needed

    NetworkRequestTicket.fetchTicket(orderId: orderId).then((dataFromServer) {
      setState(() {
        postData = dataFromServer as List<Ticket>;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    // Access the orderId using widget.orderId
    final orderId = widget.orderId;

    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.red,
        title: Text('Order Detail'),
        leading: IconButton(
          icon: Icon(Icons.arrow_back_ios),
          onPressed: () {
            // Navigate back to the previous screen
            Navigator.pop(context);
          },
        ),
      ),
      body: Column(
        children: [
          Expanded(
            child: ListView.builder(
              padding: const EdgeInsets.all(10),
              itemCount: postData.length,
              itemBuilder: (context, index) {
                return GestureDetector(
                  // Wrap each Card with GestureDetector for detecting tap events
                  child: Card(
                    elevation: 2,
                    child: Padding(
                      padding: const EdgeInsets.all(10),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            'Ticket Id: ${postData[index].ticketId}',
                            style: const TextStyle(
                              fontSize: 16,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          const SizedBox(height: 5),
                          Text(
                            'Passenger: ${postData[index].passengerName}',
                            style: const TextStyle(fontSize: 14),
                          ),
                          const SizedBox(height: 5),
                          Text(
                            'Phone: ${postData[index].passengerPhone}',
                            style: const TextStyle(fontSize: 14),
                          ),
                        ],
                      ),
                    ),
                  ),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}

