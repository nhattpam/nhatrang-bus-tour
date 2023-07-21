import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'login_page.dart';

class OrderDetailScreen extends StatefulWidget {
  final int? orderId;

  const OrderDetailScreen({required this.orderId, Key? key}) : super(key: key);

  @override
  _OrderDetailScreenState createState() => _OrderDetailScreenState();
}

class _OrderDetailScreenState extends State<OrderDetailScreen> {
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
      body: Center(
        child: Text('Order ID: ${orderId ?? 'Unknown'}'), // Display the orderId
      ),
    );
  }
}

