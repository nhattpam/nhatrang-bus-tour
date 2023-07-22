import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:swd392/network/network_request_my_ticket.dart';
import 'login_page.dart';
import 'package:swd392/model/ticket.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class OrderDetailScreen extends StatefulWidget {
  final int? orderId;

  const OrderDetailScreen({required this.orderId, Key? key}) : super(key: key);


  @override
  _OrderDetailScreenState createState() => _OrderDetailScreenState();
}

class _OrderDetailScreenState extends State<OrderDetailScreen> {

  List<Ticket> postData = []; // List to store orders

  String? ticketTypeName = "";
  static const String bearerToken = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJuaGF0cmFuZ2J1c0BnbWFpbC5jb20iLCJpYXQiOjE2ODk3NzQ0NjcsImV4cCI6MTY5MDM3OTI2N30.XX3PWi7MgZpy8nKwucCkB9jyC6oQbV-MqV0JaGqFrSOoAzZ_5zraq5-_KeimvI0yHdZbd4M9AsCFsdbmtlM_Uw';


  @override
  void initState() {
    super.initState();
    _loadData(); // Load the list of orders
  }

  Future<void> _loadData() async {
    final orderId = widget.orderId ?? 0;

    // Fetch the list of tickets from the API
    List<Ticket> tickets = await NetworkRequestTicket.fetchTicket(orderId: orderId);

    // Fetch the ticketTypeName for each ticketId in tickets
    List<Future<void>> futures = tickets.map((ticket) => _fetchTicketTypeName(ticket)).toList();
    await Future.wait(futures);

    // After all API calls complete, update the UI
    setState(() {
      postData = tickets;
    });
  }

  Future<String> _fetchTicketTypeName(Ticket ticket) async {
    String baseUrl = 'https://nhatrangbustourbackend.azurewebsites.net/api/tickets/ticket-type/${ticket.ticketId}';
    try {
      http.Response response = await http.get(
        Uri.parse(baseUrl),
        headers: {
          'Authorization': 'Bearer $bearerToken',
        },
      );

      if (response.statusCode == 200) {
        return response.body;
      } else if (response.statusCode == 404) {
        // Ticket type not found
        return 'Ticket Type Not Found';
      } else if (response.statusCode == 401) {
        // Unauthorized user
        throw Exception('Unauthorized user ID (post order)');
      } else {
        // Handle other error responses
        return 'Error';
      }
    } catch (e) {
      // Handle any exceptions
      print('Error fetching ticketTypeName: $e');
      return 'Error';
    }
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
                          const SizedBox(height: 5),

                          FutureBuilder<String>(
                            future: _fetchTicketTypeName(postData[index]),
                            builder: (context, snapshot) {
                              if (snapshot.connectionState == ConnectionState.waiting) {
                                return Text(
                                  'Type: Loading...', // Show a loading message while waiting for the API response
                                  style: const TextStyle(fontSize: 14),
                                );
                              } else if (snapshot.hasError) {
                                return Text(
                                  'Type: Error', // Show an error message if there was an error fetching ticketTypeName
                                  style: const TextStyle(fontSize: 14),
                                );
                              } else {
                                return Text(
                                  'Type: ${snapshot.data}',
                                  style: const TextStyle(fontSize: 14),
                                );
                              }
                            },
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

