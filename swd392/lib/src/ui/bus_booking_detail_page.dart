import 'package:flutter/material.dart';
import 'package:go_router/go_router.dart';

class BusBookingDetailPage extends StatefulWidget {
  const BusBookingDetailPage({super.key});

  @override
  State<BusBookingDetailPage> createState() => _BusBookingDetailPageState();
}

class _BusBookingDetailPageState extends State<BusBookingDetailPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        elevation: 1,
        centerTitle: true,
        backgroundColor: Colors.red,
        foregroundColor: Colors.white,
        title: const Text(
          "Nha Trang Bus Search",
          style: TextStyle(
            color: Colors.white,
            fontWeight: FontWeight.bold,
          ),
        ),
        leading: IconButton(
          icon: Icon(Icons.arrow_back_ios),
          onPressed: () {
            Navigator.of(context).pop();
          },
          color: Colors.white,
        ),
      ),
      body: Column(
        children: [
          Container(
            margin: const EdgeInsets.all(16),
            height: 64,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(4),
              border: Border.all(
                color: Colors.grey,
              ),
            ),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                IconButton(
                  onPressed: () {

                  },
                  icon: const Icon(
                    Icons.filter_list_outlined,
                  ),
                ),
                const Text(
                  "Search for Trips",
                  style: TextStyle(
                    fontSize: 18,
                    fontWeight: FontWeight.bold,
                  ),
                )
              ],
            ),
          ),
          const Divider(),
          Expanded(
            child: SingleChildScrollView(
              child: Column(
                children: [
                  Container(
                    padding: const EdgeInsets.all(16),
                    child: Column(
                      children: [
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text("Departure", style: TextStyle(
                                  fontSize: 16,
                                ),
                                ),
                                Padding(
                                  padding: EdgeInsets.symmetric(
                                    vertical: 8,
                                  ),
                                  child: Text(
                                    "6:30",
                                    style: TextStyle(
                                      fontWeight: FontWeight.bold,
                                      fontSize: 20,
                                    ),
                                  ),
                                ),
                                Text("Nha Trang 1",style: TextStyle(
                                  fontSize: 16,
                                ),
                                ),
                              ],
                            ),
                            Column(
                              crossAxisAlignment: CrossAxisAlignment.end,
                              children: [
                                Text("Arrival", style: TextStyle(
                                  fontSize: 16,
                                ),
                                ),
                                Padding(
                                  padding: EdgeInsets.symmetric(
                                    vertical: 8,
                                  ),
                                  child: Text(
                                    "10:30",
                                    style: TextStyle(
                                      fontWeight: FontWeight.bold,
                                      fontSize: 20,
                                    ),
                                  ),
                                ),
                                Text(
                                  "Nha Trang 2",
                                  style: TextStyle(
                                    fontSize: 16,
                                  ),
                                ),
                              ],
                            )
                          ],
                        ),
                        Padding(
                          padding: EdgeInsets.symmetric(vertical: 24),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Icon(Icons.accessible),
                              Padding(
                                padding: EdgeInsets.all(8.0),
                                child: Icon(Icons.work),
                              ),
                              Icon(Icons.electrical_services),
                            ],
                          ),
                        ),
                        GestureDetector(
                          onTap: (){
                            context.push("/seat");
                          },
                          child: Container(
                            decoration: BoxDecoration(
                                color: Colors.red,
                                borderRadius: BorderRadius.circular(32)
                            ),
                            padding: EdgeInsets.symmetric(vertical: 16),
                            child: Center(
                              child: Text.rich(TextSpan(
                                children: [
                                  TextSpan(
                                    text: "FROM",
                                    style: TextStyle(
                                        color: Colors.white,
                                        fontSize: 20
                                    ),
                                  ),
                                  TextSpan(
                                    text: ' 10\$',
                                    style: TextStyle(
                                        color: Colors.white,
                                        fontWeight: FontWeight.bold,
                                        fontSize: 22
                                    ),
                                  ),
                                ],
                              ),
                              ),
                            ),
                          ),
                        )
                      ],
                    ),
                  ),
                  Divider(),
                  Container(
                    padding: const EdgeInsets.all(16),
                    child: Column(
                      children: [
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Column(
                              crossAxisAlignment: CrossAxisAlignment.start,
                              children: [
                                Text("Departure", style: TextStyle(
                                  fontSize: 16,
                                ),
                                ),
                                Padding(
                                  padding: EdgeInsets.symmetric(
                                    vertical: 8,
                                  ),
                                  child: Text(
                                    "8:30",
                                    style: TextStyle(
                                      fontWeight: FontWeight.bold,
                                      fontSize: 20,
                                    ),
                                  ),
                                ),
                                Text("Nha Trang 1",style: TextStyle(
                                  fontSize: 16,
                                ),
                                ),
                              ],
                            ),
                            Column(
                              crossAxisAlignment: CrossAxisAlignment.end,
                              children: [
                                Text("Arrival", style: TextStyle(
                                  fontSize: 16,
                                ),
                                ),
                                Padding(
                                  padding: EdgeInsets.symmetric(
                                    vertical: 8,
                                  ),
                                  child: Text(
                                    "12:00",
                                    style: TextStyle(
                                      fontWeight: FontWeight.bold,
                                      fontSize: 20,
                                    ),
                                  ),
                                ),
                                Text(
                                  "Nha Trang 2",
                                  style: TextStyle(
                                    fontSize: 16,
                                  ),
                                ),
                              ],
                            )
                          ],
                        ),
                        Padding(
                          padding: EdgeInsets.symmetric(vertical: 24),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.center,
                            children: [
                              Icon(Icons.accessible),
                              Padding(
                                padding: EdgeInsets.all(8.0),
                                child: Icon(Icons.work),
                              ),
                              Icon(Icons.electrical_services),
                            ],
                          ),
                        ),
                        GestureDetector(
                          onTap: (){
                            context.push("/seat");
                          },
                          child: Container(
                            decoration: BoxDecoration(
                                color: Colors.red,
                                borderRadius: BorderRadius.circular(32)
                            ),
                            padding: EdgeInsets.symmetric(vertical: 16),
                            child: Center(
                              child: Text.rich(TextSpan(
                                children: [
                                  TextSpan(
                                    text: "FROM",
                                    style: TextStyle(
                                        color: Colors.white,
                                        fontSize: 20
                                    ),
                                  ),
                                  TextSpan(
                                    text: ' 10\$',
                                    style: TextStyle(
                                        color: Colors.white,
                                        fontWeight: FontWeight.bold,
                                        fontSize: 22
                                    ),
                                  ),
                                ],
                              ),
                              ),
                            ),
                          ),
                        )
                      ],
                    ),
                  ),
                  Divider(),

                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
