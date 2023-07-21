import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';
import 'package:swd392/model/order.dart';
import 'package:swd392/network/network_request_my_order.dart';
import 'package:swd392/src/ui/order_detail_screen.dart';
import '../model/ticket.dart';
import '../network/network_request_my_ticket.dart';
import 'package:shared_preferences/shared_preferences.dart';


class MyOrderScreen extends StatelessWidget {
  const MyOrderScreen({Key? key}) : super(key: key);


  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'API',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: ListViewPage(),
      debugShowCheckedModeBanner: false,
    );
  }
}

class ListViewPage extends StatefulWidget {
  const ListViewPage({Key? key}) : super(key: key);

  @override
  State<ListViewPage> createState() => _ListViewPageState();
}
//get userEmail from SharePreferences
Future<String?> getUserEmailFromSession() async {
  SharedPreferences prefs = await SharedPreferences.getInstance();
  return prefs.getString('userEmail');
}


class _ListViewPageState extends State<ListViewPage> {
  List<Order> postData = [];


  @override
  void initState() {
    super.initState();
    _loadData(); // Call a new method to load data and userEmail
  }

  Future<void> _loadData() async {
    String? userEmail = await getUserEmailFromSession();
    print("day la email: $userEmail"); // Print the userEmail

    NetworkRequestOrder.fetchOrder().then((dataFromServer) {
      setState(() {
        postData = dataFromServer as List<Order>;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "My Orders",
          style: TextStyle(
            fontSize: 18,
            fontWeight: FontWeight.bold,
          ),
        ),
        backgroundColor: Colors.red,
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
                  onTap: () {
                    // Navigate to the OrderDetailScreen with the orderId as a route parameter
                    GoRouter.of(context).push('/order_detail/${postData[index].orderId}');
                  },
                  child: Card(
                    elevation: 2,
                    child: Padding(
                      padding: const EdgeInsets.all(10),
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            'Order ID: ${postData[index].orderId}',
                            style: const TextStyle(
                              fontSize: 16,
                              fontWeight: FontWeight.bold,
                            ),
                          ),
                          const SizedBox(height: 5),
                          Text(
                            'Order Date: ${postData[index].orderDate}',
                            style: const TextStyle(fontSize: 14),
                          ),
                          const SizedBox(height: 5),
                          Text(
                            'Cost: \$ ${postData[index].totalPrice}',
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
