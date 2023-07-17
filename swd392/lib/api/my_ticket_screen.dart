import 'package:flutter/material.dart';
import '../model/ticket.dart';
import '../network/network_request_my_ticket.dart';
import 'package:shared_preferences/shared_preferences.dart';


class MyTicketScreen extends StatelessWidget {
  const MyTicketScreen({Key? key}) : super(key: key);


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
  List<Ticket> postData = [];


  @override
  void initState() {
    super.initState();
    _loadData(); // Call a new method to load data and userEmail
  }

  Future<void> _loadData() async {
    String? userEmail = await getUserEmailFromSession();
    print("day la email: $userEmail"); // Print the userEmail

    NetworkRequestTicket.fetchTicket().then((dataFromServer) {
      setState(() {
        postData = dataFromServer as List<Ticket>;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "My Tickets",
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
                return Card(
                  elevation: 2,
                  child: Padding(
                    padding: const EdgeInsets.all(10),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          'Ticket ID: ${postData[index].ticketId}',
                          style: const TextStyle(
                            fontSize: 16,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        const SizedBox(height: 5),
                        Text(
                          'Name: ${postData[index].passengerName}',
                          style: const TextStyle(fontSize: 14),
                        ),
                        const SizedBox(height: 5),
                        Text(
                          'Phone: ${postData[index].passengerPhone}',
                          style: const TextStyle(fontSize: 14),
                        ),
                        const SizedBox(height: 5),
                        Text(
                          'Email: ${postData[index].passengerEmail}',
                          style: const TextStyle(fontSize: 14),
                        ),
                        const SizedBox(height: 5),
                        Text(
                          'Feedback: ${postData[index].feedback}',
                          style: const TextStyle(fontSize: 14),
                        ),
                      ],
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
